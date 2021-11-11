package br.com.william.util;

import br.com.william.requests.student.StudentPostRequestBody;
import br.com.william.requests.student.StudentUpdateRequestBody;

public class StudentUpdateRequestBodyCreator {
    public static StudentUpdateRequestBody creatorStudentUpdateRequestBody(){
        return StudentUpdateRequestBody.builder()
                .ra(554)
                .name("John William")
                .notas(new float[]{10, 10, 10, 10})
                .build();
    }
}
