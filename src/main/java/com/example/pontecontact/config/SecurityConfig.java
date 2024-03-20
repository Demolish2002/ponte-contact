package com.example.pontecontact.config;

import com.example.pontecontact.domain.UserRole;
import com.example.pontecontact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {


    private final UserService customUserService;


    @Autowired
    public SecurityConfig(UserService customUserService) {
        this.customUserService = customUserService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/users")
                        .permitAll()
                )
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, "/api/users")
//                        .hasRole(UserRole.ROLE_ADMIN.getRole())
//                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET ,"/api/users/me")
                        .permitAll()
                ) .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE, "/api/addresses/{id}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/addresses/{id}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/addresses/forUpdate/{id}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.PUT, "/api/addresses/{id}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/addresses")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/contacts")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/contacts")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/contacts/{id}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/contacts/{id}/phoneNumber")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE, "/api/contacts/{id}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE, "/api/contacts/{contactId}/phoneNumber/{phoneNumberId}")
                        .authenticated()
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.PUT, "/api/contacts/{id}")
                        .authenticated()
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().denyAll()
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                .logout(logout -> logout
                        .logoutUrl("/api/users/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                )
                .userDetailsService(customUserService);
        return http.build();
    }


}