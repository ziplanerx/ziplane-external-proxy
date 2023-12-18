package com.ziplane.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Target {
    String vatNumber;
    String name;
    Address address;
}
