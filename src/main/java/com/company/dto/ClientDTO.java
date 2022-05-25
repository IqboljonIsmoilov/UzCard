package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDTO {

    private String id;
    private String name;
    private String surname;
    private String middleName;
    private String photo;
    private String status;
    private LocalDateTime createdDate;
}
