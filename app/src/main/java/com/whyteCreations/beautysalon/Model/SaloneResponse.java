package com.whyteCreations.beautysalon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaloneResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("content")

    private List<Salon> salonList;

    public SaloneResponse(String status, List<Salon> salonList) {
        this.status = status;
        this.salonList = salonList;
    }

    public String getStatus() {
        return status;
    }

    public List<Salon> getSalonList() {
        return salonList;
    }
}
