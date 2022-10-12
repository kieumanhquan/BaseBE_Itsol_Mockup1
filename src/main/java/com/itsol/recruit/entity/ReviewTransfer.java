package com.itsol.recruit.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="REVIEW_TRANSFERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_TRANSFERS_SEQ")
    @SequenceGenerator(name = "REVIEW_TRANSFERS_SEQ", sequenceName = "REVIEW_TRANSFERS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "ID")
    int id;
    @ManyToOne
    @JoinColumn(name = "TRANSFER_ID")
    Transfer transfer;
    @Column(name = "CREATED_DATE")
    Date createdDate;
    @ManyToOne
    @JoinColumn(name = "DM_ID")
    User user;
    @Column(name = "REASON")
    String reason;
    @Column(name = "STATUS")
    int status;
}
