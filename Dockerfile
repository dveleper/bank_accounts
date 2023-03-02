FROM openjdk:17-jdk-alpine
COPY "./target/bank_accounts-0.0.1.jar" "app.jar"
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]