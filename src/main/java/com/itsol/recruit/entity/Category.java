package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIES_SEQ")
    @SequenceGenerator(name = "CATEGORIES_SEQ", sequenceName = "CATEGORIES_SEQ", allocationSize = 1, initialValue = 1)
    int id;
    @Column(name = "name")
    String name;
    @Column(name="status")
    int status;
}
