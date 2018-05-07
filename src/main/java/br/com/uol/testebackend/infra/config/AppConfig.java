package br.com.uol.testebackend.infra.config;

import br.com.uol.testebackend.domain.codename.CodenameRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javax.inject.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Classe de configuracao e disponibilizacao de beans pra injecao
 */
@Configuration
public class AppConfig {
    @Bean
    public CodenameRepository getCodenameRepository(){
        return new CodenameRepository(new ClientHttpUrl());
    }
    
    @Bean(name = "jsonMapper")
    @Primary
    public ObjectMapper getObjectMapper(){
        ObjectMapper   jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        jsonMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        
        return jsonMapper;
    }
    
    @Bean(name="xmlMapper")
    public XmlMapper getXmlMapper(){
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        return xmlMapper;
    }
    
    
}
