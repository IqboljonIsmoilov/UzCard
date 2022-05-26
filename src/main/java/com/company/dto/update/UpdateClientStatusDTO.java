package com.company.dto.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateClientStatusDTO {

    @NotNull(message = "to enter the Old Value !")
    private String oldValue;

    @NotNull(message = "to enter the New Value !")
    private String newValue;
}
