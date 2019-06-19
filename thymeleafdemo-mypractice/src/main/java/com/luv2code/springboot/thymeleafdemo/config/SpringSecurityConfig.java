package com.luv2code.springboot.thymeleafdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/employees/showFormForAdd").hasAnyRole("MANAGER" ,"RADMIN")
				.antMatchers("/employees/list").hasAnyRole("EMPLOYEE","MANAGER" ,"ADMIN")
				.antMatchers("/employees/saveEmployee").hasAnyRole("MANAGER" ,"ADMIN")
				.antMatchers("/employees/showFormForUpdate").hasAnyRole("MANAGER" ,"ADMIN")
				.antMatchers("/employees/showFormForDelete").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showLoginForm")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout()
				.clearAuthentication(true)
				.permitAll()
			.and()
				.exceptionHandling()
				.accessDeniedPage("/accessDenied");
	}
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//use jdbc authentication
		
		auth.jdbcAuthentication().dataSource(securityDataSource)
			.usersByUsernameQuery("select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery("select username, authority from authorities where username=?");
	}
	
	
}
