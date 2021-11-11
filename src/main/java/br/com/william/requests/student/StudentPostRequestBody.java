package br.com.william.requests.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
public class StudentPostRequestBody {
    @NotEmpty(message = "The student name cannot be empty")
    @NotNull(message = "The student name cannot be null")
    private String name;
    private float[] notas;
}
