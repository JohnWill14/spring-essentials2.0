package br.com.william.repository;

import br.com.william.entities.Student;
import br.com.william.util.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Student repository")
class StudentRepositoryTest {
    @Autowired
    private  StudentRepository studentRepository;

    @Test
    @DisplayName("Save persist Student when successful")
    public void save_PersistStudent_When_Successful(){
        Student studentToSaved =  StudentCreator.createdStudentToSaved();
        Student studentSaved = this.studentRepository.save(studentToSaved);

        Assertions.assertThat(studentSaved).isNotNull();
        Assertions.assertThat(studentSaved.getRa()).isNotNull();
        Assertions.assertThat(studentSaved.getName()).isEqualTo(studentToSaved.getName());
        Assertions.assertThat(studentSaved.getNotas()).isEqualTo(studentToSaved.getNotas());

    }

    @Test
    @DisplayName("Save update Student when successful")
    public void save_UpdateStudent_When_Successful(){
        Student studentToSaved = StudentCreator.createdStudentToSaved();
        Student studentSaved = this.studentRepository.save(studentToSaved);
        studentSaved.setName("João guilherme");
        studentSaved.setNotas(new float[]{9.9f, 9.5f, 9.5f, 9.5f});

        Student studentUpdated = this.studentRepository.save(studentSaved);

        Assertions.assertThat(studentUpdated).isNotNull();
        Assertions.assertThat(studentUpdated.getRa()).isNotNull();
        Assertions.assertThat(studentUpdated.getName()).isEqualTo(studentSaved.getName());
        Assertions.assertThat(studentUpdated.getNotas()).isEqualTo(studentSaved.getNotas());

    }
    @Test
    @DisplayName("Save delete Student when successful")
    public void delete_DeleteStudent_When_Successful(){
        Student studentToSaved = StudentCreator.createdStudentToSaved();
        Student studentSaved = this.studentRepository.save(studentToSaved);

        this.studentRepository.delete(studentSaved);

        Optional<Student> studentOptional = this.studentRepository.findById(studentSaved.getRa());
        Assertions.assertThat(studentOptional).isEmpty();


    }
    @Test
    @DisplayName("Save find by name of  Student when successful")
    public void findByName_ReturnListStudents_When_Successful(){
        Student studentToSaved =  StudentCreator.createdStudentToSaved();
        Student studentSaved = this.studentRepository.save(studentToSaved);

        String nameStudent = studentSaved.getName();
        List<Student> students = this.studentRepository.findByName(nameStudent);

        Assertions.assertThat(students)
                .isNotEmpty()
                .contains(studentSaved);


    }

    @Test
    @DisplayName("Save throw ConstraintViolationException whe name is empty")
    public void save_ThrowConstraintViolationException_WhenNameIsEmpty(){
        Student studentToSaved = new Student();
        studentToSaved.setNotas(new float[]{10,10,10,10});

//        Assertions.assertThatThrownBy(() -> this.studentRepository.save(studentToSaved))
//        .isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.studentRepository.save(studentToSaved))
                .withMessageContaining("The student name cannot be empty");
    }


}