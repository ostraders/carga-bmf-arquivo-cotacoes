#!/usr/bin/env bash

dirDataBmfCarga="/data/bmfCarga"
dirEntrada="/data/bmfCarga/entrada"
dirErro="/data/bmfCarga/erro"
dirExecucao="/data/bmfCarga/execucao"
dirSucesso="/data/bmfCarga/sucesso"
dirZIP="/data/bmfCarga/zip"
dirSQL="/data/sql"

if [ -d "$dirDataBmfCarga" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirDataBmfCarga"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirDataBmfCarga"
	sudo mkdir -p "$dirDataBmfCarga"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirDataBmfCarga"
	sudo chmod -R 777 "$dirDataBmfCarga"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirDataBmfCarga " + " $USER "
	sudo chown -c -R $USER "$dirDataBmfCarga"
fi

if [ -d "$dirEntrada" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirEntrada"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirEntrada"
	sudo mkdir -p "$dirEntrada"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirEntrada"
	sudo chmod -R 777 "$dirEntrada"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirEntrada " + " $USER "
	sudo chown -c -R $USER "$dirEntrada"
fi

if [ -d "$dirErro" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirErro"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirErro"
	sudo mkdir -p "$dirErro"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirErro"
	sudo chmod -R 777 "$dirErro"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirErro " + " $USER "
	sudo chown -c -R $USER "$dirErro"
fi

if [ -d "$dirExecucao" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirExecucao"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirExecucao"
	sudo mkdir -p "$dirExecucao"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirExecucao"
	sudo chmod -R 777 "$dirExecucao"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirExecucao " + " $USER "
	sudo chown -c -R $USER "$dirExecucao"
fi

if [ -d "$dirSucesso" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirSucesso"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirSucesso"
	sudo mkdir -p "$dirSucesso"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirSucesso"
	sudo chmod -R 777 "$dirSucesso"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirSucesso " + " $USER "
	sudo chown -c -R $USER "$dirSucesso"
fi

if [ -d "$dirSQL" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirSQL"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirSQL"
	sudo mkdir -p "$dirSQL"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirSQL"
	sudo chmod -R 777 "$dirSQL"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirSQL " + " $USER "
	sudo chown -c -R $USER "$dirSQL"
fi

if [ -d "$dirZIP" ]; then
	echo "PASTAS JÁ EXISTEM NO SISTEMA" + "$dirZIP"
else
	echo "CRIANDO PASTAS DO SISTEMA" + "$dirZIP"
	sudo mkdir -p "$dirZIP"

	echo "AGUARDA 3 SEGUNDOS"
	sleep 3

	echo "ATRIBUI PERMISSÕES AS PASTAS CRIADAS" + "$dirZIP"
	sudo chmod -R 777 "$dirZIP"

	echo "ALTERANDO O DONO DA PASTA CRIADA" + " $dirZIP " + " $USER "
	sudo chown -c -R $USER "$dirZIP"
fi

echo "PARANDO TODAS MAQUINAS DOCKER"
docker stop kafka
docker stop pg-dbbmf
docker stop pg-calcula-dbbmf

echo "AGUARDA 2 SEGUNDOS"
sleep 2

echo "REMOVENDO TODAS MAQUINAS DOCKER"
docker rm -f kafka
docker rm -f pg-dbbmf
docker rm -f pg-calcula-dbbmf

echo "AGUARDA 2 SEGUNDOS"
sleep 2

echo "CRIANDO MÁQUINAS DOCKER(KAFKA E POSTGRESQL)"
docker-compose up -d

echo "AGUARDA 5 SEGUNDOS"
sleep 5

echo "CRIANDO TÓPICOS NO KAFKA - CANDLESTICK-DIARIO"
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic candlestick-diario

echo "AGUARDA 5 SEGUNDOS"
sleep 5

echo "CRIANDO TÓPICOS NO KAFKA - CANDLESTICK-SEMANAL"
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic candlestick-semanal

echo "AGUARDA 5 SEGUNDOS"
sleep 5

echo "CRIANDO TÓPICOS NO KAFKA - RECOMENDACAO-DIARIA"
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic recomendacao-diaria

echo "AGUARDA 5 SEGUNDOS"
sleep 5

echo "CRIANDO TÓPICOS NO KAFKA - RECOMENDACAO-SEMANAL"
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic recomendacao-semanal

exit 1
