package br.com.uol.testebackend.infra.config;

import br.com.uol.testebackend.domain.codename.CodenameRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de configuracao e disponibilizacao de beans pra injecao
 */
@Configuration
public class AppConfig {
    
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
    
    @Bean("restJSON")
    @Primary
    public RestTemplate restTemplateJSON() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setMessageConverters(getMessageConverters(getObjectMapper())); 
        
        return restTemplate;
    }
    
    @Bean("restXML")
    public RestTemplate restTemplateXML() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setMessageConverters(getMessageConverters(getXmlMapper())); 
        return restTemplate;
    }
    
    
    public List<HttpMessageConverter<?>> getMessageConverters(ObjectMapper objectMapper){
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();        
        MappingJackson2HttpMessageConverter converterJson = new MappingJackson2HttpMessageConverter();
        converterJson.setObjectMapper(objectMapper);
        converterJson.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
        builder.indentOutput(true);
        MappingJackson2XmlHttpMessageConverter converterXml = new MappingJackson2XmlHttpMessageConverter(builder.build());
        
        messageConverters.add(converterJson);
        messageConverters.add(converterXml);
        
        return  messageConverters;
    } 
    
    public ClientHttpRequestFactory getClientHttpRequestFactory(){
        return  new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    }
    
    
}
