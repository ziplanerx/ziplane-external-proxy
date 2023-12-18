package com.ziplane.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResults {
        public String title;
        public String place_id;
        public String data_id;
        public String data_cid;
        public String reviews_link;
        public String photos_link;
       // public GpsCoordinates gps_coordinates;
        public String place_id_search;
        public String provider_id;
        public String thumbnail;
       // public ArrayList<RatingSummary> rating_summary;
        public double rating;
        public int reviews;
        public ArrayList<String> type;
      //  public ServiceOptions service_options;
      //  public ArrayList<Extension> extensions;
        public String address;
        public String website;
        public String phone;
        public String open_state;
        public String plus_code;
        public ArrayList<Hour> hours;
        public ArrayList<Image> images;
      //  public ArrayList<QuestionsAndAnswer> questions_and_answers;
        public UserReviews user_reviews;
       // public ArrayList<PeopleAlsoSearchFor> people_also_search_for;
       // public PopularTimes popular_times;

}
