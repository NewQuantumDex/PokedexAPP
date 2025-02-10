package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvolutionChain {
	private String url;
	private EvolutionChainDetails evolution_chain_details;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public EvolutionChainDetails getEvolution_chain_details() {
		return evolution_chain_details;
	}

	public void setEvolution_chain_details(EvolutionChainDetails evolution_chain_details) {
		this.evolution_chain_details = evolution_chain_details;
	} 

	
}
