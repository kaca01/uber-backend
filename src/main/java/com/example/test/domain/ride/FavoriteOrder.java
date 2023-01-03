package com.example.test.domain.ride;
import com.example.test.domain.user.Passenger;
import com.example.test.enumeration.VehicleTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FavoriteOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "favoriteName", nullable = false)
    private String favoriteName;
    @Column(name = "vehicleType", nullable = false)
    private VehicleTypeName vehicleType;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Passenger passenger;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Passenger> passengers = new HashSet<>();
    @Column(name = "babyTransport", nullable = false)
    private boolean babyTransport;
    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Route> locations = new HashSet<>();

}
