package ap.immortal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration 
public class SpringSecurity {
	
	@Bean
	InMemoryUserDetailsManager userDetailsManger() {
		UserDetails amit = User.builder().username("amit").password("{noop}amit").roles("EMPLOYEE").build();
		UserDetails amar = User.builder().username("amar").password("{noop}amar").roles("ADMIN").build();
		UserDetails avi = User.builder().username("avi").password("{noop}avi").roles("MANAGER").build();
		return new InMemoryUserDetailsManager(amar,amit,avi);
	}
	
	

}
