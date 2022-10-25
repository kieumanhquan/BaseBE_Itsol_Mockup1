package com.itsol.recruit.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank
    @Column(name = "TRANSFER_NAME")
    String transferName;

    @ManyToOne
    @JoinColumn (name = "EMPLOYEE_ID")
    User employee;

    @Column(name = "REASON_TRANSFER")
    String reasonTransfer;
    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    User creator;
    @ManyToOne
    @JoinColumn(name = "UNIT_OLD")
    Unit unitOld;
    @ManyToOne
    @JoinColumn(name = "UNIT_NEW")
    Unit unitNew;
    @ManyToOne
    @JoinColumn(name = "ADMIN_REVIEW")
    User admin;

    @Column(name = "CREATED_DATE")
    Date createdDate;
    @Column(name = "TRANSFER_DATE")
    Date transferDate;
    @Column(name = "CANCLE_DATE")
    String cancleDay;
    @Column(name = "SUCCESS_DATE")
    Date successDate;
    @Column(name = "ADMIN_REVIEW_DATE")
    Date adminReviewDate;
    @Column(name = "IS_STATUS_OLD")
    int isStatusOld;
    @Column(name = "IS_STATUS_NEW")
    int isStatusNew;
    @Column(name = "status")
    int status;
    @Column(name = "IS_DELETE")
    int isDelete;
}
