# Teste Analista de Sistemas
# Building an Operation Rest API with Spring Data JPA Swagger JAX-RS


Esta aplicação tem como funcionalidade receber via http JSON no formato abaixo, onde através do campo cep é alimentada a tabela endereço, a qual é compostas por campos provenientes da API viacep.com.br/ws/{cep}/json/.

POST: http://localhost:8080/api/clientes

```json
{
  "cpf": "11111111111",
  "email": "string",
  "endereco": {
    "cep": "25060040"
  },
  "nome": "string"
}
```

Ao ser consultado o valor deverá constar desta forma:

GET http://localhost:8080/api/clientes/11111111111

```json
{
  "cpf": "11111111111",
  "email": "string",
  "nome": "string",
  "endereco": {
    "logradouro": "Rua Capitão Damasceno",
    "cep": "25060-040",
    "complemento": "de 1000/1001 ao fim",
    "bairro": "Carolina",
    "localidade": "Duque de Caxias",
    "uf": "RJ",
    "ibge": "3301702"
  }
}
```

Nesta aplicação são utilizadas as seguintes tecnologias:
- SpringFramework
- Java 8
- Maven
- Swagger
- JUnit
- JAX-RS
- SLF4J
- MySql
- H2 para banco runtime (é possível a migração para outros SGDBS)

## Configuração de SGDB
Caso deseje utilizar outro banco de dados ao H2 runtime, por gentileza realize as alterações no arquivo application.properties.
O SGDB Mysql esta previamente configurado dependendo apenas dos ajustes nos apontamentos.


## Run Spring Boot 

##### Execute
```
mvn clean install
```

##### Apenas este comando é suficiente para subir a aplicação (servidor/banco h2 runtime)
```
mvn spring-boot:run
```

## Documentação Swagger 
```
http://localhost:8080/swagger-ui.html#
```
##  --cURL

##### POST createCliente:
```
curl -X POST "http://localhost:8080/api/clientes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"cpf\": \"11111111111\", \"email\": \"string\", \"endereco\": { \"cep\": \"25060040\" }, \"nome\": \"string\"}"
```
##### GET getAllClientes:
```
curl -X GET "http://localhost:8080/api/clientes" -H "accept: */*"
```

##### GET getClienteByCPF {cpf}:
```
curl -X GET "http://localhost:8080/api/clientes/11111111111" -H "accept: */*"
```