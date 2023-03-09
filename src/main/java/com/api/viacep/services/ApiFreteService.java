package com.api.viacep.services;

import com.api.viacep.models.EnderecoResponse;

import java.util.Optional;

public interface ApiFreteService {
    EnderecoResponse getEnderecoByCep(String cep, Optional<String> paramOpc);
}
