#!/usr/bin/env bash

DIR="/data/bmfCarga"

if [ -d "$DIR" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA"
else
	echo "CRIANDO PASTAS DO SISTEMA"
	sudo mkdir -p /data/bmfCarga/{entrada,erro,execucao,sucesso,zip}

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS"
	sudo chmod -R 777 /data

	exit 1
fi

docker ps --format "{{.Names}}" | grep 'carga-bmf-arquivo-cotacoes' &> /dev/null

if [ $? == 0 ]; then
   echo "Containers já existem no sistema operacional"
   sudo chmod 777 /var/run/docker.sock
   docker start --detach-keys --interactive carga-bmf-arquivo-cotacoes_kafka_1
   docker start --detach-keys --interactive carga-bmf-arquivo-cotacoes_pg-dbbmf_1
   docker start --detach-keys --interactive carga-bmf-arquivo-cotacoes_pg-calcula-dbbmf_1
else
	echo "PARANDO TODAS MAQUINAS DOCKER"
	docker stop $(docker ps -aq)

	echo "AGUARDA 2 SEGUNDOS"
	sleep 2

	echo "REMOVENDO TODAS MAQUINAS DOCKER"
	docker rm -f $(docker ps -aq)

	echo "AGUARDA 2 SEGUNDOS"
	sleep 2

	echo "CRIANDO MÁQUINAS DOCKER(KAFKA E POSTGRESQL)"
	docker-compose up -d

	echo "AGUARDA 5 SEGUNDOS"
	sleep 5

	echo "CRIANDO TÓPICOS NO KAFKA DIARIO"
	docker exec carga-bmf-arquivo-cotacoes_kafka_1 /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic candlestick-diario

	echo "AGUARDA 5 SEGUNDOS"
	sleep 5

	echo "CRIANDO TÓPICOS NO KAFKA SEMANAL"
	docker exec carga-bmf-arquivo-cotacoes_kafka_1 /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic candlestick-semanal

  exit 1
fi
