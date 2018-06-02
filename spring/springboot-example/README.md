# Spring Boot example
Example of Spring Boot production code implementation. 

## How to use
**1. Clone the application**

```bash
git clone https://github.com/harvanir/example.git
```
**2. Paste these files to local directory (example windows directory = D:/opt/boot/config)**
1. /resources/opt/boot/config/application-se.properties
2. /resources/opt/boot/config/log4j2.xml

**3. Build the application**

```bash
mvn clean install -Dspring.profiles.active=se -Dspring.config.location=D:/opt/boot/config/
```

**4. Run the application**

```bash
java -jar -Dserver.port=8082 -Dlogging.config=D:/opt/boot/config/log4j2.xml -Dspring.profiles.active=se -Dspring.config.location=D:/opt/boot/config/ target/springboot-example-0.0.1-SNAPSHOT-exec.jar
```

**5. Access the application via web browser**

```
http://localhost:8082
```