FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 9000

CMD ["sh", "-c", "java -jar target/*.jar --server.port=${PORT:-9000}"]
