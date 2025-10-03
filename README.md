# API de Locadora de Filmes

## Visão Geral

Esta é uma API RESTful desenvolvida com Java e Spring Boot para gerenciar as operações de uma locadora de filmes. O sistema permite o cadastro de clientes e filmes, além do controle de locações, incluindo a lógica para cálculo de taxas por atraso na devolução. O projeto demonstra uma arquitetura de software robusta, com separação de responsabilidades, tratamento de exceções e validação de dados.

## Status do Projeto

⚠️ **Em Desenvolvimento:** Este projeto ainda está em fase de construção. Funcionalidades adicionais e os endpoints para edição, exclusão e para o gerenciamento de filmes e reservas serão implementados futuramente.

## Funcionalidades Principais

* **Gerenciamento de Clientes:**
    * Cadastro, edição, exclusão e consulta de clientes.
    * Validação e formatação de CPF/CNPJ para garantir a integridade dos dados.
* **Gerenciamento de Filmes:**
    * Cadastro, edição, exclusão e consulta de filmes.
    * Controle de estoque para cada título.
* **Sistema de Locação:**
    * Criação de reservas de filmes para clientes.
    * Finalização de reservas com cálculo automático de taxas por atraso na devolução.
    * Contador de filmes locados por cliente.

## Arquitetura do Projeto

O projeto segue uma arquitetura em camadas, promovendo baixo acoplamento e alta coesão:

* **Controllers:** Responsáveis por expor os endpoints da API, receber as requisições HTTP e retornar as respostas.
* **Services:** Camada onde reside toda a lógica de negócio da aplicação, como validações e cálculos.
* **Repositories:** Camada de acesso a dados que utiliza o Spring Data JPA para abstrair a comunicação com o banco de dados.
* **Entities:** Classes que representam o modelo de domínio e são mapeadas para as tabelas do banco de dados.
* **DTOs (Data Transfer Objects):** Objetos utilizados para transportar dados entre as camadas, garantindo que apenas as informações necessárias sejam expostas nos endpoints.
* **Exceptions:** Classes customizadas para o tratamento de erros específicos da aplicação, permitindo o retorno de respostas HTTP claras e informativas.

## Tecnologias Utilizadas

* **Java 23**
* **Spring Boot 3.4.2**
* **Spring Data JPA:** Para persistência de dados e comunicação com o banco.
* **Maven:** Para o gerenciamento de dependências e do build do projeto.
* **H2 Database:** Banco de dados em memória, utilizado para facilitar o desenvolvimento e os testes.

## Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/igorpintos/locadora-filmes-java-spring-api.git](https://github.com/igorpintos/locadora-filmes-java-spring-api.git)
    ```
2.  **Acesse o diretório do projeto:**
    ```bash
    cd locadora-filmes-java-spring-api
    ```
3.  **Execute o projeto com o Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  A aplicação estará disponível em `http://localhost:8080` e o console do H2 Database em `http://localhost:8080/h2-console`.

## Endpoints da API Implementados

Abaixo estão os principais endpoints disponíveis no momento:

### Clientes
* `GET /locadora/clientes`: Retorna a lista de todos os clientes.
* `GET /locadora/clientes/{idCliente}`: Retorna os detalhes de um cliente específico.
* `POST /locadora/clientes`: Adiciona um novo cliente.
