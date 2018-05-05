package br.com.uol.testebackend.domain.player;

import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Servico responsavel por fazer o registrar um jogador novo ou recuperar
 * dados de jogadores já existentes
 */
@Service
public class PlayerService {
    
    @Inject private PlayerRepository playerRepository;
    
    public HttpStatus register(Player player){
        Player playerSaved = playerRepository.save(player);
        
        if(playerSaved == null){
            return HttpStatus.NOT_MODIFIED;
        }
        else{
            return HttpStatus.OK;
        }
    } 
}
