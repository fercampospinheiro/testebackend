package br.com.uol.testebackend.infra.config;

import br.com.uol.testebackend.domain.codename.CodenameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuracao e disponibilizacao de beans pra injecao
 */
@Configuration
public class AppConfig {
    @Bean
    public CodenameRepository getCodenameRepository(){
        return new CodenameRepository(new ClientHttpUrl());
    }
    
    
}
