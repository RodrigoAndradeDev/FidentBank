package dev.rodrigo.fidentbank.config; // ajuste o seu pacote aqui

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        // 1. Desativa a proteção CSRF (necessário para testes de API REST via POST/PUT)
        .csrf(csrf -> csrf.disable()) 
        
        // 2. Configura a permissão das rotas
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // 🔓 Libera a criação de usuários
            .requestMatchers(HttpMethod.GET, "/contas/**").permitAll() // 🔓 Libera a busca de contas
            .anyRequest().authenticated() // 🔒 Exige autenticação para as outras rotas
        )
        .build();
}
}
