package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Location {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;

        @OneToMany(mappedBy = "location")
        private List<Forecast> forecasts;

        @OneToOne
        @JoinColumn(name = "current_weather_id")
        private CurrentWeather currentWeather;
}

