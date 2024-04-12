package c14.NoCountry.config;

import c14.NoCountry.Entity.Role;
import c14.NoCountry.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(htt->htt.disable()).authorizeHttpRequests(
                        auth->
                                auth.requestMatchers(HttpMethod.POST,"/user/registerDonor","/user/registerCreator","/user/registerAdmin","/user/login")
                                        .permitAll()
                                        .anyRequest().authenticated()
                ).sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )).
                formLogin(AbstractAuthenticationFilterConfigurer::permitAll).
                authenticationProvider(authenticationProvider).
                addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }


}
