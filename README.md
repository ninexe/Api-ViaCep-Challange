# Consulta Endereço via CEP

O desafio é construir uma API REST de consulta de endereço e cálculo de frete para um determinado CEP. O contrato da API deve ser conforme especificado abaixo:
### ![image](https://user-images.githubusercontent.com/61746866/223610790-8a8c1f24-0b57-4ee6-868f-086fd998ad2d.png)

Para a busca do endereço do CEP, você deve consultar a API VIA CEP, conforme a documentação https://viacep.com.br/.
O valor do frete é fixo de acordo com as regiões
do Brasil: Sudeste (R$ 7,85), Centro-Oeste (R$ 12,50), Nordeste (R$ 15,98), Sul (R$17,30) e Norte (R$ 20,83). O CEP é obrigatório e pode ser passado com ou sem máscara
na entrada e se o CEP não for encontrado uma mensagem informativa deve ser retornada para o cliente.

### Requisitos para o desenvolvimento:
+ Java 11
+ Spring boot
+ API REST Template
+ Documentação Swagger
+ Testes unitários JUnit5
+ Testes automatizados utilizando cucumber
