FROM amazoncorretto:17

RUN yum install -y dos2unix tar gzip

WORKDIR /app

COPY . .

RUN dos2unix mvnw

# Dar permissão de execução ao Maven Wrapper (mvnw)
RUN chmod +x mvnw

RUN ./mvnw clean package

CMD ["java", "-jar", "target/poke-api-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
