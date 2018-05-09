package br.com.uol.testebackend.domain.player;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import static org.apache.commons.lang3.StringUtils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servico responsavel por fazer o registrar um jogador novo ou recuperar
 * dados de jogadores já existentes
 */
@Service
public class PlayerService {
    
    @Inject private PlayerRepository playerRepository;
    
    
    @Transactional(readOnly = false ,propagation = Propagation.REQUIRED)
    public Optional<Player> registerOrUpdate(Player player){
        return Optional.ofNullable(playerRepository.save(player));
    } 
    
     @Transactional(readOnly = false ,propagation = Propagation.REQUIRED)
    public Optional<Player> registerOrUpdateFields(Player player){
        
        return getById(player.getIdPlayer()).map(p -> { 
        
            if(isNotBlank(player.getCodename())) p.setCodename(player.getCodename());
            if(isNotBlank(player.getEmail())) p.setEmail(player.getEmail());
            if(isNotBlank(player.getPhone())) p.setPhone(player.getPhone());
            if(isNotBlank(player.getName())) p.setName(player.getName());
            
            playerRepository.save(p);
            
            return Optional.ofNullable(p);
            
        })
        .orElse(Optional.empty());
    } 
    
    
    public  List<Player> getAll(){
        return playerRepository.findAll();
    }
    
    public Optional<Player> getById(Integer id){
        return playerRepository.findById(id);
    }
    
    public Boolean delete(Player player){
        if(playerRepository.existsById(player.getIdPlayer())){
            playerRepository.delete(player);
            return true;
        }
        return false;
    }
    
    public Boolean existis(Player player){
        return playerRepository.findByEmail(player.getEmail()).isPresent();
    }
    
}
