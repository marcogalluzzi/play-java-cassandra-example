package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Config conf = ConfigFactory.load("additional.conf");
        Cluster cluster = null;
        String renderString = "Cannot connect to Cassandra";
        try {
            cluster = Cluster.builder()
                    .addContactPoint(conf.getString("cassandra.contact-point"))
                    .build();
            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            renderString = "Connection successful to Cassandra version " + row.getString("release_version");
            
        } finally {
            if (cluster != null) cluster.close();
        }

        return ok(index.render(renderString));
    }

}
