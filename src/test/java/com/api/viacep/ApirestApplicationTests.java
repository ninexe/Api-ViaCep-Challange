package com.api.viacep;

import com.api.viacep.models.EnderecoResponse;
import com.api.viacep.services.ApiFreteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ApirestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private EnderecoResponse enderecoResponse;

	@InjectMocks
	private ApiFreteServiceImpl apiFreteServiceImpl = new ApiFreteServiceImpl();

	@Test
	void testConsultCep(){
		enderecoResponse = apiFreteServiceImpl.getEnderecoByCep("89201-420", Optional.of("https://viacep.com.br/ws"));

		assert (enderecoResponse.getRua().equals("Rua Visconde de Taunay"));
		assert (enderecoResponse.getComplemento().equals("até 333/334"));
		assert (enderecoResponse.getBairro().equals("Centro"));
		assert (enderecoResponse.getCidade().equals("Joinville"));
		assert (enderecoResponse.getEstado().equals("SC"));
		// Teste unitario validando Body completo
	}
	@Test
	void testConsultCep2(){
		enderecoResponse = apiFreteServiceImpl.getEnderecoByCep("58755-000",Optional.of("https://viacep.com.br/ws"));

		assert (enderecoResponse.getRua().equals(""));
		assert (enderecoResponse.getComplemento().equals(""));
		assert (enderecoResponse.getBairro().equals(""));
		assert (enderecoResponse.getCidade().equals("Princesa Isabel"));
		assert (enderecoResponse.getEstado().equals("PB"));
		// Teste unitario validando Body completo
	}
	@Test
	void testConsultFret(){
		enderecoResponse = apiFreteServiceImpl.getEnderecoByCep("89201-420",Optional.of("https://viacep.com.br/ws"));

		assert (enderecoResponse.getFrete().equals(17.30));
		assert (enderecoResponse.getEstado().equals("SC"));
		// Comparando o frete se está correto de acordo a consulta da api
	}
	@Test
	void testConsultFret2(){
		enderecoResponse = apiFreteServiceImpl.getEnderecoByCep("58755-000",Optional.of("https://viacep.com.br/ws"));

		assert (enderecoResponse.getFrete().equals(15.98));
		assert (enderecoResponse.getEstado().equals("PB"));
		// Comparando o frete se está correto de acordo a consulta da api
	}

}
