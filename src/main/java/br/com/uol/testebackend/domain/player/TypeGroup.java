package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.AvangersGroup;
import br.com.uol.testebackend.domain.codename.JusticeLeagueGroup;
import br.com.uol.testebackend.domain.codename.PlayerGroup;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *Enum que representa os tipo de grupos de jogadores
 */
public enum TypeGroup {
    AVANGERS("Os Vingadores","vingadores.json", MediaType.APPLICATION_JSON) {

        @Override
        public PlayerGroup getPlayerGroup(Map<MediaType,RestTemplate> mapResttemplate) {
            return mapResttemplate.get(getMediaType()).getForObject(getUrlFiles() + getFileName(), AvangersGroup.class);
        }
    },
    JUSTICE_LEAGUE("Liga da Justiça","liga_da_justica.xml",MediaType.APPLICATION_XML) {

        @Override
        public PlayerGroup getPlayerGroup(Map<MediaType,RestTemplate> mapResttemplate) {
            return mapResttemplate.get(getMediaType()).getForObject(getUrlFiles() + getFileName(), JusticeLeagueGroup.class);
        }
    };
    
    private final String name;
    private final String fileName;
    private final MediaType mediaType;
    private final String urlFiles = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/";
     
    private TypeGroup(String name, String fileName, MediaType mediaType) {
        this.name = name;
        this.fileName = fileName;
        this.mediaType = mediaType;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrlFiles() {
        return urlFiles;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
 
    public abstract PlayerGroup getPlayerGroup(Map<MediaType,RestTemplate> mapResttemplate);
}
