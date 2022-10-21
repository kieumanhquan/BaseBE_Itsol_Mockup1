package com.itsol.recruit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "PERMISSTION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSTION_SEQ")
    @SequenceGenerator(name = "PERMISSTION_SEQ", sequenceName = "PERMISSTION_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private  User user;
    @ManyToOne
    @JoinColumn(name="ROLE_ID")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private Role role;

}
