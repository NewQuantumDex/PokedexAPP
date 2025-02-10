package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Variety {
	private Boolean is_default;
	private VarietyDetails pokemon;
	public Boolean getIs_default() {
		return is_default;
	}
	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}
	public VarietyDetails getPokemon() {
		return pokemon;
	}
	public void setPokemon(VarietyDetails pokemon) {
		this.pokemon = pokemon;
	}
}
