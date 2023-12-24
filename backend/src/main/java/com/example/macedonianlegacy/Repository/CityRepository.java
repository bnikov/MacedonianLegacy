package com.example.macedonianlegacy.Repository;

import com.example.macedonianlegacy.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByCityNameIn(List<String> cityNames);}
