package com.itsol.recruit.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity(name = "TRANSFERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSFERS_SEQ")
    @SequenceGenerator(name = "TRANSFERS_SEQ", sequenceName = "TRANSFERS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "ID")
    int id;
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    User employee;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User hr;
    @ManyToOne
    @JoinColumn(name = "UNIT_OLD")
    Unit unitOld;
    @ManyToOne
    @JoinColumn(name = "UNIT_NEW")
    Unit unitNew;
    @NotBlank
    @Column(name = "name")
    String name;
    @Column(name = "CREATED_DATE")
    Date createdDate;
    @Column(name = "status")
    int status;
    @Column(name = "ISDELETE")
    int isDelete;
}
