package com.example.test.domain.ride;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Route {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.PERSIST) //moglo bi i all jer lokacije ipak nisu dijeljenje jer se ne proslijedjuje njihov id?
    private Location departure;
    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.PERSIST)
    private Location destination;
}
