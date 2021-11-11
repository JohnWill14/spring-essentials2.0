package br.com.william.repository;

import br.com.william.entities.Student;
import br.com.william.entities.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Integer> {
    UserApplication findByUsername(String username);
}
