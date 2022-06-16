package com.company.dto.responce;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CardResponseDTO {

    private String id;
    private String number;
    private Long balance;
    private String phone;
    private LocalDate expiredDate;
    private String name;
    private String surname;
    private String ProfileUserName;
    private LocalDate createDate;
}