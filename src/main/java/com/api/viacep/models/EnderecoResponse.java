package com.api.viacep.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnderecoResponse extends EnderecoApiFreteModel {
    private Double frete;
}
