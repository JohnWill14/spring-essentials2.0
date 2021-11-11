package br.com.william.client;

import br.com.william.entities.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Student> entity = new RestTemplate().getForEntity("http://localhost:8080/v1/students/{id}", Student.class,1);
        Student body = null;
       // body = new RestTemplate().getForEntity("http://localhost:8080/v1/students/1", Student.class).getBody();
        body = new RestTemplate().getForObject("http://localhost:8080/v1/students/{id}", Student.class, 1);
        log.info(entity);
        log.info(body);

        ResponseEntity<List<Student>> listaStudents = new RestTemplate().exchange(
                "http://localhost:8080/v1/students/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                }
        );
        log.info(listaStudents.getBody());

        Student student = Student.builder().name("John William").notas(new float[]{10,10,10,10}).build();
//        Student studentSalvo = new RestTemplate().postForObject(
//                "http://localhost:8080/v1/students/",
//                student,
//                Student.class
//        );
//        log.info(studentSalvo);

        ResponseEntity<Student> exchange = new RestTemplate().exchange(
                "http://localhost:8080/v1/students/",
                HttpMethod.POST,
                new HttpEntity<>(student),
                Student.class
        );
        log.info(exchange.getBody());
    }
}
