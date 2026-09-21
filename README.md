# Weagle — Documentação dos Endpoints

## 1. Visão geral

A API do Weagle AI utiliza:

- **Spring Boot**
- **Spring Security**
- **JWT** para autenticação
- **MongoDB** para persistência
- **REST API**

### Base URL

```text
http://localhost:8080
```

### Autenticação

Os endpoints protegidos utilizam:

```http
Authorization: Bearer {token}
```

O token é obtido através do endpoint de login:

```http
POST /api/auth/login
```

### Roles

A aplicação possui três níveis de acesso:

| Role | Descrição |
|---|---|
| `OPERATOR` | Criação e gerenciamento das próprias ideias |
| `MANAGER` | Gerenciamento de projetos e aprovação/priorização de ideias |
| `LEADER` | Consulta de projetos e atualização de progresso/resultados |

---

# 2. Autenticação

## POST `/api/auth/login`

Realiza a autenticação do usuário e retorna um JWT.

### Autenticação

Não requer autenticação.

### Request

```json
{
  "email": "anna@weagle.com",
  "password": "123456"
}
```

### Response — `200 OK`

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "id": "68c...",
  "name": "Anna",
  "email": "anna@weagle.com",
  "role": "OPERATOR"
}
```

### Exemplo de usuários de desenvolvimento

```text
anna@weagle.com
Senha: 123456
Role: OPERATOR
```

```text
manager@weagle.com
Senha: 123456
Role: MANAGER
```

```text
leader@weagle.com
Senha: 123456
Role: LEADER
```

> Os usuários acima são utilizados apenas para desenvolvimento e testes.

---

# 3. Strategies

Base:

```text
/api/strategies
```

## GET `/api/strategies`

Retorna todas as estratégias cadastradas.

### Autenticação

Requer usuário autenticado.

### Roles

```text
OPERATOR
MANAGER
LEADER
```

### Response — `200 OK`

```json
[
  {
    "id": "68c123...",
    "name": "Transformação Digital",
    "description": "Acelerar a transformação digital da organização.",
    "active": true,
    "createdAt": "2026-09-20T10:00:00"
  }
]
```

---

## GET `/api/strategies/{id}`

Retorna uma estratégia específica.

### Path Parameter

| Parâmetro | Tipo | Descrição |
|---|---|---|
| `id` | String | ID da estratégia |

### Autenticação

Requer usuário autenticado.

### Response — `200 OK`

```json
{
  "id": "68c123...",
  "name": "Transformação Digital",
  "description": "Acelerar a transformação digital da organização.",
  "active": true,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

## POST `/api/strategies`

Cria uma nova estratégia.

### Role

```text
LEADER
```

### Request

```json
{
  "name": "Transformação Digital",
  "description": "Acelerar a transformação digital da organização.",
  "active": true
}
```

### Response — `201 Created`

```json
{
  "id": "68c123...",
  "name": "Transformação Digital",
  "description": "Acelerar a transformação digital da organização.",
  "active": true,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

## PUT `/api/strategies/{id}`

Atualiza uma estratégia existente.

### Role

```text
LEADER
```

### Request

```json
{
  "name": "Transformação Digital 2.0",
  "description": "Nova descrição da estratégia.",
  "active": true
}
```

### Response — `200 OK`

```json
{
  "id": "68c123...",
  "name": "Transformação Digital 2.0",
  "description": "Nova descrição da estratégia.",
  "active": true,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

## DELETE `/api/strategies/{id}`

Exclui uma estratégia.

### Role

```text
LEADER
```

### Response

```text
204 No Content
```

---

# 4. Ideas

Base:

```text
/api/ideas
```

## GET `/api/ideas`

Retorna todas as ideias cadastradas.

### Autenticação

Requer usuário autenticado.

### Response — `200 OK`

```json
[
  {
    "id": "68c456...",
    "title": "Melhorar comunicação interna",
    "description": "Criar uma ferramenta para facilitar a comunicação.",
    "createdBy": "anna@weagle.com",
    "approved": false,
    "highPriority": false,
    "createdAt": "2026-09-20T10:00:00"
  }
]
```

---

## GET `/api/ideas/{id}`

Retorna uma ideia específica.

### Path Parameter

| Parâmetro | Tipo |
|---|---|
| `id` | String |

### Autenticação

Requer usuário autenticado.

### Response — `200 OK`

```json
{
  "id": "68c456...",
  "title": "Melhorar comunicação interna",
  "description": "Criar uma ferramenta para facilitar a comunicação.",
  "createdBy": "anna@weagle.com",
  "approved": false,
  "highPriority": false,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

## POST `/api/ideas`

Cria uma nova ideia.

### Role

```text
OPERATOR
```

### Request

```json
{
  "title": "Melhorar comunicação interna",
  "description": "Criar uma ferramenta para facilitar a comunicação."
}
```

### Response — `201 Created`

```json
{
  "id": "68c456...",
  "title": "Melhorar comunicação interna",
  "description": "Criar uma ferramenta para facilitar a comunicação.",
  "createdBy": "anna@weagle.com",
  "approved": false,
  "highPriority": false,
  "createdAt": "2026-09-20T10:00:00"
}
```

Os campos abaixo são definidos pelo backend:

```text
createdBy
approved
highPriority
createdAt
```

---

## PUT `/api/ideas/{id}`

Atualiza uma ideia.

### Role

```text
OPERATOR
```

O operador pode atualizar apenas suas próprias ideias.

### Request

```json
{
  "title": "Novo título",
  "description": "Nova descrição da ideia."
}
```

### Response — `200 OK`

```json
{
  "id": "68c456...",
  "title": "Novo título",
  "description": "Nova descrição da ideia.",
  "createdBy": "anna@weagle.com",
  "approved": false,
  "highPriority": false,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

## DELETE `/api/ideas/{id}`

Exclui uma ideia.

### Role

```text
OPERATOR
```

O operador pode excluir apenas suas próprias ideias.

### Response

```text
204 No Content
```

---

## PATCH `/api/ideas/{id}/approve`

Aprova uma ideia.

### Role

```text
MANAGER
```

### Request

Não possui body.

### Response — `200 OK`

```json
{
  "id": "68c456...",
  "title": "Melhorar comunicação interna",
  "description": "Criar uma ferramenta para facilitar a comunicação.",
  "createdBy": "anna@weagle.com",
  "approved": true,
  "highPriority": false,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

## PATCH `/api/ideas/{id}/priority`

Altera a prioridade de uma ideia.

### Role

```text
MANAGER
```

### Query Parameter

| Parâmetro | Tipo | Valores |
|---|---|---|
| `highPriority` | boolean | `true` / `false` |

### Exemplo

```http
PATCH /api/ideas/68c456.../priority?highPriority=true
```

### Response — `200 OK`

```json
{
  "id": "68c456...",
  "title": "Melhorar comunicação interna",
  "description": "Criar uma ferramenta para facilitar a comunicação.",
  "createdBy": "anna@weagle.com",
  "approved": true,
  "highPriority": true,
  "createdAt": "2026-09-20T10:00:00"
}
```

---

# 5. Projects

Base:

```text
/api/projects
```

## GET `/api/projects`

Retorna todos os projetos.

### Autenticação

Requer usuário autenticado.

### Roles

```text
OPERATOR
MANAGER
LEADER
```

### Response — `200 OK`

```json
[
  {
    "id": "68c789...",
    "name": "Projeto de Inovação",
    "description": "Projeto voltado à melhoria de processos.",
    "strategyId": "68c123...",
    "ideaId": "68c456...",
    "progress": 25,
    "results": "Resultados iniciais positivos.",
    "createdAt": "2026-09-20T10:00:00",
    "updatedAt": "2026-09-20T10:00:00"
  }
]
```

---

## GET `/api/projects/{id}`

Retorna um projeto específico.

### Autenticação

Requer usuário autenticado.

### Response — `200 OK`

```json
{
  "id": "68c789...",
  "name": "Projeto de Inovação",
  "description": "Projeto voltado à melhoria de processos.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 25,
  "results": "Resultados iniciais positivos.",
  "createdAt": "2026-09-20T10:00:00",
  "updatedAt": "2026-09-20T10:00:00"
}
```

---

## POST `/api/projects`

Cria um projeto.

### Role

```text
MANAGER
```

### Request

```json
{
  "name": "Projeto de Inovação",
  "description": "Projeto voltado à melhoria de processos.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 0,
  "results": ""
}
```

### Response — `201 Created`

```json
{
  "id": "68c789...",
  "name": "Projeto de Inovação",
  "description": "Projeto voltado à melhoria de processos.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 0,
  "results": "",
  "createdAt": "2026-09-20T10:00:00",
  "updatedAt": "2026-09-20T10:00:00"
}
```

---

## PUT `/api/projects/{id}`

Atualiza um projeto.

### Role

```text
MANAGER
```

### Request

```json
{
  "name": "Projeto de Inovação Atualizado",
  "description": "Nova descrição.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 25,
  "results": "Primeiros resultados."
}
```

### Response — `200 OK`

```json
{
  "id": "68c789...",
  "name": "Projeto de Inovação Atualizado",
  "description": "Nova descrição.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 25,
  "results": "Primeiros resultados.",
  "createdAt": "2026-09-20T10:00:00",
  "updatedAt": "2026-09-20T11:00:00"
}
```

---

## DELETE `/api/projects/{id}`

Exclui um projeto.

### Role

```text
MANAGER
```

### Response

```text
204 No Content
```

---

## PATCH `/api/projects/{id}/progress`

Atualiza o progresso de um projeto.

### Role

```text
LEADER
```

### Query Parameter

| Parâmetro | Tipo | Valores |
|---|---|---|
| `progress` | int | `0` a `100` |

### Exemplo

```http
PATCH /api/projects/68c789.../progress?progress=50
```

### Response — `200 OK`

```json
{
  "id": "68c789...",
  "name": "Projeto de Inovação",
  "description": "Projeto voltado à melhoria de processos.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 50,
  "results": "",
  "createdAt": "2026-09-20T10:00:00",
  "updatedAt": "2026-09-20T12:00:00"
}
```

O backend aceita apenas valores entre:

```text
0 e 100
```

---

## PATCH `/api/projects/{id}/results`

Atualiza os resultados de um projeto.

### Role

```text
LEADER
```

### Request

O endpoint recebe o conteúdo dos resultados no body.

Exemplo:

```text
Projeto apresentou redução de custos e melhoria no tempo de execução do processo.
```

### Response — `200 OK`

```json
{
  "id": "68c789...",
  "name": "Projeto de Inovação",
  "description": "Projeto voltado à melhoria de processos.",
  "strategyId": "68c123...",
  "ideaId": "68c456...",
  "progress": 50,
  "results": "Projeto apresentou redução de custos e melhoria no tempo de execução do processo.",
  "createdAt": "2026-09-20T10:00:00",
  "updatedAt": "2026-09-20T12:30:00"
}
```

---

# 6. Matriz de permissões

| Endpoint | OPERATOR | MANAGER | LEADER |
|---|:---:|:---:|:---:|
| `POST /api/auth/login` | ✅ | ✅ | ✅ |
| `GET /api/strategies` | ✅ | ✅ | ✅ |
| `GET /api/strategies/{id}` | ✅ | ✅ | ✅ |
| `POST /api/strategies` | ❌ | ❌ | ✅ |
| `PUT /api/strategies/{id}` | ❌ | ❌ | ✅ |
| `DELETE /api/strategies/{id}` | ❌ | ❌ | ✅ |
| `GET /api/ideas` | ✅ | ✅ | ✅ |
| `GET /api/ideas/{id}` | ✅ | ✅ | ✅ |
| `POST /api/ideas` | ✅ | ❌ | ❌ |
| `PUT /api/ideas/{id}` | ✅* | ❌ | ❌ |
| `DELETE /api/ideas/{id}` | ✅* | ❌ | ❌ |
| `PATCH /api/ideas/{id}/approve` | ❌ | ✅ | ❌ |
| `PATCH /api/ideas/{id}/priority` | ❌ | ✅ | ❌ |
| `GET /api/projects` | ✅ | ✅ | ✅ |
| `GET /api/projects/{id}` | ✅ | ✅ | ✅ |
| `POST /api/projects` | ❌ | ✅ | ❌ |
| `PUT /api/projects/{id}` | ❌ | ✅ | ❌ |
| `DELETE /api/projects/{id}` | ❌ | ✅ | ❌ |
| `PATCH /api/projects/{id}/progress` | ❌ | ❌ | ✅ |
| `PATCH /api/projects/{id}/results` | ❌ | ❌ | ✅ |

`*` Para `PUT` e `DELETE` de Ideas, o `OPERATOR` deve ser o criador da ideia.

---

# 7. Resumo dos recursos

```text
/api/auth
└── login

/api/strategies
├── GET
├── GET /{id}
├── POST
├── PUT /{id}
└── DELETE /{id}

/api/ideas
├── GET
├── GET /{id}
├── POST
├── PUT /{id}
├── DELETE /{id}
├── PATCH /{id}/approve
└── PATCH /{id}/priority

/api/projects
├── GET
├── GET /{id}
├── POST
├── PUT /{id}
├── DELETE /{id}
├── PATCH /{id}/progress
└── PATCH /{id}/results
```

# 8. Status HTTP utilizados

| Status | Significado |
|---|---|
| `200 OK` | Operação realizada com sucesso |
| `201 Created` | Recurso criado com sucesso |
| `204 No Content` | Recurso excluído com sucesso |
| `403 Forbidden` | Usuário autenticado não possui permissão |
| `500 Internal Server Error` | Erro não tratado pela aplicação |

> O tratamento específico de erros `400`, `404` e `500` poderá ser centralizado posteriormente através de um `@RestControllerAdvice`.
