package fit.se.haxuanphu_tuan07.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configurable
@EnableAutoConfiguration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/test").permitAll()
                .requestMatchers("/", "/products").hasAnyRole("ADMIN",
                        "CUSTOMER")
                .requestMatchers("/products/view/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/products/add", "/products/edit/**",
                        "/products/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/products", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/products")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }

}
