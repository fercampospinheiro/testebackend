package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.Codename;
import java.util.List;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author fernando
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class shouldSearchCodenamesAlreadyUsed {
    
    @Inject private PlayerRepository playerRepository;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void shouldSearchCodenamesAlreadyUsed() {
        Player player = new Player("fercampospinheiro@gmail.com", "11951662366", "batman", TypeGroup.AVANGERS);
        playerRepository.save(player);
        
        List<Codename> codenames = playerRepository.searchAllAlreadyUsed();
        
        assertThat(codenames.size(), is(1));
        assertThat(codenames.get(0).getCodinome(), is("batman"));
    }
    
    @After
    public void tearDown() {
    }
    
}
