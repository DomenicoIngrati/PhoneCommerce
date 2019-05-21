package model;

public class PositionMap {

    private long id;
    private double latitude;
    private double longitude;

    public PositionMap(){
        latitude = 0;
        longitude = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
//        return "Indirzzo [id=" + id + ", nomeecognome=" + namelastname + ", address=" + address + ", city=" + city + ", province=" + province + ", zipcode=" + zipcode + ", tel=" + tel + ", User=" + user.toString() + " ]";
        return "";
    }

}
