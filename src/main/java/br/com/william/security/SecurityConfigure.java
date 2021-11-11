package br.com.william.security;

import br.com.william.service.UserApplicationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    private final UserApplicationsService userApplicationsService;

    @Override // O que vai ser protegido
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers("/v1/**/admin/**").hasRole("ADMIN")
                .antMatchers("/v1/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encode {}", passwordEncoder.encode("test"));
//        auth.inMemoryAuthentication()
//                .withUser("john")
//                .password(passwordEncoder.encode("test"))
//                .roles("administrador", "usuario")
//                .and()
//                .withUser("pet")
//                .password(passwordEncoder.encode("test"))
//                .roles( "usuario");
        auth.userDetailsService(userApplicationsService)
                .passwordEncoder(passwordEncoder);
    }
}
