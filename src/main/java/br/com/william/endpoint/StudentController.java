package br.com.william.endpoint;

import br.com.william.entities.Student;
import br.com.william.mapper.StudentMapper;
import br.com.william.requests.student.StudentPostRequestBody;
import br.com.william.requests.student.StudentUpdateRequestBody;
import br.com.william.service.StudentService;
import br.com.william.util.DataUtil;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "v1/students")
@Log4j2
@RequiredArgsConstructor
public class StudentController {

    private final DataUtil du;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getListPageable(Pageable pageable){
        return new ResponseEntity<>(studentService.getAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll(){
        return new ResponseEntity<>(studentService.getAllNoPageable(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id){

        return new ResponseEntity<>(studentService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }
    @GetMapping(path = "/find")
    public ResponseEntity<List<Student>> findByName(@RequestParam String name){

        return new ResponseEntity<>(studentService.findByName(name), HttpStatus.OK);
    }
    @PostMapping
//    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<Student> save(@RequestBody @Valid StudentPostRequestBody studentPost){
        Student student = StudentMapper.INSTANCE.toStudent(studentPost);
        return new ResponseEntity<>( studentService.save(student), HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        studentService.deleta(id);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<Student> replace(@RequestBody StudentUpdateRequestBody studentUpdate){
        studentService.save(StudentMapper.INSTANCE.toStudent(studentUpdate));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
