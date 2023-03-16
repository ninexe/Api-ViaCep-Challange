package com.api.viacep;

import com.api.viacep.models.EnderecoApiFreteModel;
import com.api.viacep.models.EnderecoResponse;
import com.api.viacep.utils.UfUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CepSteps {

    @Autowired
    private TestRestTemplate restTemplate;
    private String cep;
    private ResponseEntity<EnderecoResponse> response;
    private ResponseEntity<EnderecoApiFreteModel> responseApi;
    private ResponseEntity<String> responseString;
    @Given("um CEP válido")
    public void um_cep_valido() {
        cep = "89201420";
    }
    @Given("um CEP inválido")
    public void um_cep_invalido() {
        cep = "123456789";
    }
    @When("o cliente realiza a consulta desse CEP")
    public void o_cliente_realiza_a_consulta_desse_cep() {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        response = restTemplate.getForEntity(url, EnderecoResponse.class);
        responseApi = restTemplate.getForEntity(url, EnderecoApiFreteModel.class);
        // aqui é a consulta feita pelo cliente
    }
    @When("o cliente realiza a consulta desse CEP inválido")
    public void o_cliente_realiza_a_consulta_desse_cep_invalido() {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        responseString = restTemplate.getForEntity(url, String.class);
        // aqui é a consulta invalida feita pelo cliente
    }
    @When("informo a UF {string}")
    public void informoAUF(String uf) {
        String ufBody = responseApi.getBody().getUf();
        assertEquals(uf, ufBody);
        // aqui você pode verificar se o valor retornado pela api é o mesmo solicitado pelo teste
    }
    @Then("as informações do endereço correspondente ao CEP são retornadas")
    public void as_informacoes_do_endereco_correspondente_ao_cep_sao_retornadas() {
        assertEquals(200, responseApi.getStatusCodeValue());
        // aqui você pode verificar as informações do endereço retornadas pela API
    }
    @Then("o valor do frete deve ser {double}")
    public void oValorDoFreteDeveSer(Double valorEsperado) {
        response.getBody().setFrete(UfUtils.getFrete(responseApi.getBody().getUf()));
        assertEquals(valorEsperado, response.getBody().getFrete());
        // aqui você pode verificar a comparação de Frete se está correta
    }
    @Then("é retornado um erro informando que o CEP é inválido")
    public void e_retornado_um_erro_informando_que_o_cep_e_invalido() {
        assertEquals(400, responseString.getStatusCodeValue());
        // aqui você pode verificar a mensagem de erro retornada pela API
    }
}
