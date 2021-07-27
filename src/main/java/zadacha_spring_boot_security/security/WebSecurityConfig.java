package zadacha_spring_boot_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import zadacha_spring_boot_security.security.handler.SuccessUserHandler;
import zadacha_spring_boot_security.service.UserDetailsImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsImpl userDetailsImpl; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    @Autowired
    public WebSecurityConfig(UserDetailsImpl userDetailsImpl, SuccessUserHandler successUserHandler) {
        this.userDetailsImpl = userDetailsImpl;
        this.successUserHandler = successUserHandler;

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsImpl).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login").anonymous()
                .antMatchers("/","/admin").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/","/user").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .anyRequest().authenticated().and().formLogin().successHandler(new SuccessUserHandler());
        http.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and().csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);

    }
}
