package data;

import java.util.Date;

public class LocationData {

    private Date time;
    private Double latitude;
    private Double longitude;

    public LocationData(Date time, Double latitude, Double longitude) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getTime() {
        return time;
    }

    public String getTimeAsString() {
        return time.toString();
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getLatitudeAsString() {
        return latitude.toString();
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLongitudeAsString() {
        return longitude.toString();
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
