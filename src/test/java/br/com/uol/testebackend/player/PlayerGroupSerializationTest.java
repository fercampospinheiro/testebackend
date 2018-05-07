/*
 * Teste para validar processo de serializacao e deserialicao dos grupos dos jogadores
 * Para os teste está sendo considerado o jackson implementaçao utlizada pelo Spring
 */
package br.com.uol.testebackend.player;

import br.com.uol.testebackend.domain.codename.AvangersGroup;
import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.domain.codename.JusticeLeagueGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testa os processos de serializacao dos tipos de grupos em xml e json
 */
public class PlayerGroupSerializationTest {
    
    private final String AVANGERS_TEMPLATE_JSON = "{\"vingadores\":[{\"codinome\":\"Hulk\"},{\"codinome\":\"Capitão América\"}]}";
    private final String JUSTICE_LEAGUE_TEMPLATE_XML = "<liga_da_justica><codinomes><codinome>Lanterna Verde</codinome><codinome>Flash</codinome></codinomes></liga_da_justica>";
    private ObjectMapper jsonMapper;
    private XmlMapper xmlMapper;
    
    @Before
    public void setUp() {
        jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        jsonMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        
        xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        
    }
    /**
     * Deve serializar um grupo do tipo Vingadores para o template json 
     */
    @Test
    public void shouldSerializeAvangersGroupToTemplateJson() throws JsonProcessingException{

        Codename c1 = new Codename();
        c1.setCodinome("Hulk");
 
        Codename c2 = new Codename();
        c2.setCodinome("Capitão América");
        
        AvangersGroup avangersGroup = new AvangersGroup();
        avangersGroup.add(c1);
        avangersGroup.add(c2);
        
        String jsonAvangers = jsonMapper.writeValueAsString(avangersGroup); 
        assertEquals(jsonAvangers,AVANGERS_TEMPLATE_JSON);
    }
    
    /**
     * Deve deserializar o template para objeto do grupo do tipo Vingadores 
     */
    @Test
    public void shouldDeserializeTemplateJsonToAvangersGroup() throws JsonProcessingException, IOException{

        AvangersGroup avangersGroupResult = jsonMapper.readValue(AVANGERS_TEMPLATE_JSON, AvangersGroup.class);
        
        Codename c1 = new Codename();
        c1.setCodinome("Hulk");
 
        Codename c2 = new Codename();
        c2.setCodinome("Capitão América");
        
        AvangersGroup avangersGroup = new AvangersGroup();
        avangersGroup.add(c1);
        avangersGroup.add(c2);

        assertEquals(avangersGroup,avangersGroupResult);
    }
    
        /**
     * Deve serializar um grupo do tipo Liga da justica para o template xml 
     */
    @Test
    public void shouldSerializeJusticeLeagueGroupToTemplateXml() throws JsonProcessingException  {

        Codename laternaVerde = new Codename();
        laternaVerde.setCodinome("Lanterna Verde");
 
        Codename flash = new Codename();
        flash.setCodinome("Flash");
        
        JusticeLeagueGroup justiceLeagueGroup = new JusticeLeagueGroup();
        justiceLeagueGroup.setCodinomes(Lists.newArrayList("Lanterna Verde","Flash"));
        
        String xmlJusticeLeague = xmlMapper.writeValueAsString(justiceLeagueGroup); 
        assertEquals(xmlJusticeLeague ,JUSTICE_LEAGUE_TEMPLATE_XML );
    }
    
    /**
     * Deve deserializar o template xml para objeto do grupo do tipo Liga da Justica
     */
    @Test
    public void shouldDeserializeTemplateXmlToJusticeLeagueGroup() throws IOException {

        JusticeLeagueGroup justiceLeagueGroup = new JusticeLeagueGroup();
        List<String> codenames = Lists.newArrayList("Lanterna Verde","Flash");
        justiceLeagueGroup.setCodinomes(codenames);
        
        JusticeLeagueGroup justiceGroupResult = xmlMapper.readValue(JUSTICE_LEAGUE_TEMPLATE_XML, JusticeLeagueGroup.class);
        
        assertEquals(justiceLeagueGroup,justiceGroupResult);
    }
    
    
    
    @After
    public void tearDown() {
    }
    
}
