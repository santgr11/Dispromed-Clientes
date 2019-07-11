package com.santg.disproclientes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add users
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("dispromed").password("disprotest").roles("JEFE", "VENDEDOR"))
			.withUser(users.username("vendedor").password("disprotest").roles("VENDEDOR"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasRole("VENDEDOR")
			.antMatchers("/agregarCliente").hasRole("JEFE") //only JEFES can acces the create Cliente page	
			.antMatchers("/borrarComentario").hasRole("JEFE") // only JEFES can delete a Comentario
			.and()
			.formLogin()
				.loginPage("/login") // where is the login form
				.loginProcessingUrl("/authenticate") // where we send the login data (POST)
				.permitAll() // everyone can see the login page
			.and()
			.logout().permitAll();
	}
	
	

}
