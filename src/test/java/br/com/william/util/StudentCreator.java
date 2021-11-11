package br.com.william.util;

import br.com.william.entities.Student;

public class StudentCreator {
    public static Student createdStudentToSaved(){
        return Student.builder()
                .name("Ligth Yagami")
                .notas(new float[]{10,10,10,10})
                .build();
    }

    public static Student createdToValidStudent(){
        return Student.builder()
                .name("Ligth Yagami")
                .notas(new float[]{10,10,10,10})
                .ra(1)
                .build();
    }
    public static Student createdToValidUpdateStudent(){
        return Student.builder()
                .name("Ligth Yagami")
                .notas(new float[]{10,10,10,10})
                .ra(1)
                .build();
    }
}
