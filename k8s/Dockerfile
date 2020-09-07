FROM adoptopenjdk/openjdk8

LABEL maintainer="Ricardo Cocati"
LABEL version="1.0"

VOLUME /data/bmfCarga/entrada
VOLUME /data/bmfCarga/erro
VOLUME /data/bmfCarga/execucao
VOLUME /data/bmfCarga/sucesso
VOLUME /data/bmfCarga/zip

COPY application/target/*.jar carga.jar

EXPOSE 8666
EXPOSE 8080
EXPOSE 8081

ENTRYPOINT ["java","-jar","/my-app.jar"]
