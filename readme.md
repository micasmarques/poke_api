# PokeAPI - Backend e Frontend

## Visão Geral

Este projeto foi desenvolvido como parte de um desafio técnico para a empresa **ZRP**, utilizando uma arquitetura hexagonal para o backend. Ele consiste em uma aplicação simples que consulta dados de Pokémons através da PokeAPI e exibe essas informações em um frontend. 

O projeto é composto por duas partes:
1. **Backend (API)**: Desenvolvido em **Java** com **Spring Boot**, utilizando a arquitetura hexagonal.
2. **Frontend**: Desenvolvido com **HTML**, **CSS**, **JavaScript**, e **Node.js**.

## Tecnologias Utilizadas

- **Java** (OpenJDK 17) para o backend.
- **Spring Boot** para o framework do backend.
- **Arquitetura Hexagonal** para organizar a lógica de negócios no backend.
- **Maven** como ferramenta de build.
- **Node.js** para o servidor do frontend.
- **Docker** para empacotamento de ambos os componentes (backend e frontend).
- **Express** para servir o frontend via Node.js.
- **Fetch API** para comunicação entre o frontend e o backend.

## Estrutura do Projeto

- **backend/**: Código do backend que segue a arquitetura hexagonal, isolando a lógica de negócios, adaptadores e portas.
- **frontend/**: Código do frontend que consome o backend e exibe as informações dos Pokémons para o usuário.
- **run_project.sh**: Script para construir as imagens Docker e rodar a aplicação.

## Arquitetura Hexagonal

A **Arquitetura Hexagonal** (ou Arquitetura de Portas e Adaptadores) foi adotada no backend para garantir uma separação clara entre o domínio da aplicação (regras de negócio) e os mecanismos externos, como APIs, banco de dados ou interfaces do usuário.

### Camadas:
- **Domínio**: Contém as entidades e regras de negócio (ex.: a classe `Pokemon`).
- **Portas**: Define interfaces para comunicação externa (ex.: interfaces de repositórios).
- **Adaptadores**: Implementam as interfaces das portas e conectam-se a mecanismos externos (ex.: `PokeApiClient` para a PokeAPI).

## Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

- **Docker**: Para construir e rodar os containers.
- **Git**: Para clonar o repositório.

## Como Baixar e Rodar o Projeto

Siga os seguintes passos para baixar e rodar a aplicação localmente usando Docker:

### 1. Clonar o Repositório

Abra um terminal e execute:

```bash
git clone https://github.com/micasmarques/poke_api.git
cd poke_api
```

### 2. Executar o Script

O projeto inclui um script chamado **run_project.sh**, que irá construir as imagens Docker e rodar os containers para o backend e frontend.

No terminal, execute:

```bash
./run_project.sh
```

Este script executa os seguintes comandos:
1. **Constrói** as imagens Docker do backend e frontend.
2. **Roda** os containers e mapeia as portas:
   - Backend: mapeado para a porta **8080**.
   - Frontend: mapeado para a porta **3000**.
3. Exibe a lista de containers em execução.

### 3. Acessar o Projeto

Após rodar o script, o frontend estará acessível no navegador:

- **URL**: [http://localhost:3000/](http://localhost:3000/)

### 4. Finalizar os Containers

Caso queira parar os containers após a execução, rode o comando:

```bash
docker stop backend-container frontend-container
```

## Considerações Finais

Este projeto foi projetado para ser simples e modular, facilitando tanto a manutenção quanto a extensão de funcionalidades. A integração entre o frontend e o backend foi feita via API REST com CORS devidamente configurado para rodar localmente.

Sinta-se à vontade para explorar e fazer melhorias!