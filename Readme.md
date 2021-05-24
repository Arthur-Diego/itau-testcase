# Itaú - TestCase

Simple API(Rest) for password validation following specifications

## Installation
```bash
mvnw package && java -jar target/itau-testcase-docker.jar

docker build -t itau-testcase/itau-testcase-docker .

docker run -p 8080:8080 itau-testcase/itau-testcase-docker .
```
