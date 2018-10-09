# WebFlux Filter Sample

This is a sample Spring Boot reactive application with a web filter.

This is also a minimalistic working example for https://stackoverflow.com/questions/52668050/spring-webflux-statuscode-is-null-in-a-webfilter

As a conclusion it seems that `org.springframework.web.server.WebFilter`
requires ResponseEntity in the REST controller return types to correctly retrieve the HTTP response status.
Bug?

Run with `mvn spring-boot:run`.

Then connect to various URL and see the HTTP response status from the web filter in the application logs:
- http://localhost:8080/empty1 : status is null
- http://localhost:8080/empty2 : status is null
- http://localhost:8080/empty3 : status is 200
- http://localhost:8080/empty4 : status is 200
- http://localhost:8080/hello1 : status is null
- http://localhost:8080/hello2 : status is null
- http://localhost:8080/hello3 : status is 200
- http://localhost:8080/hello4 : status is 200
- http://localhost:8080/bean1 : status is null
- http://localhost:8080/bean2 : status is null
- http://localhost:8080/bean3 : status is 200
- http://localhost:8080/bean4 : status is 200
- http://localhost:8080/pause1/1000 : status is null
- http://localhost:8080/pause2/1000 : status is 200
