# 📚 Biblioteca API

API REST para gerenciamento de biblioteca com integração full stack — cadastro de livros e usuários, controle de empréstimos e devoluções com regras de negócio aplicadas na camada de serviço.

🔗 **Deploy:** [biblioteca-api-nqwi.onrender.com](https://biblioteca-api-nqwi.onrender.com)
🖥️ **Frontend:** [frontend-biblioteca-api](https://github.com/educiudad/frontend-biblioteca-api)

---

## 🚀 Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3 | Framework web |
| Spring Data JPA | — | Persistência |
| Flyway | — | Migrações de banco |
| MySQL | 8 | Banco de dados |
| Maven | — | Gerenciamento de dependências |
| Lombok | — | Redução de boilerplate |
| springdoc-openapi | 2.8.9 | Documentação Swagger |
| JUnit 5 + Mockito | — | Testes unitários |

---

## 🧱 Arquitetura

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

```
src/main/java/com/eduardo/biblioteca
├── controller        # Endpoints REST (@RestController)
├── domain            # Entidades JPA e repositórios
├── dto               # DTOs de entrada e saída (Java Records)
├── service           # Regras de negócio
└── exception         # Tratamento global de erros (@RestControllerAdvice)
```

---

## 🔌 Endpoints

### 📘 Livros

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/livros` | Cadastrar livro |
| `GET` | `/livros` | Listar livros |
| `GET` | `/livros/{id}` | Buscar livro por ID |
| `DELETE` | `/livros/{id}` | Remover livro |

### 👤 Usuários

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/usuarios` | Cadastrar usuário |
| `GET` | `/usuarios` | Listar usuários |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID |
| `DELETE` | `/usuarios/{id}` | Remover usuário |

### 🔄 Empréstimos

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/emprestimos` | Registrar empréstimo |
| `GET` | `/emprestimos` | Listar empréstimos |
| `PUT` | `/emprestimos/{id}/devolucao` | Registrar devolução |
| `DELETE` | `/emprestimos/{id}` | Remover empréstimo |

---

## 🧠 Regras de Negócio

- Usuário deve estar cadastrado para realizar empréstimos
- Livro deve estar disponível no momento do empréstimo
- Limite de **5 empréstimos simultâneos por usuário**
- Livro fica marcado como indisponível ao ser emprestado e volta a ficar disponível após devolução
- Data de devolução é registrada automaticamente
- Violações de regra retornam `HTTP 422 Unprocessable Entity`

---

## 🔄 DTOs

A API utiliza DTOs para desacoplar a camada de transporte das entidades JPA, implementados como **Java Records**:

- **Input DTO** — valida e recebe dados de entrada (Bean Validation: `@NotBlank`, `@NotNull`, etc.)
- **Output DTO** — controla o que é exposto nas respostas da API

---

## 🧪 Testes

Testes unitários cobrindo as regras de negócio da camada de serviço:

- **Framework:** JUnit 5 + Mockito
- **Cobertura:** `EmprestimoService` — validação de disponibilidade, limite de empréstimos, devolução

```bash
mvn test
```

---

## 📄 Documentação

A documentação interativa está disponível via Swagger UI após subir a aplicação:

```
http://localhost:8080/swagger-ui.html
```

---

## ⚙️ Como Executar Localmente

### Pré-requisitos

- Java 17
- MySQL 8
- Maven

### 1. Clonar o repositório

```bash
git clone https://github.com/eduardo-Ciudad/biblioteca-api.git
cd biblioteca-api
```

### 2. Configurar o banco de dados

Crie o banco no MySQL:

```sql
CREATE DATABASE biblioteca;
```

### 3. Configurar as credenciais

Em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Executar

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

> As migrações do banco são executadas automaticamente pelo Flyway na inicialização.

---

## 👨‍💻 Autor

**Eduardo** — [@educiudad](https://github.com/educiudad)