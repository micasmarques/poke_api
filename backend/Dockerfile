# Usar a imagem oficial do OpenJDK 23 para Linux
FROM arm64v8/openjdk:23-jdk

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar o código do backend para o diretório de trabalho
COPY . .

# Compilar o código com Maven (ajuste conforme sua build tool)
RUN ./mvnw clean package -DskipTests

# Definir o comando para rodar a aplicação
CMD ["java", "-jar", "target/poke_api.jar"]

# Expor a porta da aplicação
EXPOSE 8080
