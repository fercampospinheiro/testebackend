package br.com.uol.testebackend.infra.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.uol.testebackend.presentation"})
public class Boot{

   public static void main(String[] args){
      SpringApplication.run(Boot.class, args);
   }
}
