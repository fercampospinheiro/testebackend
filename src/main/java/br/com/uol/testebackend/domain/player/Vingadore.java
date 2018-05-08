package br.com.uol.testebackend.domain.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "codinome"
})
public class Vingadore {

    @JsonProperty("codinome")
    @JacksonXmlProperty(localName = "codinome")
    private String codinome;

    @JsonProperty("codinome")
    @JacksonXmlProperty(localName = "codinome")
    public String getCodinome() {
        return codinome;
    }

    @JsonProperty("codinome")
    @JacksonXmlProperty(localName = "codinome")
    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

}
