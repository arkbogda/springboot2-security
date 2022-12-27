package pl.azbn.springboot2security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // the method allows you to define the source of users, i.e. LDAP, DB, inMemory
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // provided user class, must have at least 3 elements: username, password, role; ROLE prefix is required in inMemory; encoder is required
        User userAdmin = new User("Jan",
                getPasswordEncoder().encode("Jan123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        User userUser = new User("Karol",
                getPasswordEncoder().encode("Karol123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(userUser);
    }

    // request authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .antMatchers("/forUser").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/forAll");
    }
}
