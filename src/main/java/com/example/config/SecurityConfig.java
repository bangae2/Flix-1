package com.example.config;

import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

/**
 * Created by bangae1 on 2016-07-05.
 */
@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = {"com.example.repository"})
@ComponentScan(basePackages = {"com.example"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsersService usersService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(new ShaPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**/*").antMatchers("/js/*").antMatchers("/css/*").antMatchers("/videos/*")
        .antMatchers("/img/*").antMatchers("/fonts/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and()
                .csrf().disable().formLogin().loginPage("/login").passwordParameter("password").usernameParameter("id")
                .loginProcessingUrl("/loginProcess").permitAll().defaultSuccessUrl("/").failureHandler(customAuthenticationFailureHandler()).defaultSuccessUrl("/").permitAll()
                .and().logout().deleteCookies("JSESSIONID").logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and().rememberMe().tokenValiditySeconds(1209600).rememberMeParameter("remember-me").key("uniqueAndSecret");

    }

    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        AuthenticationFailureHandler fail = new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("login Filed ::: " + exception.getMessage());
                try {
                    response.sendRedirect("/login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return fail;
    }

    @Bean
    public FilterRegistrationBean getSpringSecurityFilterChainBindedToError(
            @Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(springSecurityFilterChain);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
}
