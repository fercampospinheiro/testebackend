package br.com.uol.testebackend.domain.codename;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * VO pra o Grupo da Liga da Justica
 */
@JacksonXmlRootElement(localName = "liga_da_justica")
public class JusticeLeagueGroup implements PlayerGroup<Codename>{
    
    @JacksonXmlElementWrapper(localName = "codinomes")
    @JacksonXmlProperty(localName = "codinome")
    private List<String> codinomes;

    public List<String> getCodinomes() {
        return codinomes;
    }

    public void setCodinomes(List<String> codinomes) {
        this.codinomes = codinomes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codinomes);
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
        
        final JusticeLeagueGroup other = (JusticeLeagueGroup) obj;
        
        Collections.sort(this.codinomes);
        Collections.sort(other.codinomes); 
        
        if(!this.codinomes.equals(other.codinomes)){
           return false;
        }
        
        return true;
    }
    
    @Transient
    @Override
    public List<Codename> getCodenames(){
        return this.codinomes.stream().map(c -> new Codename(c)).collect(Collectors.toList());
    }     

    public void setCodNames(List<Codename> codenames){
        if(codenames == null || codenames.isEmpty()) return;
        this.codinomes = codenames.stream().map(c -> c.getCodinome()).collect(Collectors.toList());
    }
}
