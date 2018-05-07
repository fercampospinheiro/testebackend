/*
 * Testes do repositorio de codinomes
 */
package br.com.uol.testebackend.domain.codename;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.uol.testebackend.domain.player.TypeGroup;
import br.com.uol.testebackend.infra.config.ClientHttpUrl;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Teste do repositorio de codinomes
 */
public class CodenameRepositoryTest {
    
    private CodenameRepository codenameRepository; 
    
    public CodenameRepositoryTest() {
    }
    
    @Before
    public void setUp() {
        codenameRepository = new CodenameRepository(new ClientHttpUrl());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Deve listar todos os codinomes dos arquivos json ou xml coforme os grupo Vingadore e Liga da Justica
     */
    @Test
    public void shoudListCodinamesByAvangersAndndJusticeLeagueGroup() throws IOException {
        PlayerGroup<Codename> avangersGroup = codenameRepository.listByGroup(TypeGroup.AVANGERS);
        assertThat(avangersGroup.getCodenames().size(), is(7));
        
        PlayerGroup<Codename> justiceGroup = codenameRepository.listByGroup(TypeGroup.JUSTICE_LEAGUE);
        assertThat(justiceGroup.getCodenames().size(), is(6));
        
    }
    
}
