# Sistema de Gestão para Pizzaria

Projeto realizado para integrar diferentes processos de uma pizzaria, realizando atividades como: cadastrar produtos, gerir pedidos e gerenciar clientes.

Este projeto é uma aplicação Java 17 desenvolvida com o framework Spring e baseada na arquitetura de Domain-Driven Design (DDD). A aplicação implementa APIs RESTful e inclui documentação automática via Swagger.

## Tecnologias Utilizadas

- **Java 17**: Versão de linguagem utilizada.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para interação com o banco de dados.
- **Swagger**: Para documentação e testes das APIs.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes (pode ser substituído por outros bancos de dados em ambientes de produção).

## Como Rodar o Projeto

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/joaopereirajunior/tech-challange-fiap-pizzaria.git
   cd tech-challange-fiap-pizzaria
   ```

2. **Configure o ambiente**:
   Certifique-se de ter o [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) e o [Maven](https://maven.apache.org/) instalados.

3. **Compile o projeto**:

   ```bash
   mvn clean install
   ```

4. **Execute a aplicação**:

   ```bash
   mvn spring-boot:run
   ```

   A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

## Documentação da API

A documentação da API é gerada automaticamente pelo Swagger. Você pode acessá-la no seguinte endereço após iniciar a aplicação:

- [Swagger UI](http://localhost:8080/swagger-ui.html)

## Endpoints Disponíveis

Consulte a documentação do Swagger UI para ver todos os endpoints disponíveis e detalhes sobre cada um deles.

## Configuração do Banco de Dados

Por padrão, o projeto utiliza o banco de dados H2.