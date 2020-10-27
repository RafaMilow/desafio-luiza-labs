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

O servidor da aplicação (TOMCAT) terá a porta 8081 exposta na máquina host, ou seja, você poderá verificar se o tomcat está correndo normalmente apontando o ser browser para:
http://localhost:8081/

O MySQL (mariadb), tambem esta sendo executado com a porta 3306 exposta para o seu host, você pode usar seu client preferido e conectar
no banco com os parametros abaixo:

server: localhost
porta: 3306
user: root
senha: milowrlz

Assim teremos os containeres criados e prontos para utilização. Se quiser parar a qualquer momento, basta executar:
`docker-compose down`

#### Para acompanhar os logs da aplicação:

Para que você possa acompanhar os logs da aplicação teremos que logar no container do tomcat:

```
Rafaels-MacBook-Pro:LuizaLabs RafaMilow$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
ca89e51a1e24        app_tomcat          "catalina.sh run"        10 seconds ago      Up 8 seconds        0.0.0.0:8081->8080/tcp   BFX-TOMCAT
2accaaffe4fd        mariadb-mysql       "docker-entrypoint.s…"   10 seconds ago      Up 7 seconds        0.0.0.0:3306->3306/tcp   BFX-MARIADB
Rafaels-MacBook-Pro:LuizaLabs RafaMilow$ docker exec -t -i ca89e51a1e24 /bin/bash
```

Depois que conseguir entrar no container ir para o diretório:
`cd /usr/local/tomcat/logs`

Lá teremos um arquivo com o nome: `Webapp-desafio-Luizalabs.log` com algumas informaçãoes geradas ao invocarmos os serviços da aplicação.

## A aplicação desafio-luiza-labs.war

A aplicação possui dois pontos em que podemos configurar a conexão com o banco de dados e a URL para que seja chamado a API de produtos da Luiza Labs:

- mysql.properties:
mysql.driver=com.mysql.jdbc.Driver
mysql.url=jdbc:mysql://mysqlsrv:3306/luizalabs
mysql.username=root
mysql.password=milowrlz

- Constants.Java:
```java
package com.br.luizalabs.api.constants;

public class Constants {

	public final static Integer EMPTY_LIST_SIZE = 0;
	public final static String PRODUCT_API_URL = "https://5f3589525b91f60016ca4ee6.mockapi.io";
	public final static String PRODUCT_API_PATH = "/api/product/";
  
}
```

## API do Produto

Esta API utiliza o Basic Authentication.
O Basic Authentication é o sistema de autenticação mais comum do protocolo HTTP. Ele é incluído no header da requisição HTTP dessa maneira:
```
Authorization: Basic {credenciais em base 64 no formato usuário:senha}
```

Para esse projeto utlizaremos credenciais simples, ssabendo que, em projetos reais deveremos ter uma autenticação mais segura ou até mesmo diferente utilizando tokens de acesso.

```
UserName: luiza
Senha: labs
```

Os endpoints foram mapeados usando a ferramenta POSTMAN e sua documentação se encontra em:
https://documenter.getpostman.com/view/94994/TVYGcdiL#273de19a-ea7d-40ed-8956-a28e2b3d324a
