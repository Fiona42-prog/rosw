package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @Column(name = "min_temp")
    private double minTemp;
    @Column(name = "max_temp")
    private double maxTemp;
    @Column(name = "avg_temp")
    private double avgTemp;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
}
