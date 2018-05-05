package br.com.uol.testebackend.presentation;

import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.domain.codename.CodenameRepository;
import br.com.uol.testebackend.domain.codename.CodenameService;
import br.com.uol.testebackend.domain.player.Player;
import br.com.uol.testebackend.domain.player.PlayerService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador das acoes com jogadores
 */
@RestController
public class PlayerController {

    @Inject private CodenameService codenameService;
    @Inject private PlayerService playerService; 
            
    /**
     * Carrega o fromulario da lista de jogadores
     */
    @GetMapping("/players/form")
    public String playersForm(){
        return "player/players";
    }
    
    /**
     * Carrega o fromulario de cadastro de jogador novo
     */
    @GetMapping("/player/form")
    public String playerForm(){
        return "player/player-register";
    }
    
    @GetMapping("/players")
    public ResponseEntity<List<Player>> list() {
        return new ResponseEntity<List<Player>>(new ArrayList<Player>(), HttpStatus.OK);
    }
    
    @PostMapping("/player")
    public ResponseEntity<Player> register(@RequestBody Player player) throws IOException {
        Optional<Codename> codename = codenameService.getCodenameAvaliable(player.getPlayerGroup());
        
        if(codename.isPresent()){
            player.setCodename(codename.get().getCodinome());
            return new ResponseEntity<Player>(player, playerService.register(player));
        }
        
        return new ResponseEntity<Player>( HttpStatus.PRECONDITION_FAILED);
    }
}
