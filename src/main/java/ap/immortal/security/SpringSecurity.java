package ap.immortal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SpringSecurity {
	
	@Bean
	InMemoryUserDetailsManager userDetailsManger() {
		UserDetails amit = User.builder().username("amit").password("{noop}amit").roles("EMPLOYEE").build();
		UserDetails amar = User.builder().username("amar").password("{noop}amar").roles("ADMIN").build();
		UserDetails avi = User.builder().username("avi").password("{noop}avi").roles("MANAGER").build();
		return new InMemoryUserDetailsManager(amar,amit,avi);
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
				).logout(logout -> logout.permitAll());
		return http.build();
	}
	
	

}
