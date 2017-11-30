package controllers;

import com.datastax.driver.core.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import data.LocationData;
import data.VehicleTrackingData;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.track;

import java.util.ArrayList;

public class TrackingController extends Controller {

    public Result index(String veh_id, String date_val) {

        if (veh_id == null || veh_id.isEmpty()) {
            return ok(track.render(null));
        }

        VehicleTrackingData trackingData = new VehicleTrackingData(veh_id, date_val);

        Config conf = ConfigFactory.load("additional.conf");
        Cluster cluster = null;
        try {
            cluster = Cluster.builder()
                    .addContactPoint(conf.getString("cassandra.contact-point"))
                    .build();
            Session session = cluster.connect();

            String queryString =
                    "SELECT time, latitude, longitude FROM vehicle_tracker.location " +
                    "WHERE vehicle_id = :vehicle_id AND date = :date LIMIT 1";

            PreparedStatement prepared = session.prepare(queryString);

            BoundStatement bound = prepared.bind()
                    .setString("vehicle_id", veh_id)
                    .setString("date", date_val);

            ResultSet rs = session.execute(bound);

            ArrayList<LocationData> locations = new ArrayList<LocationData>();

            for (Row row : rs) {
                locations.add(
                        new LocationData(
                                row.getTimestamp("time"),
                                row.getDouble("latitude"),
                                row.getDouble("longitude")));
            }

            trackingData.setLocations(locations);

        } finally {
            if (cluster != null) cluster.close();
        }

        return ok(track.render(trackingData));
    }

}
