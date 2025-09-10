package app;
import lombok.Data;
import java.util.List;


public class ChatbotRequest {
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocationInfo getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LocationInfo currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(String digitalId) {
        this.digitalId = digitalId;
    }

    public EnvironmentInfo getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentInfo environment) {
        this.environment = environment;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getUserQuery() {
        return userQuery;
    }

    public void setUserQuery(String userQuery) {
        this.userQuery = userQuery;
    }

    private String touristName;
    private int age;
    private String digitalId;
    private String tripStartDate;
    private String tripEndDate;
    private LocationInfo currentLocation;
    private EnvironmentInfo environment;
    private String userQuery;
    private String language; // optional for multilingual support
}


class LocationInfo {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(int safetyScore) {
        this.safetyScore = safetyScore;
    }

    public String getZoneStatus() {
        return zoneStatus;
    }

    public void setZoneStatus(String zoneStatus) {
        this.zoneStatus = zoneStatus;
    }

    private double lat;
    private double lng;
    private String address;
    private String zoneStatus; // e.g., Safe, Red, Restricted
    private int safetyScore;   // 0-100
}


class EnvironmentInfo {
    public List<String> getNearbyIncidents() {
        return nearbyIncidents;
    }

    public void setNearbyIncidents(List<String> nearbyIncidents) {
        this.nearbyIncidents = nearbyIncidents;
    }

    private List<String> nearbyIncidents; // e.g., ["Protest near India Gate"]
}
