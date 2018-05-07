package br.com.uol.testebackend.domain.codename;

import br.com.uol.testebackend.domain.player.TypeGroup;
import br.com.uol.testebackend.infra.config.ClientHttpUrl;
import java.io.IOException;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Repository;

/**
 * Repositorio de condinomes
 */
@Repository("codenameRepository")
public class CodenameRepository {
   
    private final String URL_LIST_CODINOMES = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/";
    
    private ClientHttpUrl client;
    
    @Inject
    public CodenameRepository(ClientHttpUrl client) {
        this.client = client;
    }
    /**
     * Obtem a relacao de codinome conforme o tipo de grupo informado, buscando na url padronizada no application.properties  
     * @param group
     * @return 
     */
    public PlayerGroup<Codename> listByGroup(TypeGroup group) throws IOException {
        return group.getPlayerGroup(client, URL_LIST_CODINOMES);
    }
   
}
