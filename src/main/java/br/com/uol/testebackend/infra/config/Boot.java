package br.com.uol.testebackend.infra.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("br.com.uol")
@EntityScan(basePackages = "br.com.uol.testebackend.domain")
@EnableJpaRepositories(basePackages = "br.com.uol.testebackend")
@SpringBootApplication()
public class Boot{
   public static void main(String[] args){
      SpringApplication.run(Boot.class,args);
   }
}
