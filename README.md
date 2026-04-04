# biblioteca-api

API REST para gerenciamento de biblioteca — cadastro de livros, usuários e controle de empréstimos.

## Stack

Java 17 · Spring Boot 3 · Spring Data JPA · Flyway · MySQL 8 · Maven · Lombok

## Estrutura
src/
├── controller/     # Endpoints REST
├── domain/
│   ├── livro/      # Entidade, DTO, Repository
│   ├── usuario/    # Entidade, DTO, Repository
│   └── emprestimo/ # Entidade, Service, Repository
├── service/        # Regras de negócio
└── db/migration/   # Migrations Flyway (V1–V7)

## Endpoints

### Livros
| Método | Rota         | Descrição       |
|--------|--------------|-----------------|
| POST   | /livros      | Cadastrar livro |
| GET    | /livros      | Listar livros   |
| DELETE | /livros/{id} | Remover livro   |

### Usuários
| Método | Rota      | Descrição          |
|--------|-----------|--------------------|
| POST   | /usuarios | Cadastrar usuário  |
| GET    | /usuarios | Listar usuários    |

### Empréstimos
| Método | Rota                       | Descrição                |
|--------|----------------------------|--------------------------|
| POST   | /emprestimos               | Registrar empréstimo     |
| GET    | /emprestimos               | Listar empréstimos       |
| PUT    | /emprestimos/{id}/devolucao | Devolver livro           |

## Regras de negócio

- Usuário precisa estar cadastrado para realizar empréstimo
- Livro precisa estar disponível para ser emprestado
- Usuário não pode ter mais de 5 livros emprestados simultaneamente
- Ao emprestar, livro é marcado como indisponível automaticamente
- Ao devolver, a data de devolução é registrada automaticamente

## Validações

- Campos obrigatórios validados via Bean Validation (`@NotBlank`)
- Erros de validação retornam 400 com mapa de campos e mensagens
- Erros de negócio tratados via `@RestControllerAdvice`

## Como executar

**Pré-requisitos:** Java 17, MySQL 8, Maven
```bash
git clone https://github.com/educiudad/biblioteca-api.git
```

Configure `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

Crie o schema no MySQL antes de subir:
```sql
CREATE DATABASE biblioteca;
```
```bash
mvn spring-boot:run
```

As migrations do Flyway criam as tabelas automaticamente na primeira execução.

## Próximas etapas

- DTOs nas respostas dos endpoints
- Testes unitários no `EmprestimoService`
