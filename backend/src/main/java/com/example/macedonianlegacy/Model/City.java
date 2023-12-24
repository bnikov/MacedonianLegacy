package com.example.macedonianlegacy.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cityname")
    private String cityName;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;
}
