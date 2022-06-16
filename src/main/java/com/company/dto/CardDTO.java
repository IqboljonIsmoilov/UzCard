package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CardDTO {
    private String id;
    private String number;
    private LocalDateTime expiredDate;
    private String status;
    private LocalDateTime createDate;
    private Long balance;
    private String clientId;
    private String clientPhone;
    //  ,,,phone,,,, Client, profile_name
}