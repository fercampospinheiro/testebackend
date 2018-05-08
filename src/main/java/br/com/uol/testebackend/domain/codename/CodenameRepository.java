package br.com.uol.testebackend.domain.codename;

import br.com.uol.testebackend.domain.player.TypeGroup;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Repositorio de condinomes
 */
@Repository("codenameRepository")
public class CodenameRepository {
    
    @Inject @Qualifier("restXML") private RestTemplate restXML;
    @Inject @Qualifier("restJSON") private RestTemplate restJSON;
    private Map<MediaType,RestTemplate> mapsRestemplate;
    
    @PostConstruct
    public void initialise() {
        mapsRestemplate = Maps.newHashMap();
        mapsRestemplate.put(MediaType.APPLICATION_JSON, restJSON);
        mapsRestemplate.put(MediaType.APPLICATION_XML, restXML);
    }
    
    /**
     * Obtem a relacao de codinome conforme o tipo de grupo informado, buscando na url padronizada no application.properties  
     * @param group
     * @return 
     */
    public PlayerGroup<Codename> listByGroup(TypeGroup group) throws IOException {
        return group.getPlayerGroup(mapsRestemplate);
    }
   
}
