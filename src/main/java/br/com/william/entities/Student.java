package br.com.william.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ra;
//    @NotEmpty(message = "The student name cannot be empty")
    private String name;
    private float[] notas;

    @Override
    public String toString() {
        return "Student{" +
                "ra='" + ra + '\'' +
                ", name='" + name + '\'' +
                ", notas=" + Arrays.toString(notas) +
                '}';
    }
}
