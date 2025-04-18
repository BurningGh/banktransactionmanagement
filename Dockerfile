FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/bank-transactionDO-management-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# 定义容器启动时执行的命令
CMD ["java", "-jar", "app.jar"]    