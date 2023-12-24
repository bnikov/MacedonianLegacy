package com.example.macedonianlegacy.Service.interfaces;

import com.example.macedonianlegacy.Model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> findByCityNames(List<String> cityNames);

    List<City> findAll();

    Double haversineDistance(double lat1, double lon1, double lat2, double lon2);
}
