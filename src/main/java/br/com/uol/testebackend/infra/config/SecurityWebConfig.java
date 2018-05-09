package br.com.uol.testebackend.infra.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Configurações de seguranca da aplicação com spring security
 */
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
   
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/resources/**", "/webjars/**").permitAll()
            .antMatchers(HttpMethod.GET, "/","/api/v1/players","/players","/api/v1/player/**" , "/player/form").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/player").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/v1/player").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/v1/player").permitAll()
            .antMatchers(HttpMethod.PATCH, "/api/v1/player").permitAll()
            .anyRequest().authenticated()
        .and()
        .httpBasic();
  }
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST,"/api/v1/player/**")
                .antMatchers(HttpMethod.PUT, "/api/v1/player/**")
                .antMatchers(HttpMethod.DELETE, "/api/v1/player/**")
                .antMatchers(HttpMethod.PATCH, "/api/v1/player/**");
    }
  
  
}
