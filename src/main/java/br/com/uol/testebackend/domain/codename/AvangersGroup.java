package br.com.uol.testebackend.domain.codename;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * lista de codeinomes do Grupo vingadores
 */
@JsonRootName("vingadores")
public class AvangersGroup extends ArrayList<Codename> implements PlayerGroup<Codename>{

    @Override
    @Transient
    public List<Codename> getCodenames() {
        return this;
    }
    
}
