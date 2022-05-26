package com.company.dto.response;

import com.company.dto.request.CardRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardResponseDTO extends CardRequestDTO {

    private String id;
    private String number;
    private String phone;
    private Boolean status;
    private LocalDateTime expiredDate;
    private LocalDateTime createDate = LocalDateTime.now();
    private Long balance;

    private String clientId;
    private String profileName;
}
