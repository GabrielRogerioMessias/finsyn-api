# 💸 API de Gerenciamento Financeiro Pessoal - em DESENVOLVIMENTO

API desenvolvida com **Java 17** e **Spring Boot**, utilizando **padrão de arquitetura hexagonal**, persistência com **MySQL**, controle de versão de banco via **Flyway**, e autenticação segura com **Spring Security + JWT**. Documentação interativa com **Swagger (OpenAPI)**.

---
### 🚧 Status do Projeto

> Esta aplicação está atualmente em **desenvolvimento ativo**.

- A branch principal de desenvolvimento é: **`desenvolvimento`**
- Utiliza a estratégia de versionamento baseada em **Git Flow**
- A branch `main` é reservada para versões estáveis e releases de produção
- Commits e features são organizados em branches específicas (`feature/`, `release/`, `hotfix/`)

---

## 🧠 Visão Geral

A aplicação permite ao usuário:

- Registrar e autenticar sua conta com Spring Security e JWT
- Cadastrar, editar e visualizar **categorias**
- Realizar o **CRUD de transações** (Tipos: Receitas e Despesas)
- Criar e gerenciar **metas financeiras**
- Realizar depósitos e visualizar o **progresso das metas**
- Consultar saldo atual e **listar despesas**

---

## 🔧 Tecnologias e Ferramentas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- Flyway (migrações de banco)
- MySQL
- Swagger/OpenAPI
- Maven
- JUnit (pendente)
- Arquitetura Hexagonal

---

## 🧩 Estrutura de Projeto (Hexagonal)

```
com.messias.finsyn
├── adapters
│   ├── inbounds         # Entradas (controllers, dtos, mapeadores, exceptions)
│   └── outbounds        # Saídas (entities-jpa, repositories, mapeadores)
├── application
│   ├── usecases         # Casos de uso (portas de entrada)
│   ├── services         # Implementações dos casos de uso
│   └── exceptions       # Exceptions específicas da aplicação
├── domain
│   ├── models           # Modelos da regra de negócio - core da aplicação
│   └── ports.out        # Portas de saída
├── infrastructure
│   ├── config           # Configurações gerais (Swagger, CORS etc.)
│   └── security         # JWT, filtros, autenticação
└── resources
    ├── db.migration     # Scripts do Flyway
    └── application.properties
```

---

## 📄 Documentação da API

A documentação está disponível via Swagger em:

```
http://localhost:8080/swagger-ui.html
```

Você pode testar os endpoints diretamente por lá, inclusive autenticar com o token JWT.

---

## 🔐 Autenticação

A autenticação é baseada em **JWT**:

1. Registre-se em `/auth/register`
2. Faça login em `/auth/login` e copie o token
3. Use `Bearer <TOKEN>` nos headers das requisições autenticadas (pode ser feito pelo Swagger)

---

## ✅ Funcionalidades Implementadas

### Usuário
- [x] Registro e login
- [x] Atualização de dados

### Categorias
- [x] CRUD de categorias

### Transações
- [x] CRUD (Receita e Despesa)
- [x] Listagem de transações
- [ ] Filtro por tipo e data com paginação (pendente)

### Metas Financeiras
- [x] CRUD de metas
- [x] Registro de depósitos
- [x] Listagem e progresso da meta
- [x] Consulta de depósitos
- [ ] Saque de valor guardado em uma meta financeira (pendente)

### Outros
- [x] Consulta de saldo
- [x] Listagem de despesas
- [x] Documentação via Swagger
- [x] Versionamento do banco com Flyway
- [ ] Testes unitários (pendente)
- [ ] Validação de e-mail no cadastro (pendente)
- [ ] Recuperação de senha com validação de e-mail (pendente)

---

## 📝 Banco de Dados com Flyway

As migrações estão localizadas em:

```
src/main/resources/db/migration
```

Nomeie os arquivos conforme o padrão: `V1__criar_tabela_usuario.sql`, `V2__adicionar_coluna_saldo.sql`, etc.

---

## 🧪 Testes

Testes unitários e de integração ainda **não foram implementados**, mas a estrutura do projeto facilita a separação e mock de dependências para testes isolados nos `usecases`.

Futuramente será utilizada a stack:

- JUnit 5
- Mockito

---

## 🚀 Como Executar

### Pré-requisitos
- Java 17
- MySQL
- Maven

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/GabrielRogerioMessias/finsyn-api.git
   cd finsyn-api
   ```

2. Configure o banco em `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/finsyn
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

3. Execute com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse o Swagger:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 📬 Contato

- **Desenvolvedor:** Gabriel Messias  
- **Email:** gamessiasjt@gmail.com

---
