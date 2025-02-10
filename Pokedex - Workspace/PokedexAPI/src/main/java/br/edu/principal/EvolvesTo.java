package br.edu.principal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class EvolvesTo {
    private List<EvolutionDetails> evolution_details;
    private List<EvolvesTo> evolves_to;
    private Species species;

    public List<EvolutionDetails> getEvolution_details() {
        return evolution_details;
    }

    public void setEvolution_details(List<EvolutionDetails> evolution_details) {
        this.evolution_details = evolution_details;
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
