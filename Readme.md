# Itaú - TestCase

Simple API(Rest) for password validation following specifications
#### Tecnologias
* Java 11 
* Springboot 2.5.0
* Lombok
* Junit
* Swagger
* Docker
#### Solução
* Dado (String) recebido por uma requisição ***http post*** na camada controller
* Passado para camada de service
* Uso da interface(***ValidatePasswordService***) para desacoplamento com o detalhe priorizando a abstração
* Classe(***ValidatePasswordServiceImpl***) que implementa interface contém a lógica da validação
* Uso de ***regex*** para solução da maioria das possíveis situações

```java
PATTERN_DIGIT = ".*\\d.*";
PATTERN_LETTER_LOWER_CASE = ".*[a-z].*";
PATTERN_LETTER_UPPER_CASE = ".*[A-Z].*";
PATTERN_SPECIAL_CHARACTER = ".*[!@#$%^&*()+-].*";
PATTERN_DUPLICATE_CHARACTER = ".*([A-Za-z0-9!@#$%^&*()+-])(?=.+\\1).*";
PATTERN_DUPLICATE_SEQUENCE_CHARACTER = ".*([A-Za-z0-9!@#$%^&*()+-])\\1.*";
```
* Para cada situação prevista temos um regex que vai validar nossa senha, 
com exceção de ***espaço em branco e tamanho da senha***, optei por usar Java
devido a simplicidade da validação.

```java
password.contains(" ")
password.length() < 9
```
* Se caso a senha não satisfaça os requisitos, será lançada uma exception ***PasswordInvalidException*** com sua respectiva mensagem
* Essa exception é capturada pela classe ***ApiExceptionHandlerAdvice*** que trata a exception devolvendo uma mensagem amigavel para o consumidor da API

* Caso tudo ocorra bem, e a senha passe em todas as validações, então retornará uma mensagem ***true***

## Instalação
```bash
git clone https://github.com/Arthur-Diego/itau-testcase.git

cd itau-testcase/

mvnw package && java -jar target/itau-testcase-docker.jar or ./mvnw package && java -jar target/itau-testcase-docker.jar

docker build -t itau-testcase/itau-testcase-docker .
```

## Uso
```bash
docker run -p 8080:8080 itau-testcase/itau-testcase-docker .

http://localhost:8080/swagger-ui.html#
```