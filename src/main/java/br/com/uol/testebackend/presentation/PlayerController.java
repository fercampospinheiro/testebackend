package br.com.uol.testebackend.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador das solicitacoes dos formularios tela do jogador
 */
@Controller
public class PlayerController {
    
    @GetMapping("/")
    public String home(){
        return playersForm();
    }
    
    /**
     * Carrega o fromulario da lista de jogadores
     * 
     */
    @GetMapping("/players")
    public String playersForm(){
        return "player/players";
    }
    

}
