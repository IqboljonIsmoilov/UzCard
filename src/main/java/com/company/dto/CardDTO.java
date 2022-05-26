package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardDTO {

    private String id;
    private String name;
    private String number;
    private String phone;
    private Boolean status;
    private LocalDateTime expiredDate;
    private LocalDateTime createDate = LocalDateTime.now();
    private Long balance;

    private String clientId;
    private String profileName;
}
