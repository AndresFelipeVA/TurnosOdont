package com.andresFVA.turnosOdont.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class TurnOdontSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2").hasRole("ADMIN")//Habilitar acceso a la consola h2
                .antMatchers("/dentists/*").hasRole("ADMIN")//Creación, borrado o búsqueda 1 dentist
                .antMatchers("/patients/*").hasRole("ADMIN")//Creación, borrado o búsqueda 1 patient
                .antMatchers(HttpMethod.POST,"/api/v1/dentists").hasRole("ADMIN")//API Creación dentist
                .antMatchers(HttpMethod.POST,"/api/v1/patients").hasRole("ADMIN")//API Creación patient
                .antMatchers("/api/v1/dentists/*").hasRole("ADMIN")//API Borrado o búsqueda 1 dentist
                .antMatchers("/api/v1/patients/*").hasRole("ADMIN")//API Borrado o búsqueda 1 patient
                .antMatchers("/images/**").permitAll() //Para poder acceder a las imágenes
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")//El form tiene que incluir un token CSRF Token, Thymeleaf lo incluye automáticamente
                .loginProcessingUrl("/login").permitAll()
                .failureUrl("/login?error=si")
                .defaultSuccessUrl("/", true)

                .and()
                //.cors().and()
                .csrf().disable()//Para acceder desde Postman .ignoringAntMatchers("/h2")//Habilitar acceso a la consola h2
                .headers().frameOptions().sameOrigin()//Habilitar acceso a la consola h2, que usa marcos

                .and()
                .httpBasic()//Basic Authentication para acceder desde una API REST

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=si")//Puede ser cualquiera igual al no estar autentificado va a login
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
                //.logoutUrl("logout")  //Esto lo reemplazamos con el AntPathRequestMatcher
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(myPasswordEncoder());
    }

    private PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
