package com.example.macedonianlegacy.misc.RequestResponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class LocationSearchRequest {
    private String name;
    private List<String> category;
    private List<String> cityName;
}
