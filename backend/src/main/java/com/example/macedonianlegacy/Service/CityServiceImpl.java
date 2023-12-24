package com.example.macedonianlegacy.Service;

import com.example.macedonianlegacy.Model.City;
import com.example.macedonianlegacy.Repository.CityRepository;
import com.example.macedonianlegacy.Service.interfaces.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    public List<City> findByCityNames(List<String> cityNames) {
        return cityRepository.findAllByCityNameIn(cityNames);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dlat = lat2Rad - lat1Rad;
        double dlon = lon2Rad - lon1Rad;
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double radius = 6371;

        return radius * c;
    }
}
