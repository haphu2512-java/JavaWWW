package fit.se.haxuanphu_springai.config;


import fit.se.haxuanphu_springai.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Public access
                        .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**", "/home/**").permitAll()
                        .requestMatchers("/customers", "/customers/view/**").hasAnyRole("GUEST", "CUSTOMER", "ADMIN")
                        .requestMatchers("/products", "/products/view/**").hasAnyRole("GUEST", "CUSTOMER", "ADMIN")
                        .requestMatchers("/orders", "/orders/view/**").hasAnyRole("GUEST", "CUSTOMER", "ADMIN")
                        .requestMatchers("/orders/new", "/orders/save").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/orderlines/**").hasAnyRole("CUSTOMER", "ADMIN")

                        // ADMIN: Full CRUD access
                        .requestMatchers("/customers/new", "/customers/edit/**", "/customers/save", "/customers/delete/**").hasRole("ADMIN")
                        .requestMatchers("/products/new", "/products/edit/**", "/products/save", "/products/delete/**").hasRole("ADMIN")
                        .requestMatchers("/orders/edit/**", "/orders/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/products", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}
