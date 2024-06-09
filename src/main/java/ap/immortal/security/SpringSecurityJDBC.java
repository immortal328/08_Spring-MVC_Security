package ap.immortal.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityJDBC {
	
	//Use JDBC Database 
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
		http.authorizeHttpRequests(
				configure -> configure
			                .requestMatchers("/").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
			                .requestMatchers("/leaders/**").hasRole("MANAGER")
			                .requestMatchers("/systems/**").hasRole("ADMIN")
			                .anyRequest().authenticated()
				).formLogin(
						form -> form
								.loginPage("/showMyLoginForm")
								.loginProcessingUrl("/authenticateTheUser")
								.permitAll()
				).logout(
						logout -> logout
								  .permitAll()
				).exceptionHandling(
						configure -> configure
						 .accessDeniedPage("/access-denied"));
		
		return http.build();
	}
	
	



}
