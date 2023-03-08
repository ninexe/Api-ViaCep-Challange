package com.api.viacep.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class EnderecoApiFreteModel implements Serializable {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
