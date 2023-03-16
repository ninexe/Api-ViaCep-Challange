package com.api.viacep.models;

import lombok.Data;


@Data
public class EnderecoResponse {
    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Double frete;
}
