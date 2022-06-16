package com.company.dto;

import com.company.enums.Status;

import java.time.LocalDateTime;

public class CardFilterDTO {

    private String clientId;
    private String CardNumber;
    private String cardId;
    private Long fromAmount;
    private Long toAmount;
    private String profile_name;
    private Status status;
    private LocalDateTime created_date;
}