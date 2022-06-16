package com.company.entity;

import com.company.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Transaction")
public class TransactionEntity extends BaseEntity {

    private String fromCard;
    private String toCard;
    private Long amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String profileId;
}