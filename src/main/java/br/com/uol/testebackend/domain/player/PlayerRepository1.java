
package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.Codename;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de jogadores
 */
@Repository
public class PlayerRepository1 {
    
    @PersistenceContext
    private EntityManager em;

    public void add(Player player) {
        em.persist(player);
    }

 
    public void update(Player player) {
        em.merge(player);
    }

 
    public Player getById(Integer id) {
        return em.find(Player.class, id);
    }

 
    public void delete(Integer id) {
        Player player = getById(id);
        if (player != null) {
            em.remove(player);
        }
    }
    
    public List<Codename> searchAllAlreadyUsed(){
        String jpql = "select new br.com.uol.testebackend.domain.codename.Codename(p.codename) from Player p group by p.codename" ;
        TypedQuery<Codename> query = em.createQuery(jpql, Codename.class);
        return query.getResultList();
    }
    
    
}
