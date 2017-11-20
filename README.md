## Carga Boleto ConciPag

### Resumo

Este projeto tem como objetivo a carga de boletos no sistema ConciPag.

Desenho macro do fluxo da aplicação na plataforma EDI:
![alt text](http://gitlab.accesstage.com.br/resources/concipag/raw/master/CargaConciPag.png "Fluxo Macro")

---

### Tecnologias

* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Kubernetes](https://kubernetes.io/)
* [Spring Boot](https://projects.spring.io/spring-boot/)
* [Spring Batch](https://projects.spring.io/spring-batch/)
* [PostgreSQL](https://www.postgresql.org/)
* [Java 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* Git & SCM

---

### Datasources

| Name         | JNDI       | Connection URL                                            | Service Name 			| User 			 | Pass 		    |
| -------      |:----:      |:-------------:                                            |:-------------:		|:-------------: |:-------------:   |
| dbconcipag   | dbconcipag | jdbc:postgresql://192.168.42.35:5432/dbconcipag           |                       | usrconcipag    | usrconcipagdev   |

---

### Arquivos e Diretórios

Na raíz do sistema operacional efetuar a criação das seguintes pastas conforme árvore descrita a seguir:

/data/concipag/cargaBoleto/entrada

/data/concipag/cargaBoleto/saida

/data/concipag/cargaBoleto/execucao

/data/concipag/cargaBoleto/erro

Para os sistemas operacionais baseados em linux execute os seguintes comandos:

```
mkdir -p /data/concipag/cargaBoleto/entrada
mkdir -p /data/concipag/cargaBoleto/saida
mkdir -p /data/concipag/cargaBoleto/execucao
mkdir -p /data/concipag/cargaBoleto/erro
```

### Docker/Kubernetes

Para efetuar o build da imagem Docker dentro do kubernetes execute os seguintes comandos: 

Obs.: O comando abaixo deve ser executado como sudo pois o Docker gerado e enviado ao registry só aceita comandos como root.

Colocar o profile conforme a necessidade no comando do fabric8:

develop

homolog

prod

```
sudo mvn clean install -P homolog package fabric8:build fabric8:push
```

Após o comando acima é necessário copiar o arquivo ".yml" fora do diretório .m2 conforme exemplo abaixo:

Na pasta :

.../.m2/repository/com/accesstage/hiker-accmon-application/2.0.0-SNAPSHOT 

```
cp -p hiker-accmon-application-2.0.0-SNAPSHOT-kubernetes.yml /pasta_destino
```
Obs.: Disponibilize o arquivo .yml em uma pasta onde possua todas as permissões para o usuário comum da máquina, para que o navegador consiga enxergar quando for efetuar o upload.


Após os passos acima, acesse a seguinte URL:[http://kube-dev.accesstage.com.br:8080/ui](http://kube-dev.accesstage.com.br:8080/ui) e no canto direito superior clique em create.

Na tela de "Deploy a Containerized App" selecione a opção "Upload a YAML or JSON file" e selecione o arquivo .yml copiado no passo anterior.

---

### Build & Distribuição

Build usando simplesmente o seguinte comando do [Maven](https://maven.apache.org/):

```
mvn clean install -U
```

Quando um ciclo de desenvolvimento terminar, use as etapas a seguir para gerar uma nova versão compilada:

1. Certificar que o repositório local não tem commit ou pushes pendentes. E está 100% sincronizado com a origem remota:
	* Para verificar se não há nada restante
 	```
 	git status
 	```
2. Preparando o release
	* Para preparar o release use o Gerenciamento de Distribuição do Maven
	```
	mvn release:prepare
	```
	* Enviar todas as mudanças para a origem remota do git
	```
	git push
	```
Nesta etapa é preciso executar previamente os seguintes passos:
	* Maven compile e build das dependencias do projeto
	* Incrementar as versões do project/modules no Maven nos arquivos pom
	* Git fará dois commits com todas as mudanças e uma mensagem gerada automaticamente
		* [maven-release-plugin] prepare release NameOfProject-Version
		* [maven-release-plugin] prepare for next development iteration
	* Git publicará uma nova [tag](http://gitlab.accesstage.com.br/source/GerenciarUsuarioWS/tags) com a versão gerada.
3. Execute a liberação
	* Para executar a versão usando o Gerenciamento de Distribuição do Maven
	```
	mvn release:perform
	```

Neste passo o maven publica a nova versão no [Nexus](http://nexus.accesstage.com.br/nexus/content/groups/public/br/com/accesstage/boleto/)
