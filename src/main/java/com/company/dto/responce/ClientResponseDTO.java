package com.company.dto.responce;

import com.company.dto.request.ClientRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponseDTO extends ClientRequestDTO {

    private String id;
    private LocalDateTime createdDate;
}