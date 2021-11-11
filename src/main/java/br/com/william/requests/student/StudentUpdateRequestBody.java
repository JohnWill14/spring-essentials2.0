package br.com.william.requests.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudentUpdateRequestBody {
    private Integer ra;
    private String name;
    private float[] notas;
}
