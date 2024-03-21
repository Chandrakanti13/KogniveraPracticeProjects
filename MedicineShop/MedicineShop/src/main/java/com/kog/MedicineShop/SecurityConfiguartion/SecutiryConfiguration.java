//package com.kog.MedicineShop.SecurityConfiguartion;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import static  org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecutiryConfiguration {
//
//    //User Creation
//    @Bean
//    UserDetailsService userDetalisService(PasswordEncoder encoder)
//	{
//		//InMemoryUserDetailsManager
//		UserDetails admin=User.withUsername("Chandrak")
//				.password(encoder.encode("Bca13"))
//				.roles("ADMIN","USER")
//				.build();
//		
//		UserDetails user=User.withUsername("Rhj")
//				.password(encoder.encode("cde46"))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
//		
//	}
//
//    //Configuring HttpSecurity
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//    {
//    	return http.csrf(csrf->csrf.disable())
//    			.authorizeHttpRequests(requests->requests
//    					.requestMatchers("/api/welcome").permitAll())
//    			.authorizeHttpRequests(requests->requests.requestMatchers("/api/user/**").authenticated())
//    			.authorizeHttpRequests(requests->requests.requestMatchers("/api/admin/**").authenticated()).formLogin(withDefaults()).build();
//    }
//
//    //Password Encoding
//    @Bean
//    PasswordEncoder passwordEncoder() {
//    	return new BCryptPasswordEncoder();
//    }
//	
//
//}
