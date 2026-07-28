package dev.rodrigo.fidentbank.config;

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
                // 🔓 Libera o passe livre (VIP) para a interface gráfica do Swagger
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/error"
                ).permitAll()

                // 🔓 Libera as suas rotas de negócio
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() 
                .requestMatchers(HttpMethod.GET, "/contas/**").permitAll() 
                
                // 🔒 Exige autenticação para qualquer outra rota que não listamos acima
                .anyRequest().authenticated() 
            )
            .build();
    }
}
