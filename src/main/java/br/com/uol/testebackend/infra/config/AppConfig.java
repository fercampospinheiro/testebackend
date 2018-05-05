package br.com.uol.testebackend.infra.config;

import br.com.uol.testebackend.domain.codename.CodenameRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de configuracao e disponibilizacao de beans pra injecao
 */
@Configuration
public class AppConfig {
    @Bean
    public CodenameRepository getCodenameRepository(){
        return new CodenameRepository();
    }
    
//    @Bean
//    public ClientHttpUrl getClientHttpUrl() {
//       return new ClientHttpUrl();
//    }
    
}
