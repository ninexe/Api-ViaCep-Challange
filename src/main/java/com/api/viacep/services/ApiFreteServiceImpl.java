package com.api.viacep.services;

import com.api.viacep.models.EnderecoApiFreteModel;
import com.api.viacep.models.EnderecoResponse;
import com.api.viacep.utils.CepUtils;
import com.api.viacep.utils.UfUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ApiFreteServiceImpl implements ApiFreteService {
    @Value("${api.frete.url}")
    private String apiUrl;

    @ExceptionHandler(value = { Exception.class })
    public EnderecoResponse getEnderecoByCep(final String cep, Optional<String> paramOpc) {
        if (!CepUtils.isCep(cep)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cep vazio ou inválido!");
        }

        if(!(paramOpc.get() == "")) {
            apiUrl = paramOpc.get();
        }

        RestTemplate restTemplate = new RestTemplate();
        EnderecoApiFreteModel enderecoApiFreteModel = restTemplate.getForObject(String.format("%s/%s/json/", apiUrl, cep), EnderecoApiFreteModel.class);

        if (enderecoApiFreteModel.getCep() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cep não encontrado!");
        }

        EnderecoResponse enderecoResponse = convertToEnderecoResponse(enderecoApiFreteModel);
        enderecoResponse.setFrete(UfUtils.getFrete(enderecoApiFreteModel.getUf()));
        return enderecoResponse;
    }

    private EnderecoResponse convertToEnderecoResponse(final EnderecoApiFreteModel from){
        EnderecoResponse to = new EnderecoResponse();

        to.setCep(from.getCep());
        to.setRua(from.getLogradouro());
        to.setComplemento(from.getComplemento());
        to.setBairro(from.getBairro());
        to.setCidade(from.getLocalidade());
        to.setEstado(from.getUf());

        return to;
    }
}
