package com.api.viacep.services;

import com.api.viacep.models.EnderecoResponse;

public interface ApiFreteService {
    EnderecoResponse getEnderecoByCep(String cep);
}
