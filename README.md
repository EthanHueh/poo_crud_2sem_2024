# Projeto CRUD de Construções em JAVA

Este é um projeto desenvolvido para a disciplina de Programação Orientada a Objetos (POO). O objetivo do projeto é criar uma aplicação CRUD (Create, Read, Update, Delete) para gerenciar informações relacionadas a construções (como edifícios, casas, pontes, etc.). O projeto utiliza Java como linguagem de programação e PostgreSQL como sistema de gerenciamento de banco de dados.

## Funcionalidades

A aplicação permite realizar as seguintes operações:

1. **Cadastrar** - Adicionar uma nova construção ao sistema.
2. **Consultar todos** - Consultar todas as construções cadastradas no sistema.
3. **Consultar por ID** - Consultar uma construção específica a partir do ID
4. **Atualizar** - Editar informações de uma construção existente.
5. **Deletar** - Remover uma construção do sistema.

Cada construção possui os seguintes atributos:

- **ID**: Identificador único da construção.
- **Nome**: Nome da construção.
- **Endereço**: Localização específica da construção.
- **Tipo**: Tipo de construção (ex.: Edifício, Casa, Ponte).
- **Data de Início**: Data de início da construção.
- **Data de Previsão de Término**: Data estimada para finalização da construção.
- **Área Total (m²)**: Área total da construção em metros quadrados.
- **Orçamento Total**: Orçamento estimado para a construção.
- **Nome do Responsável**: Nome do responsável pela construção.
- **Status**: Estado atual da construção (ex.: Em andamento, Concluída, Pendente).

## Pré-requisitos

Para executar o projeto, você precisará de:

- **Java 8** ou superior
- **PostgreSQL** instalado e configurado

## Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL chamado `banco_poo_prj1`.
2. Execute o seguinte comando SQL para criar a tabela necessária:

```sql
CREATE TABLE construcoes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    endereco VARCHAR(60) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    data_inicio DATE NOT NULL,
    data_previsao_termino DATE NOT NULL,
    area_total_m2 NUMERIC(6,0) NOT NULL,
    orcamento_total NUMERIC(15,2) NOT NULL,
    nome_responsavel VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL
);
```
3. Atualize o metodo **getConexao** da classe **ConexaoFactory.java** com as credenciais do seu banco de dados:
```
// Exemplo de configuração
String URL = "jdbc:postgresql://localhost:5432/banco_poo_prj1";
String USER = "seu usuario";
String PASSWORD = "sua senha";
```

## Vídeo de apresentação
Foi elaborado um video de apresentação com duração de 8 minutos para o professor de POO, os arquivos da edição do vídeo estão presentes neste repositório.
Clique **[aqui](https://www.youtube.com/watch?v=RdHQh8YDeDM)** para assistir ao vídeo.

![image](https://github.com/user-attachments/assets/c90433a3-88cb-478e-a060-3eca2e8e37fb)

