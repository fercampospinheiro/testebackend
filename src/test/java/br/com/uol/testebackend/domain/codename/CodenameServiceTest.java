package br.com.uol.testebackend.domain.codename;

import br.com.uol.testebackend.domain.player.Player;
import br.com.uol.testebackend.domain.player.PlayerRepository;
import br.com.uol.testebackend.domain.player.TypeGroup;
import br.com.uol.testebackend.infra.config.Boot;
import java.util.Optional;
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
 * Teste do servico de codinomes
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Boot.class)
public class CodenameServiceTest {
    
    @Inject private CodenameService codenameService;
    @Inject private PlayerRepository playerRepository;
    private Player player;
    
    public CodenameServiceTest() {
    }
    
    @Before
    public void setUp() {
        player = new Player("fercampospinheiro@gmail.com", "11951662366", "Thor", TypeGroup.AVANGERS);
        playerRepository.save(player);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Deve retornar um codinome do tipo vingadores
     * @throws java.lang.Exception
     */
    @Test
    public void shouldReturnOneCodenameAvangersType() throws Exception {

        Optional<Codename> codename = codenameService.getCodenameAvaliable(TypeGroup.AVANGERS);
        assertThat(codename.isPresent(), is(true));

    }
    
}
