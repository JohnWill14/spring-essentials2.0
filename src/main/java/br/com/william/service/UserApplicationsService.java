package br.com.william.service;

import br.com.william.entities.UserApplication;
import br.com.william.repository.UserApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserApplicationsService implements UserDetailsService {
    private UserApplicationRepository userApplicationRepository;
    @Override
    public UserDetails loadUserByUsername(String s)  {
        return Optional.ofNullable(userApplicationRepository.findByUsername(s))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
