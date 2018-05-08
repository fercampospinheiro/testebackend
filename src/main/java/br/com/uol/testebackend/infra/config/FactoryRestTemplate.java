package br.com.uol.testebackend.infra.config;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

/**
 * Fabrica que fornece um tipo especifico de resttemplate injetado e pronto para ser usado
 */

public class FactoryRestTemplate {
    
    @Inject @Qualifier("restXML") private RestTemplate restXML;
    @Inject @Qualifier("restJSON") private RestTemplate restJSON;
    private static FactoryRestTemplate factory;

    private FactoryRestTemplate() {
    }

    public static FactoryRestTemplate getInstance(){
        if(factory == null){
            factory = new FactoryRestTemplate();
        }
        return factory;
    }

    public RestTemplate xml() {
        return restXML;
    }

    public RestTemplate json() {
        return restJSON;
    }
  
    
}
