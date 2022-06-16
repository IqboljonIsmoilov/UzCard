package com.company.dto.request;

import com.company.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionRequestDTO extends BaseEntity {


    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDAte;
    private String fromCard;
    private String toCard;
    private Long amount;
    private String profileId;
}