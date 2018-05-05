package br.com.uol.testebackend.domain.codename;

import br.com.uol.testebackend.domain.player.TypeGroup;
import br.com.uol.testebackend.domain.player.PlayerRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Servico responsavel por obter os codinomes para os jogadores
 */
@Service
public class CodenameService {

    @Inject CodenameRepository codenameRepository;
    @Inject PlayerRepository playerRepository;
    
    /**
     * Tenta obter um codinome livre, se nao encontra retorna null
     * @param group
     * @return Codename ou Excpetion
     */
    public Optional<Codename> getCodenameAvaliable(TypeGroup group) throws IOException{
        PlayerGroup<Codename> playerGroup = codenameRepository.listByGroup(group);
        List<Codename> alreadyUseds = playerRepository.searchAllAlreadyUsed();

        return playerGroup.getCodenames().stream().filter(c -> !alreadyUseds.contains(c)).findFirst();
    }
}
