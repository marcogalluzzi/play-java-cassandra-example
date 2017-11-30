package controllers;

import com.datastax.driver.core.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import data.AlarmEventData;
import data.HomeData;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.alarms;

import java.util.ArrayList;

public class AlarmSystemController extends Controller {

    public Result index(String h_id) {

        if (h_id == null || h_id.isEmpty()) {
            return ok(alarms.render(null));
        }

        HomeData homeData = new HomeData(h_id);

        Config conf = ConfigFactory.load("additional.conf");
        Cluster cluster = null;
        try {
            cluster = Cluster.builder()
                    .addContactPoint(conf.getString("cassandra.contact-point"))
                    .build();
            Session session = cluster.connect();

            // Get alarm events
            String queryString =
                    "SELECT datetime, event, code_used FROM home_security.activity WHERE home_id = :home_id";

            PreparedStatement prepared = session.prepare(queryString);

            BoundStatement bound = prepared.bind().setString("home_id", h_id);

            ResultSet rs = session.execute(bound);

            ArrayList<AlarmEventData> events = new ArrayList<AlarmEventData>();

            for (Row row : rs) {
                events.add(
                        new AlarmEventData(
                                row.getTimestamp("datetime"),
                                row.getString("code_used"),
                                row.getString("event")));
            }

            homeData.setEvents(events);

            // Get home information
            queryString =
                    "SELECT contact_name, address, city, state, zip FROM home_security.home WHERE home_id = :home_id";

            prepared = session.prepare(queryString);
            bound = prepared.bind().setString("home_id", h_id);
            rs = session.execute(bound);

            if (! rs.isExhausted()) {
                Row row = rs.one();
                homeData.setContactName(row.getString("contact_name"));
                homeData.setAddress(row.getString("address"));
                homeData.setCity(row.getString("city"));
                homeData.setState(row.getString("state"));
                homeData.setZip(row.getString("zip"));
            }
        } finally {
            if (cluster != null) cluster.close();
        }

        return ok(alarms.render(homeData));
    }

}
