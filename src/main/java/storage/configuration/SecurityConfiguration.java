package storage.configuration;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import storage.constant.SecurityConstant;
import storage.filter.JwtAuthorizationFilter;
import storage.service.CustomUserDetailsService;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration{
	
	private final JwtAuthorizationFilter jwtAuthFilter;
	private CustomUserDetailsService customUserDetailsService;
	
	
	
	public SecurityConfiguration(JwtAuthorizationFilter jwtAuthFilter, CustomUserDetailsService customUserDetailsService) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
		this.customUserDetailsService = customUserDetailsService;
	}
	
	 @Bean
	 CorsConfigurationSource corsFilter() {
			UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
			CorsConfiguration corsConfiguration = new CorsConfiguration();
			corsConfiguration.setAllowCredentials(true);
			corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
			corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type",
					"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
					"Access-Control-Request-Method", "Access-Control-Request-Headers"));
			corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
					"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
			corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
			return urlBasedCorsConfigurationSource;
		}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(customizer -> customizer.disable())
		    .authorizeHttpRequests(auth->{
			                       auth.requestMatchers(SecurityConstant.PUBLIC_URLS).permitAll();
			                       auth.anyRequest().authenticated();	
		   })
		  .cors(c->c.configurationSource(corsFilter()))  
		  .httpBasic(Customizer.withDefaults())
		  .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  .authenticationProvider(authenticationProvider())
		  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		  .build();											
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		return authenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
		
	}

}
