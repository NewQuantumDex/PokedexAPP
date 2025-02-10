package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EvolutionChainDetails {
	private int id;
	private EvolutionChainDetailsChain chain;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EvolutionChainDetailsChain getChain() {
		return chain;
	}
	public void setChain(EvolutionChainDetailsChain chain) {
		this.chain = chain;
	}
}
