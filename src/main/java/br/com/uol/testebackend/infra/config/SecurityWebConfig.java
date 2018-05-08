package br.com.uol.testebackend.infra.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Configurações de seguranca da aplicação com spring boot
 */
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
   
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/resources/**", "/webjars/**").permitAll()
            .antMatchers(HttpMethod.GET, "/","/players","/players/form","/player/**").permitAll()
            .antMatchers(HttpMethod.POST, "/player").permitAll()
            .antMatchers(HttpMethod.PUT, "/player").permitAll()
            .antMatchers(HttpMethod.DELETE, "/player").permitAll()
            .anyRequest().authenticated()
        .and()
        .httpBasic();
  }
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST,"/player/**");
    }
  
  
}
