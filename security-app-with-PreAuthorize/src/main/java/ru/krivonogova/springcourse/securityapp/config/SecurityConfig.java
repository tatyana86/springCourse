package ru.krivonogova.springcourse.securityapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.krivonogova.springcourse.securityapp.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final PersonDetailsService personDetailsService;
	
	@Autowired	
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Замените NoOpPasswordEncoder.getInstance() на вашу реализацию PasswordEncoder
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(personDetailsService);

		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
		
//		http.csrf(AbstractHttpConfigurer::disable);
		
        http.authenticationManager(authenticationManager)
        .authorizeHttpRequests((authz) -> authz
//        		.requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/", "/auth/**").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
        );
		
        http.formLogin((formLogin) ->
        formLogin
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
        		);
        
        http.logout((logout) ->
        logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
        		);
		
		return http.build();
	}

	
	
}
