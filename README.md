# FH.SistemaUnicoSaude.API
Projeto Hackathon Sistema Único de Saúde API do Curso de Pós-Graduação Lato Sensu Arquitetura e Desenvolvimento em JAVA da Faculdade de Informática e Administração Paulista (FIAP)

## Arquitetura do Sistema

Este sistema foi desenvolvido utilizando **Java 21**, **Spring Boot 3.4.4** e **PostgreSQL 17.4**.

## Configuração e Execução

### Pré-requisitos

*   Docker e Docker Compose instalados.
*   Java 21 (para desenvolvimento local sem Docker).

### Executando com Docker Compose

Para iniciar a aplicação e o banco de dados, execute o seguinte comando na raiz do projeto:

```bash
docker-compose up -d
```

A API estará disponível em `http://localhost:8085`.
O banco de dados PostgreSQL estará acessível na porta `5453`.
O PGAdmin estará disponível na porta `80`.

### Variáveis de Ambiente

As configurações de ambiente são gerenciadas através de um arquivo `.env` (com conteúdo igual ao .env.example incluído no repositório).

## Acesso ao Banco de Dados

### Acessando o banco pelo terminal
```shell
docker exec -it fh_sistema_unico_saude_database psql -U postgres # entrar no container do banco
\l # listar todos os bancos
\c fh_sistema_unico_saude_database # se conectar no banco
\dt # listar as tabelas
SELECT * FROM t_question; # ver o conteúdo delas
```

## Usuários de Teste

O sistema é inicializado com os seguintes usuários para teste (definidos em `src/main/resources/runready/user.json`):

| Nome | Login | Senha | Perfil |
|---|---|---|---|
| Admin | `admin` | `admin` | ADMIN |
| Patient 1 | `patient1` | `patient1` | PATIENT |
| Health Professional 1 | `healthprofessional1` | `healthprofessional1` | HEALTH_PROFESSIONAL |

## Documentação da API

A documentação completa dos endpoints da API pode ser acessada através do Swagger UI:

[http://localhost:8084/fh-sistema-unico-saude/swagger-ui/index.html](http://localhost:8084/fh-sistema-unico-saude/swagger-ui/index.html)

*(Nota: A porta pode variar dependendo da configuração local. No docker-compose padrão a porta mapeada é 8085.)*

## Troubleshooting

Caso precise resetar completamente o ambiente (apagar bancos de dados, recriar tabelas do zero, baixar dependências novamente), utilize os comandos abaixo:
```shell
chmod +x docker-clean.sh
./docker-clean.sh
```
