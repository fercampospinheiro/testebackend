/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uol.testebackend.player;

import br.com.uol.testebackend.domain.codename.AvangersGroup;
import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.domain.codename.JusticeLeagueGroup;
import br.com.uol.testebackend.domain.player.TypeGroup;
import br.com.uol.testebackend.infra.config.Boot;
import com.google.common.collect.Lists;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Testa serializaçao de objetos do grupo do jogador com resttemplate do Spring
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Boot.class)
public class SerializeRestemplatePlayerGroup {
    
    @Inject @Qualifier("restJSON") private RestTemplate restJSON;
    @Inject @Qualifier("restXML") private RestTemplate restXML;
    private AvangersGroup avangersExpected;
    private JusticeLeagueGroup justiceLeagueExspected;
    
    private final String URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/";
    
    public SerializeRestemplatePlayerGroup() {
    }
    
    @Before
    public void setUp() {
        avangersExpected  = new AvangersGroup();
        avangersExpected.add(new Codename("Hulk"));
        avangersExpected.add(new Codename("Capitão América"));
        avangersExpected.add(new Codename("Pantera Negra"));
        avangersExpected.add(new Codename("Homem de Ferro"));
        avangersExpected.add(new Codename("Thor"));
        avangersExpected.add(new Codename("Feiticeira Escarlate"));
        avangersExpected.add(new Codename("Visão")); 
        
        justiceLeagueExspected = new JusticeLeagueGroup();
        justiceLeagueExspected.setCodinomes(
                Lists.newArrayList("Lanterna Verde","Flash","Aquaman","Batman","Superman","Mulher Maravilha")
        );
         
    }
    
    /**
     *  Grupo vinagadores retornado da url deve ser a lista experada 
     */
    @Test
    public void returnAvangerGroupOfUrlShouldBeObjectList(){
        AvangersGroup avangers = restJSON.getForObject(URL + TypeGroup.AVANGERS.getFileName() , AvangersGroup.class);
        
        assertThat(avangers, is(avangersExpected));
    }
    
     /**
     *  Grupo liga da justica retornado da url deve ser a lista experada 
     */
    @Test
    public void returnJusticeLeagueGroupOfUrlShouldBeObjectList(){
        JusticeLeagueGroup justiceLeagueGroup = restXML.getForObject(URL + TypeGroup.JUSTICE_LEAGUE.getFileName(), JusticeLeagueGroup.class);
        
        assertThat(justiceLeagueGroup, is(justiceLeagueExspected));
    }
    
    
    @After
    public void tearDown() {
    }
    
}
