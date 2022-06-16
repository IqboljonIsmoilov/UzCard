package com.company.dto.change;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangePhoneDTO {

    @NotNull
    private String phone;
}