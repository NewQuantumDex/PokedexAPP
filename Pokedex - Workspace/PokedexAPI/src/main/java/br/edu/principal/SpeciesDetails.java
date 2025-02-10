package br.edu.principal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeciesDetails {
	private int base_happiness;
	private int capture_rate;
	private EvolutionChain evolution_chain;
	private List<PokemonTextEntry> flavor_text_entries;
	private List<Variety> varieties;
	

	public EvolutionChain getEvolution_chain() {
		return evolution_chain;
	}

	public void setEvolution_chain(EvolutionChain evolution_chain) {
		this.evolution_chain = evolution_chain;
	}

	public int getBase_happiness() {
		return base_happiness;
	}

	public void setBase_happiness(int base_happiness) {
		this.base_happiness = base_happiness;
	}

	public int getCapture_rate() {
		return capture_rate;
	}

	public void setCapture_rate(int capture_rate) {
		this.capture_rate = capture_rate;
	}

	public List<PokemonTextEntry> getFlavor_text_entries() {
		return flavor_text_entries;
	}

	public void setFlavor_text_entries(List<PokemonTextEntry> flavor_text_entries) {
		this.flavor_text_entries = flavor_text_entries;
	}

	public List<Variety> getVarieties() {
		return varieties;
	}

	public void setVarieties(List<Variety> varieties) {
		this.varieties = varieties;
	}
}
