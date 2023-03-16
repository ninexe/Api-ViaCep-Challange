package com.api.viacep.services;

import com.api.viacep.models.EnderecoApiFreteModel;
import com.api.viacep.utils.CepUtils;
import com.api.viacep.utils.UfUtils;
import com.api.viacep.models.EnderecoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ApiFreteServiceImpl implements ApiFreteService {
    @Value("${api.frete.url}")
    private String apiUrl;

    public EnderecoResponse getEnderecoByCep(final String cep, Optional<String> paramOpc) {
        if (!CepUtils.isCep(cep)){
            throw new IllegalArgumentException("Cep Inválido!");
        }

        if(!(paramOpc.get() == "")) {
            apiUrl = paramOpc.get();
        }

        RestTemplate restTemplate = new RestTemplate();
        EnderecoApiFreteModel enderecoApiFreteModel = restTemplate.getForObject(String.format("%s/%s/json/", apiUrl, cep), EnderecoApiFreteModel.class);

        if (enderecoApiFreteModel.getCep() == null){
            throw new IllegalArgumentException("Cep não encontrado");
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
