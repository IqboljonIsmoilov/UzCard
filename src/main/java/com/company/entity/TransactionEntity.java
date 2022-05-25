package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "fromCard")
    private String fromCard;

    @Column(name = "toCard")
    private String toCard;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
