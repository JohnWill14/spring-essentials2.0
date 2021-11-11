package br.com.william.integration;

import br.com.william.entities.Student;
import br.com.william.util.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class StudentControllerTI {
    @Autowired
    private RestTemplate restTemplate;
    @LocalServerPort
    private int port;

//
//    @Test
//    @DisplayName("Returns list of students inside page object when successful")
//    public void list_ReturnsListOfStudentsInsidePageObject_WhenSuccessful(){
//        Page<Student> students = studentController.getListPageable(null).getBody();
//        Student studentValid = StudentCreator.createdToValidStudent();
//
//        Assertions.assertThat(students).isNotEmpty();
//        Assertions.assertThat(students).contains(studentValid);
//
//    }

}
