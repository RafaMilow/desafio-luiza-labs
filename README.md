# desafio-luiza-labs
Esta documentação descreve a estrutura da API de lista de produtos favoritos para o desafios técnico da Luiza Labs.

## Instalação do Ambiente

Para Instalação correta do ambiente, utilizaremos o docker. Nele teremos duas instâncias responsaveis pela execução da API, um
container para o MYSQL e outro para o TOMCAT que rodará a aplicação.

A estrutura de diretório da instalação do ambiente contem o segguinte formato:

- mariadb (banco de dados utilizado)
-- Dockerfile (arquivo responsavel pela constução da imagem do banco de dados)
-- setup.sql (arquivo responsavel pela criação dos dados iniciais do BD)
- tomcat (Servidor da aplicação)
-- Dockerfile (arquivo responsavel pela constução da imagem do servidor da aplicação)
- docker-compose.yml (arquivo responsavel pela orquestração da construção e instalação do ambiente)

### Comandos para a Instalação do ambiente

Você deverá entrar no diretório raiz em que se encontra o docker-compose.yml e executar o seguinte comando:
`docker-compose up -d`

Ele irá construir as imagens do banco de dados e do servidor de aplicação seguindo os passos:
- Criar imagem do banco de dados;
- Fazer a criação da tabelas do aplicação seguindo o que esta contido no mariadb/setup.sql;
- Criar a imagem do servidor de aplicação tomcat;
- Fazer checkout deste repositório com o código da aplicação;
- Executar o maven para compilação e montagem do war da aplicação;
- Copiar o artefato para o tomcat;
- Inicializar o tomcat;

Ao terminar teremos:
Creating BFX-TOMCAT  ... done
Creating BFX-MARIADB ... done

Assim teremos os containeres criados e prontos para utilização. Se quiser parar a qualquer momento, basta executar:
`docker-compose down`

#### Para acompanhar os logs da aplicação:

Para que você possa acompanhar os logs da aplicação teremos que logar no container do tomcat:

Rafaels-MacBook-Pro:LuizaLabs RafaMilow$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
`ca89e51a1e24`        app_tomcat          "catalina.sh run"        10 seconds ago      Up 8 seconds        0.0.0.0:8081->8080/tcp   BFX-TOMCAT
2accaaffe4fd        mariadb-mysql       "docker-entrypoint.s…"   10 seconds ago      Up 7 seconds        0.0.0.0:3306->3306/tcp   BFX-MARIADB
Rafaels-MacBook-Pro:LuizaLabs RafaMilow$ docker exec -t -i `ca89e51a1e24` /bin/bash

Depois que conseguir entrar no container ir para o diretório:
`cd /usr/local/tomcat/logs`

Lá teremos um arquivo com o nome: Webapp-desafio-Luizalabs.log


## Produtos

Os endpoints de listagem e detalhe possuem produtos com a mesma estrutura, sendo que esta é composta por:

- `price`: preço do produto
- `image`: URL da imagem do produto
- `brand`: marca do produto
- `id`: id do produto
- `title`: nome do produto
- `reviewScore`: média dos reviews para este produto

### Listagem

`<PAGINA>` representa o número da página requisitada, iniciando em `1`.

URL: `http://challenge-api.luizalabs.com/api/product/?page=<PAGINA>`

### Detalhe

`<ID>` representa o `id` do produto, a ser coletado no endpoint de listagem.

URL: `http://challenge-api.luizalabs.com/api/product/<ID>/`
