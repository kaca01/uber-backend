package com.example.test.domain.ride;
import com.example.test.domain.user.Passenger;
import com.example.test.enumeration.VehicleTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @Length(max = 100)
    @NotNull
    @NotEmpty
    private String favoriteName;
    @Column(name = "vehicleType", nullable = false)
    @NotNull
    private VehicleTypeName vehicleType;
    @ManyToOne(fetch = FetchType.EAGER)
    private Passenger passenger;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Passenger> passengers = new HashSet<>();
    @Column(name = "babyTransport", nullable = false)
    private boolean babyTransport;
    @Column(name = "petTransport", nullable = false)
    private boolean petTransport;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Route> locations = new HashSet<>();

}
