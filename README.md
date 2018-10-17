# WebFlux Filter Sample

This is a sample Spring Boot reactive application with a web filter.

This is also a minimalistic working example for
<https://stackoverflow.com/questions/52668050/spring-webflux-statuscode-is-null-in-a-webfilter>
and <https://jira.spring.io/browse/SPR-17368>

As a conclusion it seems that `org.springframework.web.server.WebFilter`
requires ResponseEntity in the REST controller return types to correctly retrieve the HTTP response status.

Run with `mvn spring-boot:run`.

Then connect to various URL and see the HTTP response status from the web filter in the application logs:
- <http://localhost:8080/empty1> (returns void): status is null
- <http://localhost:8080/empty2> (returns Mono&lt;Void&gt;): status is null
- <http://localhost:8080/empty3> (returns ResponseEntity&lt;Void&gt;): status is 200
- <http://localhost:8080/empty4> (returns Mono&lt;ResponseEntity&lt;Void&gt;&gt;): status is 200
- <http://localhost:8080/hello1> (returns String): status is null
- <http://localhost:8080/hello2> (returns Mono&lt;String&gt;): status is null
- <http://localhost:8080/hello3> (returns ResponseEntity&lt;String&gt;): status is 200
- <http://localhost:8080/hello4> (returns Mono&lt;ResponseEntity&lt;String&gt;&gt;): status is 200
- <http://localhost:8080/bean1> (returns MyBean): status is null
- <http://localhost:8080/bean2> (returns Mono&lt;MyBean&gt;): status is null
- <http://localhost:8080/bean3> (returns ResponseEntity&lt;MyBean&gt;): status is 200
- <http://localhost:8080/bean4> (returns Mono&lt;ResponseEntity&lt;MyBean&gt;&gt;): status is 200
- <http://localhost:8080/pause1/1000> (returns Mono&lt;String&gt;): status is null
- <http://localhost:8080/pause2/1000> (returns Mono&lt;ResponseEntity&lt;String&gt;&gt;): status is 200
