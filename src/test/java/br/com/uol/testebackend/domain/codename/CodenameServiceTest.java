package br.com.uol.testebackend.domain.codename;

import br.com.uol.testebackend.domain.player.TypeGroup;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Teste do servico de codinomes
 */
public class CodenameServiceTest {
    
    public CodenameServiceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCodenameAvaliable method, of class CodenameService.
     */
    @Test
    public void testGetCodenameAvaliable() throws Exception {
        System.out.println("getCodenameAvaliable");
        TypeGroup group = null;
        CodenameService instance = new CodenameService();
        Optional<Codename> expResult = null;
        Optional<Codename> result = instance.getCodenameAvaliable(group);
        assertEquals(expResult, result);

        fail("The test case is a prototype.");
    }
    
}
