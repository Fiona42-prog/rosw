package org.example.entities;

import jakarta.persistence.*;

@Entity
public class CurrentWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temp;
    @Column(name = "wind_speed")
    private double windSpeed;

    @OneToOne(mappedBy = "currentWeather")
    private Location location;
}
