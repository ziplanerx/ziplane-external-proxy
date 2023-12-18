package com.ziplane.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalResults {
    public int position;
    public String title;
    public String place_id;
    public String data_id;
    public String data_cid;
    public String reviews_link;
    public String photos_link;
    public String place_id_search;
    public String provider_id;
    public double rating;
    public int reviews;
    public String type;
    public ArrayList<String> types;
    public String address;
    public String open_state;
    public String hours;
    public OperatingHours operating_hours;
    public String phone;
    public String website;
    public String thumbnail;
}
