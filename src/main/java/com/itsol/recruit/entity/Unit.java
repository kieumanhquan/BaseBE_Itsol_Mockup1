package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "UNITS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Unit {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNITS_SEQ")
    @SequenceGenerator(name = "UNITS_SEQ", sequenceName = "UNITS_SEQ", allocationSize = 1, initialValue = 1)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "status")
    int status;

}
