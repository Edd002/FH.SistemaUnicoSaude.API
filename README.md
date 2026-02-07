# FH.SistemaUnicoSaude.API
Projeto Hackathon Sistema Único de Saúde API do Curso de Pós-Graduação Lato Sensu Arquitetura e Desenvolvimento em JAVA da Faculdade de Informática e Administração Paulista (FIAP)

## Arquitetura do Sistema

Este sistema foi desenvolvido utilizando **Java 21**, **Spring Boot 3.4.4** e **PostgreSQL 17.4**.

### Pré-requisitos

*   Docker e Docker Compose instalados.
*   Java 21 (para desenvolvimento local sem Docker).

### Tecnologias Utilizadas
- **Java 21** e **Spring Boot 3.4.4** para a criação da aplicação web
- **Docker** e **Docker Compose** para execução e gerenciamento de ambientes
- **PostgreSQL** como banco de dados, garantindo confiabilidade e desempenho
- **Flyway** para gerenciamento de migrações do banco de dados
- **H2 Database Engine** como banco de dados para ambiente de testes automatizados
- **Swagger OpenAPI Specification** como documentação interativa da API

### Estrutura Arquitetural
A arquitetura do sistema segue uma abordagem baseada em **Domínios** e **Clean Architecture**, promovendo a separação de responsabilidades e facilitando a escalabilidade. Os principais componentes dentro de cada domínio incluem:

- **Config**: Configuração de dependências necessárias para o levantamento e funcionamento do container de aplicação.
- **Controller**: Gerencia requisições HTTP e as direciona para os serviços apropriados.
- **ServiceGateway**: Coordena acessos sistêmicos como arquivos de configuração, repositórios e orquestra use cases.
- **MapperPresenter**: Prepara os dados para retorno ao cliente.
- **Repository**: Interface para acesso e manipulação de dados armazenados no banco de dados.
- **Entity**: Representação das tabelas do banco de dados como classes Java (ORM) e core de domínio.
- **UseCases**: Implementações de regras de negócio para cada caso de uso de cada domínio.

### Benefícios da Arquitetura
Essa estrutura modular possibilita:
- Desenvolvimento mais organizado
- Manutenção facilitada
- Maior flexibilidade para futuras expansões

## Configuração e Execução

### Variáveis de Ambiente

As configurações de ambiente são gerenciadas através de um arquivo `.env` (com conteúdo igual ao .env.example incluído no repositório).

### Executando com Docker Compose

Para iniciar a aplicação e o banco de dados, na raiz do projeto, crie (ou altere) um arquivo .env com suas configurações (PS.: Utilize como base o arquivo .env.example), em seguida execute o seguinte comando:

```bash
docker compose --profile docker up --build
```

A API estará disponível em `http://localhost:8085`.
O banco de dados PostgreSQL estará acessível na porta `5453`.
O PGAdmin estará disponível na porta `80`.

**Observação:** Na primeira execução da aplicação, devido à pré carga de registros na base de dados, levará alguns minutos para finalizar.

## Documentação da API

A documentação completa dos endpoints da API pode ser acessada através do Swagger UI uma vez a aplicação em execução:

[Documentação Swagger](http://localhost:8085/fh-sistema-unico-saude/swagger-ui/index.html)

*(Nota: A porta pode variar dependendo da configuração local. No docker-compose padrão a porta mapeada é 8085.)*

Segue também a documentação via Postman:

[Documentação Postman](https://documenter.getpostman.com/view/43787842/2sBXVig9sS)

## Usuários de Teste

O sistema é inicializado com os seguintes usuários para teste (definidos em `src/main/resources/runready/user.json`):

| Nome | Login | Senha | Perfil |
|---|---|---|---|
| Admin | `admin` | `admin` | ADMIN |
| Patient 1 | `patient1` | `patient1` | PATIENT |
| Health Professional 1 | `healthprofessional1` | `healthprofessional1` | HEALTH_PROFESSIONAL |

## Acesso ao Banco de Dados

### Acessando o banco pelo terminal
```shell
docker exec -it fh_sistema_unico_saude_database psql -U postgres # entrar no container do banco
\l # listar todos os bancos
\c fh_sistema_unico_saude_database # se conectar no banco
\dt # listar as tabelas
SELECT * FROM t_question; # ver o conteúdo delas
```

### Como acessar o banco de dados em memória H2 de testes automatizados via console?
Em contexto de desenvolvimento, enquanto os testes automatizados estiverem em execução ou em pausa (thread breakpoint) é possível acessar a estrutura do banco de dados enquanto está em memória em http://localhost:8085/restaurant-manager/h2-console com as credenciais:

Driver Class: org.h2.Driver<br>
JDBC URL: jdbc:h2:tcp://localhost:9093/mem:db<br>
User Name: sa<br>
Password:<br>

O breakpoint pode ser configurado para suspender apenas uma única thread para que o acesso ao H2-console seja possível (https://hrrbrt.medium.com/using-h2-during-test-debugging-in-spring-f6a3db355e3a).

## Troubleshooting

Caso precise resetar completamente o ambiente (apagar bancos de dados, recriar tabelas do zero, baixar dependências novamente), utilize os comandos abaixo:
```shell
chmod +x docker-clean.sh
./docker-clean.sh
```
