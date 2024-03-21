package com.kog.SecurityConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Value("${spring.security.user.name}")
	private String userName;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	@Value("${spring.security.user.roles}")
	private String roles;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.authorizeHttpRequests(requests -> requests
	    		.requestMatchers("/user/userLogin").hasAnyRole("ADMIN","USER")
                .requestMatchers("/admin/adminLogin").hasRole("ADMIN")
                .anyRequest().authenticated())
                .httpBasic(withDefaults()).csrf(csrf -> csrf.disable())
                .logout(logout -> logout.logoutUrl("/user/userLogout").permitAll()
                		.logoutSuccessHandler((request, response, authentication) -> {
                	       
                	        response.setStatus(HttpServletResponse.SC_OK);
                	        response.getWriter().write("Successfully logged out from user profile");
                	        response.getWriter().flush();
                	    }))
                .logout(logout -> logout.logoutUrl("/admin/adminLogout").permitAll()
                		.logoutSuccessHandler((request, response, authentication) -> {
                	        
                	        response.setStatus(HttpServletResponse.SC_OK);
                	        response.getWriter().write("Successfully logged out from admin profile");
                	        response.getWriter().flush();
                	    }))
                .build();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails user=User.builder()
				.username(userName)
				.password(passwordEncoder().encode(password))
				.roles(roles)
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityContextLogoutHandler  securityContextLogoutHandler()
	{
		return new SecurityContextLogoutHandler();
	}
	

}
