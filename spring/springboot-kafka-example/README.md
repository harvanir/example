# Spring Boot Kafka example
Example of Spring Boot Kafka production code implementation. 

## How to use
**1. Clone the application**

```bash
git clone https://github.com/harvanir/example.git
```

**2. Download & install kafka**

```bash
https://kafka.apache.org/downloads
https://kafka.apache.org/quickstart
```

**3. Paste these files to local directory (example windows directory = D:/opt/boot/config)**
1. /resources/opt/boot/config/application-prd.properties
2. /resources/opt/boot/config/log4j2.xml

**4. Build the application**

```bash
mvn clean install -Dkafka.bootstrapAddress=localhost:9092
```

**5. Go to kafka directory**

```bash
localDrive>cd D:\app\kafka_2.12-1.1.0
```

**6. Run the kafka server**

```bash
D:\app\kafka_2.12-1.1.0>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
D:\app\kafka_2.12-1.1.0>.\bin\windows\kafka-server-start.bat .\config\server.properties
```

**7. Run the kafka consumer (listen message via console)**

```bash
D:\app\kafka_2.12-1.1.0>.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
```

**8. Run the kafka producer (produce message via console)**

```bash
D:\app\kafka_2.12-1.1.0>.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test
```

**9. Run the spring boot application**

```bash
java -jar -Dserver.port=8082 -Dlogging.config=D:/opt/boot/config/log4j2.xml -Dspring.profiles.active=prd -Dspring.config.location=D:/opt/boot/config/ target/springboot-kafka-example-0.0.1-SNAPSHOT-exec.jar
```

**10. Access the application via web browser (produce message)**

```
http://localhost:8082/kafka/testconsumer/send/{any message}
```

**11. Access the application via web browser (get message)**

```
http://localhost:8082/kafka/testconsumer/list
```