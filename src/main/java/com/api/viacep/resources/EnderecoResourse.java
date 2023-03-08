package com.api.viacep.resources;

import com.api.viacep.services.ApiFreteService;
import com.api.viacep.models.CepRequest;
import com.api.viacep.models.EnderecoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "Consulta CEP")
    @PostMapping("/v1/consulta-endereco")
    public ResponseEntity<EnderecoResponse> consultarCep(@RequestBody CepRequest request) {
        EnderecoResponse response = apiFreteService.getEnderecoByCep(request.getCep());
        return ResponseEntity.ok(response);
    }
}
