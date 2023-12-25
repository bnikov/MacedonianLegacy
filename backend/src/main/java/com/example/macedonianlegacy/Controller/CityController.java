package com.example.macedonianlegacy.Controller;

import com.example.macedonianlegacy.Model.City;
import com.example.macedonianlegacy.Service.interfaces.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "https://dians-fe-3ikxnodbya-lm.a.run.app"})
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }
}
