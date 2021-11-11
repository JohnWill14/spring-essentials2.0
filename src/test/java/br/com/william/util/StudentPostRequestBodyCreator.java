package br.com.william.util;

import br.com.william.requests.student.StudentPostRequestBody;

public class StudentPostRequestBodyCreator {
    public static StudentPostRequestBody createStudentPostRequestBody(){
        return StudentPostRequestBody.builder()
                .name("John William")
                .notas(new float[]{10, 10, 10, 10})
                .build();
    }
}
