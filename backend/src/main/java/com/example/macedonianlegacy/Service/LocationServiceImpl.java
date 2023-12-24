package com.example.macedonianlegacy.Service;

import com.example.macedonianlegacy.Model.City;
import com.example.macedonianlegacy.Model.Location;
import com.example.macedonianlegacy.Repository.LocationRepository;
import com.example.macedonianlegacy.Service.interfaces.CityService;
import com.example.macedonianlegacy.Service.interfaces.LocationService;
import com.example.macedonianlegacy.misc.RequestResponse.LocationSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final CityService cityService;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> search(LocationSearchRequest locationSearchRequest) {

        List<City> cities = cityService.findByCityNames(locationSearchRequest.getCityName());
        List<Location> locations = locationRepository.search(locationSearchRequest);
        List<String> categories = locationSearchRequest.getCategory();

        if (locations.isEmpty() && (cities.isEmpty() || categories.isEmpty())) {
            return locations;
        }

        List<Location> result = new ArrayList<>();

        for (Location location : locations) {
            boolean isCategoryMatch = categories.isEmpty(); // true if categories are empty
            boolean isCityMatch = cities.isEmpty(); // true if cities are empty

            if (!categories.isEmpty()) {
                for (String category : categories) {
                    if (location.getCategory().equals(category)) {
                        isCategoryMatch = true;
                        break;
                    }
                }
            }

            if (!cities.isEmpty()) {
                for (City city : cities) {
                    float locationLatitude = location.getLatitude();
                    float locationLongitude = location.getLongitude();
                    float cityLatitude = Float.parseFloat(city.getLatitude());
                    float cityLongitude = Float.parseFloat(city.getLongitude());

                    if (cityService.haversineDistance(locationLatitude, locationLongitude, cityLatitude, cityLongitude) < 10) {
                        isCityMatch = true;
                        break;
                    }
                }
            }

            if (isCategoryMatch && isCityMatch) {
                result.add(location);
            }
        }

        return result;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public List<Location> findByCategory(String category) {
        return locationRepository.findAllByCategory(category);
    }
}
