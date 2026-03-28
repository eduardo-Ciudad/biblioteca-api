# biblioteca-api

API REST para gerenciamento de biblioteca — cadastro de livros,
usuários e controle de empréstimos.

## Stack

Java 17 · Spring Boot · Spring Data JPA ·
Flyway · MySQL · Maven · Lombok

## Estrutura

src/
├── controller/   # Endpoints REST
├── domain/
│   ├── livro/    # Entidade, DTO, Repository
│   ├── usuario/  # Entidade, DTO, Repository
│   └── emprestimo/ # Entidade, Repository
└── db/migration/ # Migrations Flyway (V1, V2, V3)

## Endpoints

### Livros
| Método | Rota     | Descrição        |
|--------|----------|------------------|
| POST   | /livros  | Cadastrar livro  |

### Usuários
| Método | Rota      | Descrição           |
|--------|-----------|---------------------|
| POST   | /usuarios | Cadastrar usuário   |

### Empréstimos
| Método | Rota         | Descrição              |
|--------|--------------|------------------------|
| POST   | /emprestimos | Registrar empréstimo   |
| GET    | /emprestimos | Listar empréstimos     |

## Como executar

**Pré-requisitos:** Java 17, MySQL, Maven
```bash
git clone https://github.com/educiudad/biblioteca-api.git
```

Configure `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```
```bash
mvn spring-boot:run
```

## Próximas etapas

- Bean Validation nos endpoints
- Tratamento global de erros (`@RestControllerAdvice`)
- Paginação com `Pageable`
- Spring Security