package com.example.goconnect;

public class Services_helper {

    String serviceName;
    String prices;
    String phoneNo;
    String timings;
    String location;
    String  Latitude;
    String Longitude;



    public Services_helper() {

    }

    public Services_helper(String serviceName, String prices, String phoneNo, String timings, String location, String Lat, String Long) {
        this.serviceName = serviceName;
        this.prices = prices;
        this.phoneNo = phoneNo;
        this.timings = timings;
        this.location = location;
        this.Latitude = Lat;
        this.Longitude = Long;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }


}
