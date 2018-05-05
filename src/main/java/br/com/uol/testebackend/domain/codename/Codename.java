package br.com.uol.testebackend.domain.codename;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.Objects;

/**
 * Objeto que representa o codinome
 */
public class Codename {
    @JacksonXmlElementWrapper(useWrapping = false)
    private String codinome;

    public Codename() {
    } 
    
    public Codename(String codinome) {
        this.codinome = codinome;
    }
    
    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Codename other = (Codename) obj;
        if (!Objects.equals(this.codinome, other.codinome)) {
            return false;
        }
        return true;
    }
   
}
