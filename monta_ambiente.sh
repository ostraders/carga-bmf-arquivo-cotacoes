#!/usr/bin/env bash

echo "PARANDO TODAS MAQUINAS DOCKER"
sudo docker stop $(docker ps -aq)

echo "AGUARDA 2 SEGUNDOS"
sleep 2

echo "REMOVENDO TODAS MAQUINAS DOCKER"
sudo docker rm -f $(docker ps -aq)

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

echo "AGUARDA 2 SEGUNDOS"
sleep 2

echo "CRIANDO MÁQUINAS DOCKER(KAFKA E POSTGRESQL)"
sudo docker-compose up -d

echo "AGUARDA 5 SEGUNDOS"
sleep 5

echo "CRIANDO TÓPICOS NO KAFKA DIARIO"
sudo docker exec carga-bmf-arquivo-cotacoes_kafka_1 /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic candlestick-diario

echo "AGUARDA 5 SEGUNDOS"
sleep 5

echo "CRIANDO TÓPICOS NO KAFKA SEMANAL"
sudo docker exec carga-bmf-arquivo-cotacoes_kafka_1 /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic candlestick-semanal
