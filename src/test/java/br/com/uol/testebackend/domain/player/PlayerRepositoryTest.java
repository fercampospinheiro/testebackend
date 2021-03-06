package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.infra.config.Boot;
import java.util.List;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Teste do repositorio de jogadores
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Boot.class)
public class PlayerRepositoryTest {
    
    @Inject private PlayerRepository playerRepository;
    
    
    @Before
    public void setUp() {
        Player player = new Player("fercampospinheiro@gmail.com", "11951662366", "Thor", TypeGroup.AVANGERS , "Fernando");
        playerRepository.save(player);
    }
    
    
    /**
     * Deve trazer todos os codinomes já usados
     */
    @Test
    public void shouldSearchCodenamesAlreadyUsed() {
        List<Codename> codenames = playerRepository.searchAllAlreadyUsed();
        
        assertThat(codenames.size(), is(1));
        assertThat(codenames.get(0).getCodinome(), is("Thor"));
    }
    
    @After
    public void tearDown() {
    }
    
}
