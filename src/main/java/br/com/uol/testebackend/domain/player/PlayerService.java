package br.com.uol.testebackend.domain.player;

import br.com.uol.testebackend.domain.codename.Codename;
import br.com.uol.testebackend.domain.codename.CodenameService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Inject private CodenameService codenameService;
    
    /**
     *  Atualiza o jogador
     *  Se o jogador muda de grupo tenta encontrar um novo codinome
     *  Do contrario mantem o codinome que já possuia
     * @param player
     * @return
     */
    @Transactional(readOnly = false ,propagation = Propagation.REQUIRED)
    public Optional<Player> registerOrUpdate(Player player) {
        
        Optional<Player> previous = playerRepository.findById(player.getIdPlayer());
        
        if(previous.isPresent()){
            if(!previous.get().getPlayerGroup().equals(player.getPlayerGroup())){
                try {
                    
                    Optional<Codename> codenameAvaliable = codenameService.getCodenameAvaliable(player.getPlayerGroup());
                    codenameAvaliable.ifPresent(c -> player.setCodename(c.getCodinome()));
                    
                } catch (IOException ex) {
                    Logger.getLogger(PlayerService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
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
