package com.ziplane.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

        String line1;
        String line2;
        String line3;
        String postcode;
        String countryCode;
    }

