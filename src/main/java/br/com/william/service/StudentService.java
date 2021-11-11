package br.com.william.service;

import br.com.william.entities.Student;
import br.com.william.exception.exception.BadRequestException;
import br.com.william.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentDao;

    public Page<Student> getAll(Pageable pageable){
        return studentDao.findAll(pageable);
    }
    public List<Student> getAllNoPageable(){
        return studentDao.findAll();
    }
    public Student findByIdOrThrowBadRequestException(int id){
       return studentDao.findById(id)
               .orElseThrow(() -> new BadRequestException("Student not found"));
    }
    public List<Student> findByName(String name){
        return studentDao.findByName(name);
    }
    public Student save(Student student){
        return studentDao.save(student);
    }
    public void deleta(int id){
        studentDao.delete(findByIdOrThrowBadRequestException(id));
    }
}
