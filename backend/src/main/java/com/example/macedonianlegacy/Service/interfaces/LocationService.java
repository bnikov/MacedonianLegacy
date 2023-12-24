package com.example.macedonianlegacy.Service.interfaces;

import com.example.macedonianlegacy.Model.Location;
import com.example.macedonianlegacy.misc.RequestResponse.LocationSearchRequest;

import java.util.List;
import java.util.Optional;


public interface LocationService {

    List<Location> findAll();

    List<Location> search(LocationSearchRequest locationSearchRequest);

    Optional<Location> findById(Long id);

    List<Location> findByCategory(String category);
}
