package com.company.dto.response;

import com.company.dto.request.ClientRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponseDTO extends ClientRequestDTO {

    private String id;
    private String status;
    private String profileName;
    private LocalDateTime createdDate;
}
