package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleTrackingData {

    private String vehicleId;
    private String date;
    private List<LocationData> locations;

    public VehicleTrackingData(String vehicleId, String date) {
        this.vehicleId = vehicleId;
        this.date = date;
        this.locations = new ArrayList<LocationData>();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<LocationData> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationData> locations) {
        this.locations = locations;
    }

    public LocationData getFirstLocation() {
        LocationData location = new LocationData(new Date(), 0.0, 0.0);

        if (! locations.isEmpty()) {
            location = locations.get(0);
        }

        return location;
    }
}
