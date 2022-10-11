package com.itsol.recruit.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CONTRACTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "created_date")
    Date createdDate;
    @Column(name = "end_date")
    Date endDate;
    @Column(name = "note")
    String note;
    @Column(name = "reason")
    int reason;
    @Column(name = "status")
    int status;

}
