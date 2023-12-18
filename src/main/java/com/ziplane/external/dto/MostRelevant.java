package com.ziplane.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostRelevant {
        public String username;
        public int rating;
        public String contributor_id;
        public String description;
        public ArrayList<Image> images;
        public String date;
    }

