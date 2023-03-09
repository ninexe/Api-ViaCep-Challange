Feature: Consulta de CEP

  Scenario: Consultar CEP válido
    Given um CEP válido
    When o cliente realiza a consulta desse CEP
    Then as informações do endereço correspondente ao CEP são retornadas

  Scenario: Consultar CEP inválido
    Given um CEP inválido
    When o cliente realiza a consulta desse CEP inválido
    Then é retornado um erro informando que o CEP é inválido

  Scenario: Calculando o frete para uma UF
    Given um CEP válido
    When o cliente realiza a consulta desse CEP
    When informo a UF "SC"
    Then o valor do frete deve ser 17.30
