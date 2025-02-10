package br.edu.principal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class EvolutionDetails {
    private List<EvolvesTo> evolves_to; // Evoluções subsequentes
    private Species species; // Espécie associada

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
