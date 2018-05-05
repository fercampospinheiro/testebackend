package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.Codename;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface que representa repositorio de jogadores
 */
public interface PlayerRepository extends JpaRepository<Player,Integer>{
    @Query("select new br.com.uol.testebackend.domain.codename.Codename(p.codename) from Player p group by p.codename ")
    List<Codename> searchAllAlreadyUsed();
}
