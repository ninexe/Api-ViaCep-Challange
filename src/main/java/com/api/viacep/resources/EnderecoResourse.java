package com.api.viacep.resources;

import com.api.viacep.models.CepRequest;
import com.api.viacep.models.EnderecoResponse;
import com.api.viacep.services.ApiFreteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = "/api")
@RestController
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class EnderecoResourse {
    private final ApiFreteService apiFreteService;

    @Autowired
    public EnderecoResourse(final ApiFreteService apiFreteService) {
        this.apiFreteService = apiFreteService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cep consultado com sucesso!"),
            @ApiResponse(code = 400, message = "Cep vazio ou inválido!"),
            @ApiResponse(code = 404, message = "Cep não encontrado!"),
    })
    @ApiOperation(value = "Consulta CEP")
    @PostMapping("/v1/consulta-endereco")
    public ResponseEntity<EnderecoResponse> consultarCepPost(@RequestBody CepRequest request) {
        EnderecoResponse response = apiFreteService.getEnderecoByCep(request.getCep(), Optional.of(""));
        return ResponseEntity.ok(response);
    }
}
