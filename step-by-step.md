Fase 1 — Fundação
☐ MongoDB
☐ application.properties
☐ estrutura de packages
☐ User
☐ Role
☐ UserRepository
Fase 2 — Autenticação
☐ PasswordEncoder
☐ JWT
☐ JWT Filter
☐ SecurityConfig
☐ AuthController
☐ Login
☐ autorização por Role
Testar no Postman/Insomnia.
Fase 3 — Estratégias
☐ Strategy
☐ StrategyRepository
☐ StrategyService
☐ StrategyController
☐ CRUD
☐ LIDER → CRUD
☐ demais → GET
Fase 4 — Ideias
☐ Idea
☐ IdeaRepository
☐ IdeaService
☐ IdeaController
☐ CRUD operador
☐ consulta gestor
☐ priorização
☐ aprovação
☐ vínculo com Strategy
Fase 5 — Projetos
☐ Project
☐ ProjectRepository
☐ ProjectService
☐ ProjectController
☐ CRUD gestor
☐ consulta líder
☐ atualização de progresso
☐ resultados
☐ vínculo com Strategy
☐ vínculo com Idea
Fase 6 — Dashboard
☐ DashboardService
☐ métricas
☐ ROI
☐ investimento
☐ retorno
☐ produtividade
☐ redução de custos
☐ projetos por status
☐ projetos por estratégia
Fase 7 — IA
☐ integração Gemini/OpenRouter
☐ análise de ideia
☐ score
☐ justificativa
☐ priorização automática
Fase 8 — Android
Trocar:
Room
Mocks
FakeRepository
por:
Retrofit
↓
Spring API
↓
MongoDB
E manter a arquitetura que vocês já fizeram:
Compose
↓
ViewModel
↓
UseCase
↓
Repository
↓
Retrofit
↓
Spring

POST apiauthlogin

GET apistrategies
POST apistrategies
PUT apistrategies{id}
DELETE apistrategies{id}

GET apiideas
POST apiideas
PUT apiideas{id}
DELETE apiideas{id}
PATCH apiideas{id}approve
PATCH apiideas{id}priority

GET apiprojects
POST apiprojects
PUT apiprojects{id}
DELETE apiprojects{id}

GET apidashboard
