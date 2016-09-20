/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Przemek
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, true from User where email=?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from User where email=?");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, true from Employee where email=?")
                .authoritiesByUsernameQuery("select e.email, r.title from role r, employee e  where r.id in"
                            + "(select er.role_id from employee_role er where er.employee_id = e.id ) and e.email = ?");
    }

    //RECEPTIONIST
    //ADMIN
    //COORDINATOR
    //USER
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                
                .antMatchers("/movie/add").hasAnyRole("ADMIN","COORDINATOR")
                .antMatchers("/movie/create").hasAnyRole("ADMIN","COORDINATOR")
                .antMatchers("/movie/**/edit").hasAnyRole("ADMIN","COORDINATOR")
                .antMatchers("/movie/**/delete").hasAnyRole("ADMIN","COORDINATOR")
                
                .antMatchers("/showing/add").hasAnyRole("ADMIN","COORDINATOR")
                .antMatchers("/showing/create").hasAnyRole("ADMIN","COORDINATOR")
                .antMatchers("/showing/**/edit").hasAnyRole("ADMIN","COORDINATOR")
                .antMatchers("/showing/**/delete").hasAnyRole("ADMIN","COORDINATOR")
                
                .antMatchers("/employee/add").hasRole("ADMIN")
                .antMatchers("/employee/create").hasRole("ADMIN")
                .antMatchers("/employee/**/edit").hasRole("ADMIN")
                .antMatchers("/employee/**/delete").hasRole("ADMIN")
                .antMatchers("/employee/**/show").hasRole("ADMIN")
                .antMatchers("/employee").hasRole("ADMIN")
                
                .antMatchers("/user/**/edit").hasRole("ADMIN")
                .antMatchers("/user/**/delete").hasRole("ADMIN")
                .antMatchers("/user/**/show").hasAnyRole("ADMIN","COORDINATOR","RECEPTIONIST","USER")
                .antMatchers("/user").hasAnyRole("ADMIN","COORDINATOR","RECEPTIONIST")
                .antMatchers("/user/myProfile").hasRole("USER")
                .antMatchers("/user/deleteMyProfile").hasRole("USER")
                .antMatchers("/user/editMyProfile").hasRole("USER")
                .antMatchers("/user/myTickets").hasRole("USER")
                
                .antMatchers("/ticket/**/delete").hasAnyRole("ADMIN","COORDINATOR","RECEPTIONIST")
               // .antMatchers("/ticket").hasAnyRole("ADMIN","COORDINATOR","RECEPTIONIST")
               
                .anyRequest().permitAll();
    }
}
