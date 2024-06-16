# DengueDefender
## Overview
### Como funciona
Esta aplicação foi desenvolvida em Java, utilizando a lib JavaFX para a interface gráfica, esse projeto é responsável por gerenciar e exibir denúncias feitas por usuários,
integrando com a API do Google Maps através de uma página web que é renderizada pelo JavaFX.

### Slides de introdução
https://dengue-zero.my.canva.site

## Configurando
### Java e Dependencias
Todos pacotes e serviços necessários já estão incluidos neste repositório, exceto a SDK do Java.

Recomendados utilizar a versão mais atual do JDK:

* JDK 22 - https://www.oracle.com/br/java/technologies/downloads/#jdk22-windows

### Docker
Para facilitar a execução do banco de dados, optamos por utilizar o Docker, com isso criamos um arquivo docker-compose.yaml onde esta configurado todos parametros necessários para o banco de dados,
e onde também é executado o arquivo init.sql para criar as tabelas que serão utilizadas e executar inserções que serão necessárias.

#### Instalação
Para instalar o Docker, basta seguir sua documentação:
https://docs.docker.com/get-docker/

## Rodando de forma local através do terminal
Como neste repositório já se encontra o build da aplicação, não será necessário rodar esta etapa, e então podemos seguir direto para a execução.

Primeiramente, clone este repositório em sua máquina e abra um terminal dentro do diretório do projeto.

Com o terminal aberto, começaremos iniciando o banco de dados:

```
docker compose up -d
```

Você deve ver a seguinte saida após rodar o comando acima:

```
[+] Running 2/2
 - Network denguedefender_default  Created
 - Container denguedefender-db-1   Started   
```

Após isso, para rodar a aplicação basta executar o seguinte comando:

```
java --module-path javafx-sdk-22.0.1\lib --add-modules javafx.controls,javafx.base,javafx.graphics,javafx.web,java.sql,java.desktop,jdk.jsobject -jar target\DengueDefender-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Com isso esta tudo pronto.
