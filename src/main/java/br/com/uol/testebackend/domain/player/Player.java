package br.com.uol.testebackend.domain.player;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade jogador 
 */
@Entity
@Table(name = "player")
public class Player implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_player")
    private Integer idPlayer;
    
    @NotNull
    @NotBlank(message = "Informe o email")
    @Size(max = 200)
    @Email(message ="Email inválido" )
    @Column(unique=true)
    private String email;
    
    @NotNull
    @NotBlank(message = "Informe o nome")
    @Size(max = 200)
    private String name;
    
    @NotNull
    @NotBlank(message = "Informe o telefone")
    @Size(max = 12)
    private String phone;
    
    @Size(max = 200)
    private String codename;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "player_group")
    private TypeGroup playerGroup;

    public Player() {
    }
    
    public Player(String email, String phone, String codename, TypeGroup playerGroup, String name) {
        this.email = email;
        this.phone = phone;
        this.codename = codename;
        this.playerGroup = playerGroup;
        this.name = name;
    }

    public TypeGroup getPlayerGroup() {
        return playerGroup;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCodename() {
        return codename;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPlayerGroup(TypeGroup playerGroup) {
        this.playerGroup = playerGroup;
    }
    
    public String getPlayerGroupName() {
        return playerGroup.getName();
    }
    
}
