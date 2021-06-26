package com.example.goconnect;

public class Services_helper {

    String serviceName, prices, phoneNo, timings, location;


    public Services_helper() {

    }
    public Services_helper(String serviceName, String prices, String phoneNo, String timings, String location) {
        this.serviceName = serviceName;
        this.prices = prices;
        this.phoneNo = phoneNo;
        this.timings = timings;
        this.location = location;
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
}
