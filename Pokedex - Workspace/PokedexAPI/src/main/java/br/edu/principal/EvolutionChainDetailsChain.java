package br.edu.principal;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvolutionChainDetailsChain {
    private Boolean is_baby;
    private List<EvolvesTo> evolves_to; // Lista de evoluções
    private Species species; // Espécie inicial

    public Boolean getIs_baby() {
        return is_baby;
    }

    public void setIs_baby(Boolean is_baby) {
        this.is_baby = is_baby;
    }

    public List<EvolvesTo> getEvolves_to() {
        return evolves_to;
    }

    public void setEvolves_to(List<EvolvesTo> evolves_to) {
        this.evolves_to = evolves_to;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
