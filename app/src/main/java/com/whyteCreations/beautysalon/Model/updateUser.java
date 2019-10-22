package com.whyteCreations.beautysalon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class updateUser extends UpdateUserResponse {
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("building_number")
    @Expose
    private String buildingNumber;
    @SerializedName("zone_number")
    @Expose
    private String zoneNumber;
    @SerializedName("street_number")
    @Expose
    private String streetNumber;
    @SerializedName("street_name")
    @Expose
    private String streetName;

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(String zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
