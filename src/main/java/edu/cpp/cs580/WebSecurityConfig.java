package edu.cpp.cs580;

import edu.cpp.cs580.security.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = CustomUserService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //This will list what links are not going to be authenticate so we will have to put in the registration page which i used as sample
                .antMatchers("/bill/**", "/verificationCode", "/validateCode/**", "/home",
                        "/sendsms", "/registration", "/processRegistration/**",
                        "/css/**", "/image/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //This is the login page i think we need to change the way to do login,
                .formLogin()
                .loginPage("/home")
                .defaultSuccessUrl("/success")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //Needed to make some database calls
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user@cpp.edu").password("password").roles("USER");
          auth.userDetailsService(userDetailsService);

    }
}