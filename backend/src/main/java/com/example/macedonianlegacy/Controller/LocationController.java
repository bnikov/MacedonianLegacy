package com.example.macedonianlegacy.Controller;

import com.example.macedonianlegacy.Model.Location;
import com.example.macedonianlegacy.Service.interfaces.LocationService;
import com.example.macedonianlegacy.misc.Exceptions.LocationNotFoundException;
import com.example.macedonianlegacy.misc.RequestResponse.LocationSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/findAll")
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @PostMapping("/search")
    public List<Location> search(@RequestBody LocationSearchRequest locationSearchRequest) {
        return locationService.search(locationSearchRequest);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Location> findById(@PathVariable Long id) {
        return locationService.findById(id)
                .map(resp -> ResponseEntity.ok().body(resp))
                .orElseThrow(LocationNotFoundException::new);
    }

    @GetMapping("/findAllByCategory")
    public List<Location> findAllByCategory(@RequestParam String category) {
        return locationService.findByCategory(category);
    }
}
