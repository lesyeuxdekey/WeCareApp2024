package com.keylin.WeCare.security.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AuthenticationSuccessHandler success;

    @Bean
    public UserDetailsManager userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        log.info("securityFilterChain");

        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/h2-ui/**").permitAll()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/login", "/loggedinN", "/loggedinF", "/logout", "/list", "/profile/", "/images/**", "/",
                        "/css/**", "/img/**")
                .permitAll()
                .requestMatchers("/n/**").hasRole("N")
                .requestMatchers("/f/**").hasRole("F")
                .anyRequest().authenticated())

                .formLogin((form) -> form.loginPage("/login")
                        .successHandler(success)
                        .failureUrl("/loginError")
                        .permitAll());
        http.logout((logout) -> logout.permitAll());

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-ui/**"));
        http.headers(headers -> headers.frameOptions().sameOrigin());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
