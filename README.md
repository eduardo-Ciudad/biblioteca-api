# biblioteca-api
O sistema permite cadastrar usuários, livros e controlar empréstimos, aplicando regras de negócio e relacionamento entre entidades.

🚀 Tecnologias utilizadas
Java 17
Spring Boot
Spring Data JPA
Flyway (migração de banco)
MySQL
Maven
Lombok
📂 Estrutura do projeto

O projeto segue uma arquitetura em camadas:

domain → entidades do sistema (Livro, Usuario, Emprestimo)
repository → interfaces JPA para acesso ao banco
service (se aplicável) → regras de negócio
controller → endpoints da API
config → configurações gerais
⚙️ Funcionalidades
📘 Livros
Cadastrar livro
Listar livros
Atualizar livro
Deletar livro
👤 Usuários
Cadastrar usuário
Listar usuários
🔄 Empréstimos
Registrar empréstimo de livro
Relacionamento entre usuário e livro
Controle de empréstimos via banco de dados
🗄️ Banco de dados

O banco de dados é gerenciado com Flyway, garantindo versionamento das tabelas.

Principais tabelas:

livros
usuarios
emprestimos

Relacionamentos:

Um usuário pode ter vários empréstimos
Um livro pode estar em vários empréstimos
▶️ Como executar o projeto
Pré-requisitos
Java 17 instalado
MySQL rodando
Maven
Passos
Clone o repositório:
git clone https://github.com/seu-usuario/biblioteca-api.git
Configure o banco no application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
Execute o projeto:
mvn spring-boot:run
🔌 Endpoints (exemplo)
Livros
GET /livros
POST /livros
Usuários
GET /usuarios
POST /usuarios
Empréstimos
POST /emprestimos
📌 Próximas melhorias
Implementar validações (Bean Validation)
Tratamento de exceções global
Paginação de resultados
Autenticação (Spring Security)
Deploy da API
👨‍💻 Autor

Desenvolvido por Eduardo durante sua jornada de aprendizado em backend Java.
