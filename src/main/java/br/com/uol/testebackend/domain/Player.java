package br.com.uol.testebackend.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Fernando Pinheiro
 */
@Entity()
@Table(name = "player")
public class Player implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_player")
    private Integer idPlayer;

    private String email;
    private String phone;
    private String codename;
    @Column(name = "group_codename")
    private String group;
    
}
