package com.itsol.recruit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
//    @Id
//    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
//    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 1)
//    Long id;
//
//    @Column(name = "full_name")
//    String fullName;
//
//    @Column(name = "email")
//    String email;
//
//    @Column(name = "user_name")
//    String userName;
//
//    @Column(name = "password")
//    String password;
//
//    @Column(name = "phone_number")
//    String phoneNumber;
//
//    @Column(name = "home_town")
//    String homeTown;
//
//    @Column(name = "cccd")
//    String cccd;
//
//    @Column(name = "avatar")
//    String avatarName;
//
//    @Column(name = "gender")
//    String gender;
//
//    @Column(name = "salary")
//    Double salary;
//
//    @Column(name = "school")
//    String school;
//
//    @Column(name = "position")
//    String position;
//
//    @JsonFormat(pattern = "YYYY-MM-dd")
//    @Column(name = "birth_day")
//    Date birthDay;
//
//
//    @Column(name = "is_delete")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    boolean isDelete;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "permisstion",
//            joinColumns = @JoinColumn(name = "USER_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
//    )
//    private Set<Role> roles = new HashSet<>();
//
//    @Column(name = "is_Active")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    private boolean isActive;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "Unit_ID")
//    private Unit unit;
//
//    @Column(name = "level")
//    private String level;
//
//    @Column(name = "is_Leader")
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    private boolean isLeader;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @Column(name = "fullname")
    String fullName;

    @Column(name = "email")
    String email;

    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "cccd")
    String cccd;

    //trình độ học vấn
    @Column(name = "literacy")
    String literacy;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "home_town")
    String homeTown;

    @Column(name = "avatar")
    String avatarName;

    @Column(name = "salary")
    long salary;

    @Column(name = "gender")
    String gender;

    @Column(name = "position")
    String position;

    @Column(name = "is_leader")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isLeader;

    @Column(name = "birth_day")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date birthDay;

    @Column(name = "is_delete")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean isDelete;

    @Column(name = "IS_DM")
    int isDm;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    Unit unit;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERMISSTION",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "active")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;


}