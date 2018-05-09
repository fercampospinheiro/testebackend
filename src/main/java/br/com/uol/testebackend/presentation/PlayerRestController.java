package br.com.uol.testebackend.presentation;

import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.domain.codename.CodenameService;
import br.com.uol.testebackend.domain.player.Player;
import br.com.uol.testebackend.domain.player.PlayerService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador restfull da api de jogadores
 */
@RestController()
@RequestMapping("/api/v1")
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
    public ResponseEntity<Player> register(@RequestBody @Valid Player player) throws IOException {
        
        Optional<Codename> codename = codenameService.getCodenameAvaliable(player.getPlayerGroup());
        
        if(playerService.existis(player)) return new ResponseEntity<>( HttpStatus.ALREADY_REPORTED);
        
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
    public ResponseEntity<Player> update(@RequestBody @Valid Player player) throws IOException {
        
        return playerService.registerOrUpdate(player)
                .map( p -> {
                    return new ResponseEntity<>(p,HttpStatus.OK);
                })
                .orElse( new ResponseEntity<>(HttpStatus.NOT_MODIFIED));

    }
    
     @PatchMapping(value = "/player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> updateByFields(@RequestBody Player player) throws IOException {
        
        return playerService.registerOrUpdateFields(player)
                .map( p -> {
                    return new ResponseEntity<>(p,HttpStatus.OK);
                })
                .orElse( new ResponseEntity<>(HttpStatus.NOT_MODIFIED));

    }
    
    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getById(@PathVariable Integer id) {
        
        return playerService.getById(id).map(p -> {
            return new ResponseEntity<>(p,HttpStatus.OK);
        })
        .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }
    
    @DeleteMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> delete(@RequestBody Player player) {
         if(playerService.delete(player)) return new ResponseEntity<>(HttpStatus.OK) ;
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}