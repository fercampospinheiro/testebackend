package br.com.uol.testebackend.domain.player;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Servico responsavel por fazer o registrar um jogador novo ou recuperar
 * dados de jogadores já existentes
 */
@Service
public class PlayerService {
    
    @Inject private PlayerRepository playerRepository;
    
    public Optional<Player> registerOrUpdate(Player player){
        return Optional.ofNullable(playerRepository.save(player));
    } 
    
    public  List<Player> getAll(){
        return playerRepository.findAll();
    }
    
    public ResponseEntity<Player> getById(Integer id){
        Optional<Player> player = playerRepository.findById(id);
        
        if(player.isPresent()){
            return new ResponseEntity<>(player.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(player.get(),HttpStatus.NO_CONTENT);
        }
        
    }
    
    public Boolean delete(Player player){
        if(playerRepository.existsById(player.getIdPlayer())){
            playerRepository.delete(player);
            return true;
        }
        return false;
    }
    
}
