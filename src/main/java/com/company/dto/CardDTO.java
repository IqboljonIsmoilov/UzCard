package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardDTO {

    private String id;
    private String number;
    private String phone;
    private Long balance;
    private LocalDateTime UpdateDate;
}
