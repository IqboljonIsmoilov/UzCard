package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    private String id;
    private String fromCard;
    private String toCard;
    private Long amount;

    private LocalDateTime createDate;
    private Boolean status;

    private String profileId;
}
