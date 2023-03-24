package fr.dawan.gestionutilisateur.securitie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auProvider = new DaoAuthenticationProvider();
		auProvider.setUserDetailsService(userDetailsServiceImpl);
		
		// on configure également un PassWordEncoder à utiliser pour encoder les mots de passe
		//avant de les comparer avec ceux stockés en base de données
		auProvider.setPasswordEncoder(passwordEncoder);
		
		System.out.println(auProvider);
		return auProvider;
		
	}
	

		
		/*
	     * SecurityFilterChain est une classe de Spring Security. 
	     * Elle est utilisée pour définir la logique de sécurité pour 
	     * une application Web en spécifiant les filtres
	     *  qui seront appliqués pour chaque requète HTTP.
	     */
	        @Bean
	        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	                http.authorizeHttpRequests()
	                  .requestMatchers("/user/login", 
	                                        "/user/register", 
	                                        "/js/**", 
	                                        "/css/**", 
	                                        "/img/**", 
	                                        "/vendor/**", 
	                                        "/less/**", 
	                                        "/dist/**", 
	                                        "/webjars/**").permitAll()
	                
	                  .and()
	                        .formLogin(form -> form
	                                        .loginPage("/user/login")
	                                        .defaultSuccessUrl("/user/index")
	                                        .loginProcessingUrl("/user/login")
	                                        .failureUrl("/user/login?error=true")
	                                        .permitAll())
	                        .logout(logout -> logout
	                                        .invalidateHttpSession(true)
	                                        .clearAuthentication(true)
	                                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	                                        .logoutSuccessUrl("/user/logout?logout")
	                                        .permitAll());

	        return http.build();
	        }

//	        
//	        http.authorizeHttpRequests().requestMatchers("/").permitAll();
//	        
//	        http.authorizeRequests().requestMatchers("/operations", "/consultercompte").hasAuthority("USER");
//	        http.authorizeRequests().requestMatchers("/saveOperation").hasAuthority("ADMIN")


}
