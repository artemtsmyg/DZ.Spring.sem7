package artemtsmyg.ru.DZ_sem7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Включение и настройка Spring Security для веб-приложения
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Цепочка фильтров по настройке параметров безопасности
     * @param http экземпляр HttpSecurity - настройка веб-безопасности для определенных http-запросов
     * @return цепочка фильтров
     * @throws Exception исключения
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/css/**", "/", "/index", "/img/pet-house.svg").permitAll()
                        .requestMatchers("/user", "/public-data").hasAnyRole("USER")
                        .requestMatchers("/admin", "/private-data").hasAnyRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/error-page"))
                .formLogin(login -> login
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    /**
     * Одностороннее преобразование пароля
     * @return экземпляр DelegatingPasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * UserDetailsManager - метод предоставления пользователям доступа к информации в соответствии с их ролью в системе.
     * @return - информация о пользователе и его правах в ИС.
     */
    @Bean
    UserDetailsManager inMemoryUserDetailsManager() {
        var user1 = User.withUsername("user1").password("{noop}user1").roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}admin").roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user1, admin);
    }
}