package br.com.william.mapper;

import br.com.william.entities.Student;
import br.com.william.requests.student.StudentPostRequestBody;
import br.com.william.requests.student.StudentUpdateRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StudentMapper {
    public static final StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    public abstract Student toStudent(StudentPostRequestBody studentPostRequestBody);
    public abstract Student toStudent(StudentUpdateRequestBody studentPutRequestBody);
}
