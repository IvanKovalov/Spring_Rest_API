package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicAuthWebSecurityConfiguration
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                /*.authorizeRequests(authorize -> authorize
                        .mvcMatchers("/task").hasAnyAuthority("write"))*/
                .authorizeRequests()
                .mvcMatchers("/tasks")
                .hasRole("ADMIN")
                .and()
                .authorizeRequests()
                .mvcMatchers("/hello")
                .hasRole("USER")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails userAdmin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                //.authorities("write", "read")
                .build();
        return new InMemoryUserDetailsManager(user, userAdmin) ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

  /* @Bean
    public DaoAuthenticationConfigurer daoAuthenticationConfigurer(AuthenticationManagerBuilder builder)
            throws Exception {
      return builder.userDetailsService(userDetailsService);
    }

    @Bean
    javax.sql.DataSource dataSource() {
        return (javax.sql.DataSource) new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }




    @Bean
    UserDetailsManager users(javax.sql.DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return users;
    }*/
}