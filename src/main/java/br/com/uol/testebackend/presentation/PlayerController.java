package br.com.uol.testebackend.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Fernando Pinheiro
 */
@Controller("/")
public class PlayerController {

    @GetMapping("players")
    public String all(){
        return "player/players";
    }
    
}
