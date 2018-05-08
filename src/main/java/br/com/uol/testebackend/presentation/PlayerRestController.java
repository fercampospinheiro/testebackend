package br.com.uol.testebackend.presentation;

import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.domain.codename.CodenameService;
import br.com.uol.testebackend.domain.player.Player;
import br.com.uol.testebackend.domain.player.PlayerService;
import br.com.uol.testebackend.domain.player.TypeGroup;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador restfull das acoes com jogadores
 */
@RestController
public class PlayerRestController {

    @Inject private CodenameService codenameService;
    @Inject private PlayerService playerService; 
            

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Player>> list() {
        
        List<Player> players = playerService.getAll();
        
        if(players.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
    }
    
    @PostMapping(value = "/player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> register(@RequestBody Player player) throws IOException {
        
        Optional<Codename> codename = codenameService.getCodenameAvaliable(player.getPlayerGroup());
        
        return codename.map(c ->{ 
            player.setCodename(c.getCodinome());
            
            return playerService.registerOrUpdate(player)
                .map( p -> {
                    return new ResponseEntity<>(p,HttpStatus.CREATED);
                })
                .orElse( new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
            
        })
        .orElse(new ResponseEntity<>( HttpStatus.PRECONDITION_FAILED));
    }
    
    @PutMapping(value = "/player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> update(@RequestBody Player player) throws IOException {
        
        return playerService.registerOrUpdate(player)
                .map( p -> {
                    return new ResponseEntity<>(p,HttpStatus.OK);
                })
                .orElse( new ResponseEntity<>(HttpStatus.NOT_MODIFIED));

    }
    
    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Player> getById(@PathVariable Integer id) {
         Player player = new Player("fercampospinheiro@gmail.com", "11951662366", "Thor", TypeGroup.AVANGERS);
         return new ResponseEntity<>(player,HttpStatus.OK);
        //return playerService.getById(id);
    }
    
    @DeleteMapping(value = "/player", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Player> delete(@RequestBody Player player) {
         if(playerService.delete(player)) return new ResponseEntity<>(HttpStatus.OK) ;
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}