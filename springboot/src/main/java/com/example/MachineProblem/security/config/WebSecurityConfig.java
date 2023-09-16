package com.example.MachineProblem.security.config;

import org.springframework.context.annotation.Configuration;

import com.example.MachineProblem.security.PasswordEncoder;
import com.example.MachineProblem.service.CustomerService;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers("/api/v*/registration/**", "/h2/**", "/h2/*", "/h2-console/**", "/h2-console/*")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .formLogin();
//
//        //For h2 console
//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  	    http.cors().configurationSource(request -> {
  	        CorsConfiguration configuration = new CorsConfiguration();
  	        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
  	        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH","OPTIONS"));
  	        configuration.setAllowCredentials(true);
  	        configuration.addExposedHeader("Message");
  	        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
  	        return configuration;
  	    }).and().csrf().disable()
  	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  	        .authorizeHttpRequests(auth -> 
  	          auth.requestMatchers("/add-to-cart").permitAll()
  	              .requestMatchers("/displayCart").permitAll()
  	          	  .requestMatchers("/update-quantity").permitAll()
  	          	  .requestMatchers("/deleteCartItem/**").permitAll()
  	          	  .requestMatchers("/deleteCartByUser").permitAll()
  	          	  .requestMatchers("/getLogin").permitAll()
  	          	  .requestMatchers("/addSignUp").permitAll()
  	              .requestMatchers("/addLogin").permitAll()
  	              .requestMatchers("/getProducts").permitAll()
	          	  .requestMatchers("/addProducts").permitAll()
	          	  .requestMatchers("/product/**").permitAll()
	          	  .requestMatchers("/deleteProduct/**").permitAll()
	          	  .requestMatchers("/searchProduct").permitAll()
	          	  .requestMatchers("/receipt").permitAll()
	          	  .requestMatchers("/getReceipt/**").permitAll()
	          	  .requestMatchers("/getReceipts").permitAll()
	          	  .requestMatchers("/confirm").permitAll()
	          	  .requestMatchers("/{token}").permitAll()
	          	  .requestMatchers("/api/tokens/**").permitAll()
	          	  .requestMatchers("searchToken").permitAll()
  	              .anyRequest().authenticated()
          );
//      
//      http.authenticationProvider(authenticationProvider());

//      http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
      
      return http.build();
    }
  
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
//        provider.setUserDetailsService(customerService);
//
//        return provider;
//    }
}
