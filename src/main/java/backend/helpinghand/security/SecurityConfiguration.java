package backend.helpinghand.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {
            // --sawgger-ui
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",

            //Login
            "/helpinghands/users/login/**",

            //Register
            "/helpinghands/users/register/**"
    };

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //Validar el Token
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //Configurar Conexiones Externas
        http.cors(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);


        //Permisos para las rutas
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/helpinghands/users/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/helpinghands/users/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/helpinghands/**").hasAnyAuthority("CONSULTA")
                .requestMatchers(HttpMethod.POST, "/helpinghands/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.PUT, "/helpinghands/**").hasAnyAuthority("REGISTRO")
                .requestMatchers(HttpMethod.DELETE, "/helpinghands/**").hasAnyAuthority("REGISTRO")
                .anyRequest().authenticated()
        );

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }


}