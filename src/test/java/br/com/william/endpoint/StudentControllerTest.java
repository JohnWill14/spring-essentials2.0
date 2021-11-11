package br.com.william.endpoint;

import br.com.william.entities.Student;
import br.com.william.exception.exception.BadRequestException;
import br.com.william.requests.student.StudentPostRequestBody;
import br.com.william.requests.student.StudentUpdateRequestBody;
import br.com.william.service.StudentService;
import br.com.william.util.StudentCreator;
import br.com.william.util.StudentPostRequestBodyCreator;
import br.com.william.util.StudentUpdateRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @InjectMocks // classe
    private StudentController studentController;
    @Mock// atributos da classe
    private StudentService studentService;

    @BeforeEach
    public void setup(){
        List<Student> students = List.of(StudentCreator.createdToValidStudent());
        PageImpl<Student> studentsPage = new PageImpl<>(students);
        BDDMockito.when(studentService.getAll(ArgumentMatchers.any()))
                .thenReturn(studentsPage);
        BDDMockito.when(studentService.getAllNoPageable())
                .thenReturn(students);
        BDDMockito.when(studentService.findByName(ArgumentMatchers.anyString()))
                .thenReturn(students);
        BDDMockito.when(studentService.findByIdOrThrowBadRequestException(ArgumentMatchers.anyInt()))
                .thenReturn(StudentCreator.createdToValidStudent());
        BDDMockito.when(studentService.save(ArgumentMatchers.any(Student.class)))
                .thenReturn(StudentCreator.createdToValidStudent());
        BDDMockito.doNothing().when(studentService).deleta(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("Returns list of students inside page object when successful")
    public void list_ReturnsListOfStudentsInsidePageObject_WhenSuccessful(){
        Page<Student> students = studentController.getListPageable(null).getBody();
        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students).contains(studentValid);

    }
    @Test
    @DisplayName("Returns list of students when successful")
    public void list_ReturnsListOfStudents_WhenSuccessful(){
        List<Student> students = studentController.getAll().getBody();

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students).contains(studentValid);

    }
    @Test
    @DisplayName("Returns list of students by name when successful")
    public void findByName_ReturnsListOfStudents_WhenSuccessful(){
        List<Student> students = studentController.findByName("john").getBody();

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students).contains(studentValid);

    }
    @Test
    @DisplayName("Returns list of students by name when list is empty")
    public void findByName_ReturnsListOfStudents_WhenListIsEmpty(){
        BDDMockito.when(studentService.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());
        List<Student> students = studentController.findByName("john").getBody();

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students).isEmpty();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students).doesNotContain(studentValid);

    }
    @Test
    @DisplayName("Return student by id when successful")
    public void findByID_Student_WhenSuccessful(){
        Student student = studentController.findById(987878).getBody();

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(student)
                .isNotNull()
                .isEqualTo(studentValid);
    }
    @Test
    @DisplayName("Return student by id when successful")
    public void findByID_ReturnStudent_WhenThrowBadRequestException(){
        BDDMockito.when(studentService.findByIdOrThrowBadRequestException(ArgumentMatchers.anyInt()))
                .thenThrow(new BadRequestException("Error"));


        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> studentController.findById(987878))
                .withMessageContaining("Error");
    }
    @Test
    @DisplayName("Return student saved when successful")
    public void save_ReturnStudent_WhenSuccessful(){

        StudentPostRequestBody studentPostRequestBody = StudentPostRequestBodyCreator.createStudentPostRequestBody();
        Student student = studentController.save(studentPostRequestBody)
                .getBody();
        Student studentValid = StudentCreator.createdToValidStudent();
        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getRa()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo(studentValid.getName());
        Assertions.assertThat(student.getNotas()).isEqualTo(studentValid.getNotas());
    }
    @Test
    @DisplayName("Return void student updated when successful")
    public void replace_ReturnVoidOrThrowException_WhenSuccessful(){
        StudentUpdateRequestBody studentUpdateRequestBody = StudentUpdateRequestBodyCreator.creatorStudentUpdateRequestBody();
        Assertions.assertThatCode(() -> studentController.replace(studentUpdateRequestBody) )
                .doesNotThrowAnyException();
    }
    @Test
    @DisplayName("Return void student delete when successful")
    public void replace_ReturnStudent_WhenSuccessful(){

        Assertions.assertThatCode(() -> studentController.delete(1))
                .doesNotThrowAnyException();
    }

}