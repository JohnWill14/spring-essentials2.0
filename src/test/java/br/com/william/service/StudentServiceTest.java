package br.com.william.service;

import br.com.william.endpoint.StudentController;
import br.com.william.entities.Student;
import br.com.william.exception.exception.BadRequestException;
import br.com.william.repository.StudentRepository;
import br.com.william.requests.student.StudentPostRequestBody;
import br.com.william.requests.student.StudentUpdateRequestBody;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @InjectMocks // classe
    private StudentService studentService;
    @Mock// atributos da classe
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup(){
        List<Student> students = List.of(StudentCreator.createdToValidStudent());
        PageImpl<Student> studentsPage = new PageImpl<>(students);
        BDDMockito.when(studentRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(studentsPage);
        BDDMockito.when(studentRepository.findAll())
                .thenReturn(students);
        BDDMockito.when(studentRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(students);
        BDDMockito.when(studentRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(StudentCreator.createdToValidStudent()));
        BDDMockito.when(studentRepository.save(ArgumentMatchers.any(Student.class)))
                .thenReturn(StudentCreator.createdToValidStudent());
    }

    @Test
    @DisplayName("Returns list of students inside page object when successful")
    public void list_ReturnsListOfStudentsInsidePageObject_WhenSuccessful(){
        Page<Student> studentsPage = studentService.getAll(PageRequest.of(1, 1));
        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(studentsPage)
                .isNotEmpty()
                .contains(studentValid);


    }
    @Test
    @DisplayName("Returns list of students when successful")
    public void list_ReturnsListOfStudents_WhenSuccessful(){
        List<Student> students = studentService.getAllNoPageable();

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .contains(studentValid);
    }
    @Test
    @DisplayName("Returns list of students by name when successful")
    public void findByName_ReturnsListOfStudents_WhenSuccessful(){
        List<Student> students = studentService.findByName("john");

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students).isNotEmpty();
        Assertions.assertThat(students).contains(studentValid);

    }
    @Test
    @DisplayName("Returns list of students by name when list is empty")
    public void findByName_ReturnsListOfStudents_WhenListIsEmpty(){
        BDDMockito.when(studentRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());
        List<Student> students = studentService.findByName("john");

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(students)
                .isNotNull()
                .hasSize(0)
                .doesNotContain(studentValid);

    }
    @Test
    @DisplayName("Return student by id when successful")
    public void findByID_Student_WhenSuccessful(){
        Student student = studentService.findByIdOrThrowBadRequestException(987878);

        Student studentValid = StudentCreator.createdToValidStudent();

        Assertions.assertThat(student)
                .isNotNull()
                .isEqualTo(studentValid);
    }
    @Test
    @DisplayName("Return student by id when successful")
    public void findByID_ReturnStudent_WhenThrowBadRequestException(){
        BDDMockito.when(studentRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());


        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> studentService.findByIdOrThrowBadRequestException(987878))
                .withMessageContaining("Student not found");
    }
    @Test
    @DisplayName("Return student saved when successful")
    public void save_ReturnStudent_WhenSuccessful(){

        Student student = studentService.save(StudentCreator.createdToValidStudent());
        Student studentValid = StudentCreator.createdToValidStudent();
        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getRa()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo(studentValid.getName());
        Assertions.assertThat(student.getNotas()).isEqualTo(studentValid.getNotas());
    }

    @Test
    @DisplayName("Return void student delete when successful")
    public void replace_ReturnStudent_WhenSuccessful(){

        Assertions.assertThatCode(() -> studentService.deleta(1))
                .doesNotThrowAnyException();
    }

}