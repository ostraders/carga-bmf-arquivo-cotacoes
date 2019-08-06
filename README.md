## Carga BMF: Arquivo de Cotações

#### Resumo
Projeto tem como objetivo a Carga BMF do arquivo posicional de cotações 245 posições.
Efetuando a leitura e a escrita dos dados na base de dados(PostgreSQL)

#### Algumas regras de negócio pertinentes:
* Validação dos dados carregados.
* Criação do Candlestick Diário no momento da carga de dados do arquivo posicional.
* Criação do Candlestick Semanal após a execução de carga, para que o sistema contenha todas as informações necessárias.


#### Tecnologias

* [Java 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* [Spring Boot](https://projects.spring.io/spring-boot/) * Tomcat embedded
* [Spring Batch](https://projects.spring.io/spring-batch/)
* [PostgreSQL](https://www.postgresql.org/) * Onde os dados da carga são armazenados e 
utilizado para as tabelas de controle do Spring Batch
* [Maven](https://maven.apache.org/)

#### Passos para a execução do projeto

Criar as bases de dados conforme descrito abaixo nas linhas de datasource.

#### Porta de execução
Porta de execução padrão 8080 

#### Datasources

| Name         | JNDI       | Connection URL                                            | Service Name 			| User 			 | Pass 		    |
| -------      |:----:      |:-------------:                                            |:-------------:		|:-------------: |:-------------:   |
| xxx   | xxx | jdbc:postgresql://0.0.0.0:5432/dbbmf           |                       | dbbmf    | dbbmf   |

---

#### Diretórios

sudo mkdir -p data/bmfCarga/{entrada,erro,execucao,saida}

