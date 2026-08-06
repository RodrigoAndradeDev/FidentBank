<div align="center">

# 🏦 FidentBank - Core Banking System API

> **API RESTful de alta performance e segurança projetada para simular o núcleo (core) de um banco digital moderno.**

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)

</div>

---

## 📋 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Diferenciais de Engenharia & FinTech](#-diferenciais-de-engenharia--fintech)
- [Arquitetura da Aplicação](#-arquitetura-da-aplicação)
- [Stack Tecnológica](#-stack-tecnológica)
- [Tabela de Endpoints (API)](#-tabela-de-endpoints-api)
- [Mecanismo de Segurança e Tratamento de Erros](#-mecanismo-de-segurança-e-tratamento-de-erros)
- [Como Executar o Projeto](#-como-executar-o-projeto)
- [Autor & Contato](#-autor--contato)

---

## 🎯 Sobre o Projeto

O **FidentBank** foi desenvolvido para resolver problemas reais de ecossistemas financeiros distribuídos: **consistência de dados**, **prevenção contra ataques de concorrência (Race Conditions)** e **alta disponibilidade**. 

A aplicação gerencia todo o ciclo de vida bancário, desde a abertura e autenticação segura de contas até a execução de transferências, saques e depósitos com garantia absoluta de auditoria e validações rigorosas.

---

## ⚡ Diferenciais de Engenharia & FinTech

* 💎 **Precisão Monetária Absoluta:** Todos os valores financeiros utilizam `BigDecimal` para evitar erros de arredondamento de ponto flutuante (`double`/`float`).
* 🔒 **Controle de Concorrência (Pessimistic Locking):** Utilização de `@Lock(LockModeType.PESSIMISTIC_WRITE)` em transações de saldo, impedindo o problema de *Double Spending* (gasto duplo) em requisições simultâneas.
* 🛡️ **Propriedades ACID Garantidas:** Operações bancárias envoltas em `@Transactional`, garantindo *rollback* automático em caso de falha durante a transferência entre contas.
* 🗄️ **Versionamento de Banco de Dados:** Evolução controlada do schema do PostgreSQL através de *migrations* automatizadas com **Flyway**.
* 🛂 **Tratamento Global de Exceções:** Interceptação centralizada de falhas via `@RestControllerAdvice`, padronizando retornos HTTP e ocultando *stack traces* sensíveis.

---

## 🏗️ Arquitetura da Aplicação

O projeto adota o padrão de **Arquitetura em Camadas (Layered Architecture)** alinhado aos princípios **SOLID** e **Clean Code**:

```text
src/main/java/dev/rodrigo/fidentbank
│
├── 📂 controller       # Endpoints REST e validação inicial de entrada (@Valid)
├── 📂 dto              # Data Transfer Objects (Request/Response desacoplados da entidade)
├── 📂 service          # Regras de negócio, cálculos financeiros e controle transacional
├── 📂 repository       # Comunicação com o banco (Spring Data JPA + Pessimistic Locks)
├── 📂 model            # Entidades de domínio JPA (User, Account, Transaction)
├── 📂 security         # Filtros JWT, Password Encoding e Autorização do Spring Security
└── 📂 exception        # Handler global (@RestControllerAdvice) e DTOs de erro padronizados
```

---

## 🛠️ Stack Tecnológica

| Categoria | Tecnologia / Ferramenta |
| --- | --- |
| **Linguagem** | Java 17 (LTS) |
| **Framework Base** | Spring Boot 3.x |
| **Segurança & Auth** | Spring Security + JWT (JSON Web Tokens) |
| **Persistência** | Spring Data JPA / Hibernate |
| **Banco de Dados** | PostgreSQL |
| **Migrations** | Flyway |
| **Containerização** | Docker & Docker Compose |
| **Documentação/Testes** | Postman / Swagger UI / Bean Validation |
| **Produtividade** | Lombok |

---

## 📍 Tabela de Endpoints (API)

### 🔐 Autenticação & Usuários

| Método | Endpoint | Descrição | Requer Auth? |
| --- | --- | --- | --- |
| `POST` | `/api/v1/auth/register` | Cadastro de novo correntista | ❌ Não |
| `POST` | `/api/v1/auth/login` | Autenticação e geração de Token JWT | ❌ Não |

### 💳 Operações Bancárias

| Método | Endpoint | Descrição | Requer Auth? |
| --- | --- | --- | --- |
| `GET` | `/api/v1/accounts/me` | Consulta saldo e detalhes da conta logada | 🔑 Sim |
| `POST` | `/api/v1/transactions/deposit` | Realiza depósito na conta | 🔑 Sim |
| `POST` | `/api/v1/transactions/withdraw` | Realiza saque na conta | 🔑 Sim |
| `POST` | `/api/v1/transactions/transfer` | Realiza transferência via PIX/TED | 🔑 Sim |
| `GET` | `/api/v1/transactions/statement` | Extrato histórico de transações | 🔑 Sim |

---

## 🛡️ Mecanismo de Segurança e Tratamento de Erros

A API possui duas camadas de defesa globais contra dados corrompidos ou maliciosos:

1. **Portão de Tipo e Leitura (`HttpMessageNotReadableException`):** Intercepta dados malformados (ex: texto em campo numérico ou datas inválidas) devolvendo `400 Bad Request`.
2. **Portão de Regras de Negócio (`MethodArgumentNotValidException`):** Intercepta falhas de anotações `@Valid` (`@CPF`, `@Email`, `@NotNull`), retornando um JSON limpo com a lista exata dos campos que falharam.

#### Exemplo de Resposta de Erro de Validação (`400 Bad Request`):

```json
[
  {
    "campo": "cpf",
    "mensagem": "O CPF deve conter exatamente 11 dígitos numéricos"
  },
  {
    "campo": "email",
    "mensagem": "Formato de e-mail inválido"
  }
]

```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* **Git** instalado
* **Java 17+** configurado
* **Docker & Docker Compose** ativos

### 1. Clonar o Repositório

```bash
git clone [https://github.com/RodrigoAndradeDev/FidentBank.git](https://github.com/RodrigoAndradeDev/FidentBank.git)
cd FidentBank

```

### 2. Subir a Infraestrutura com Docker

Suba o container do PostgreSQL pré-configurado:

```bash
docker-compose up -d

```

### 3. Executar a Aplicação Spring Boot

```bash
./mvnw spring-boot:run

```

A aplicação estará acessível em `http://localhost:8080`.

---

## 👨‍💻 Autor & Contato

Desenvolvido por **Rodrigo Andrade** *Estudante de Engenharia de Software na UCSAL | Desenvolvedor Java Full Stack*

* **GitHub:** [@RodrigoAndradeDev](https://github.com/RodrigoAndradeDev)
* **LinkedIn:** [Rodrigo Andrade]([https://www.google.com/search?q=https://www.linkedin.com/in/](https://www.linkedin.com/in/rodrigoadev/))
* **E-mail:** [rodrigo.anddev@gmail.com](https://www.google.com/search?q=mailto%3Arodrigo.anddev%40gmail.com)

