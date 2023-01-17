package com.example.test.domain.ride;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Route {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.PERSIST)
    @NotNull
    private Location departure;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.PERSIST)
    @NotNull
    private Location destination;
}
