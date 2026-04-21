# 📚 Biblioteca API

API REST para gerenciamento de biblioteca com autenticação JWT e controle de acesso por perfil — cadastro de livros e usuários, controle de empréstimos e devoluções com regras de negócio aplicadas na camada de serviço.

🔗 **Deploy:** [biblioteca-api-nqwi.onrender.com](https://biblioteca-api-nqwi.onrender.com)
🖥️ **Frontend:** [frontend-biblioteca-api](https://github.com/educiudad/frontend-biblioteca-api)
 
---

## 🚀 Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3 | Framework web |
| Spring Security | 6 | Autenticação e autorização |
| Spring Data JPA | — | Persistência |
| Flyway | — | Migrações de banco |
| PostgreSQL | 17 | Banco de dados |
| Maven | — | Gerenciamento de dependências |
| Lombok | — | Redução de boilerplate |
| JJWT | 0.11.5 | Geração e validação de tokens JWT |
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
├── security          # Filtro JWT, configuração do Spring Security
└── exception         # Tratamento global de erros (@RestControllerAdvice)
```
 
---

## 🔐 Autenticação e Autorização

A API utiliza autenticação stateless com **JWT (JSON Web Token)**. O token deve ser enviado no header de cada requisição protegida:

```
Authorization: Bearer <token>
```

### Perfis de acesso

| Perfil | Permissões |
|---|---|
| `ADMIN` | Cadastrar/remover livros, remover usuários, todas as operações |
| `USER` | Realizar empréstimos, visualizar livros e seus próprios empréstimos |

### Fluxo de autenticação

```
POST /auth/login → retorna JWT
```

O token tem validade de **1 hora**. Após expirar, é necessário autenticar novamente.
 
---

## 🔌 Endpoints

### 🔑 Autenticação

| Método | Endpoint | Autenticação | Descrição |
|---|---|---|---|
| `POST` | `/auth/login` | Pública | Autenticar e obter token JWT |

### 📘 Livros

| Método | Endpoint | Perfil | Descrição |
|---|---|---|---|
| `POST` | `/livros` | ADMIN | Cadastrar livro |
| `GET` | `/livros` | Autenticado | Listar livros |
| `GET` | `/livros/{id}` | Autenticado | Buscar livro por ID |
| `DELETE` | `/livros/{id}` | ADMIN | Remover livro |

### 👤 Usuários

| Método | Endpoint | Perfil | Descrição |
|---|---|---|---|
| `POST` | `/usuarios` | Pública | Cadastrar usuário |
| `GET` | `/usuarios` | Autenticado | Listar usuários |
| `GET` | `/usuarios/{id}` | Autenticado | Buscar usuário por ID |
| `DELETE` | `/usuarios/{id}` | ADMIN | Remover usuário |

### 🔄 Empréstimos

| Método | Endpoint | Perfil | Descrição |
|---|---|---|---|
| `POST` | `/emprestimos` | Autenticado | Registrar empréstimo |
| `GET` | `/emprestimos` | Autenticado | Listar empréstimos |
| `PUT` | `/emprestimos/{id}/devolucao` | Autenticado | Registrar devolução |
| `DELETE` | `/emprestimos/{id}` | Autenticado | Remover empréstimo |
 
---

## 🧠 Regras de Negócio

- Usuário deve estar cadastrado para realizar empréstimos
- Livro deve estar disponível no momento do empréstimo
- Limite de **5 empréstimos simultâneos por usuário**
- Livro fica marcado como indisponível ao ser emprestado e volta a ficar disponível após devolução
- Data de devolução é registrada automaticamente
- Apenas usuários com perfil `ADMIN` podem cadastrar e remover livros
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
- PostgreSQL
- Maven
### 1. Clonar o repositório

```bash
git clone https://github.com/eduardo-Ciudad/biblioteca-api.git
cd biblioteca-api
```

### 2. Configurar o banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE biblioteca;
```

### 3. Configurar as credenciais

Em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Executar

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

> As migrações do banco são executadas automaticamente pelo Flyway na inicialização, incluindo a criação do usuário administrador padrão.

### 5. Login inicial

Após subir a aplicação, autentique com o admin padrão criado via migration:

```json
POST /auth/login
{
  "email": "admin@biblioteca.com",
  "senha": "admin123"
}
```
 
---

## 👨‍💻 Autor

**Eduardo** — [@educiudad](https://github.com/educiudad)
 