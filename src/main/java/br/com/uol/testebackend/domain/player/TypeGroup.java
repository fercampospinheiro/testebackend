package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.AvangersGroup;
import br.com.uol.testebackend.domain.codename.JusticeLeagueGroup;
import br.com.uol.testebackend.domain.codename.PlayerGroup;
import br.com.uol.testebackend.infra.config.ClientHttpUrl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 *Enum que representa os tipo de grupos de jogadores
 */
public enum TypeGroup {
    AVANGERS("Os Vingadores","vingadores.json") {

        @Override
        public PlayerGroup getPlayerGroup(ClientHttpUrl client ,String url) {
            AvangersGroup avangers = new AvangersGroup() ;
            
            try {
                HttpHeaders header = new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON_UTF8);
                String json = client.getBodyByUrl(url + getFileName(),header);
                
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
                jsonMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
                jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            
                avangers = jsonMapper.readValue(json, AvangersGroup.class);
                
            } catch (IOException ex) {
                Logger.getLogger(TypeGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
            return avangers;
        }
    },
    JUSTICE_LEAGUE("Liga da Justiça","liga_da_justica.xml") {

        @Override
        public PlayerGroup getPlayerGroup(ClientHttpUrl client,String url) {
            JusticeLeagueGroup justiceGroup = new JusticeLeagueGroup() ;
            
            try {
                HttpHeaders header = new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_XML);
                String xml = client.getBodyByUrl(url + getFileName(),header);
                
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
                jsonMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
                jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.setDefaultUseWrapper(false);
                justiceGroup = xmlMapper.readValue(xml, JusticeLeagueGroup.class);
                
            } catch (IOException ex) {
                Logger.getLogger(TypeGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
            return justiceGroup;
        }
    };
    
    private String name;
    private String fileName;
    
    private TypeGroup(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }
    
    public abstract PlayerGroup getPlayerGroup(ClientHttpUrl client,String url);
}
