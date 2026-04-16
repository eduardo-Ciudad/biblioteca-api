# 📚 Biblioteca API

API REST para gerenciamento de biblioteca com integração full stack, permitindo cadastro de livros e usuários, além do controle completo de empréstimos e devoluções.

---

## 🚀 Tecnologias

* Java 17
* Spring Boot 3
* Spring Data JPA
* Flyway
* MySQL 8
* Maven
* Lombok

---

## 🧱 Arquitetura

O projeto segue arquitetura em camadas com separação de responsabilidades:

```text
src/main/java/com/eduardo/biblioteca
├── controller        # Endpoints REST
├── domain            # Entidades e repositórios
├── dto               # DTOs (input/output)
├── service           # Regras de negócio
├── exception         # Tratamento global de erros
```

---

## 🔌 Endpoints

### 📘 Livros

| Método | Endpoint       | Descrição           |
| ------ | -------------- | ------------------- |
| POST   | `/livros`      | Cadastrar livro     |
| GET    | `/livros`      | Listar livros       |
| GET    | `/livros/{id}` | Buscar livro por ID |
| DELETE | `/livros/{id}` | Remover livro       |

### 👤 Usuários

| Método | Endpoint         | Descrição             |
| ------ | ---------------- | --------------------- |
| POST   | `/usuarios`      | Cadastrar usuário     |
| GET    | `/usuarios`      | Listar usuários       |
| GET    | `/usuarios/{id}` | Buscar usuário por ID |
| DELETE | `/usuarios/{id}` | Remover usuário       |

### 🔄 Empréstimos

| Método | Endpoint                      | Descrição            |
| ------ | ----------------------------- | -------------------- |
| POST   | `/emprestimos`                | Registrar empréstimo |
| GET    | `/emprestimos`                | Listar empréstimos   |
| PUT    | `/emprestimos/{id}/devolucao` | Registrar devolução  |
| DELETE | `/emprestimos/{id}`           | Remover empréstimo   |

---

## 🧠 Regras de Negócio

* Usuário deve estar cadastrado para realizar empréstimos
* Livro deve estar disponível
* Limite de **5 empréstimos simultâneos por usuário**
* Livro fica indisponível ao ser emprestado
* Devolução registra automaticamente a data

---

## 🔄 DTOs

A API utiliza DTOs para desacoplamento entre camadas:

* Input DTO → entrada de dados
* Output DTO → saída controlada da API

Benefícios:

* Segurança (não expõe entidades)
* Flexibilidade
* Organização

---

## 🌐 Integração Frontend

A API é consumida por um frontend desenvolvido em **HTML, CSS e JavaScript puro**, utilizando `fetch` para comunicação HTTP.

* Comunicação via JSON
* Tratamento de erros no frontend
* Integração completa com endpoints REST

---

## 🧪 Testes

* JUnit 5 + Mockito
* Testes de regras de negócio do serviço de empréstimos

---

## ⚙️ Como executar

### Pré-requisitos

* Java 17
* MySQL 8
* Maven

---

### 1. Clonar

```bash
git clone https://github.com/eduardo-Ciudad/biblioteca-api.git
```

---

### 2. Banco

```sql
CREATE DATABASE biblioteca;
```

---

### 3. Configurar

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

---

### 4. Rodar

```bash
mvn spring-boot:run
```

---

## 📌 Próximos Passos

* Deploy da API (Railway / Render)
* Deploy do frontend (Netlify / GitHub Pages)
* Melhorias visuais (UI/UX)

---

## 📄 Licença

Projeto para fins educacionais e portfólio.
