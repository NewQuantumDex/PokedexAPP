package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ability {
	private AbilityDetails ability;
	private boolean is_hidden;
	private int slot;
	public AbilityDetails getAbility() {
		return ability;
	}
	public void setAbility(AbilityDetails ability) {
		this.ability = ability;
	}
	public boolean isIs_hidden() {
		return is_hidden;
	}
	public void setIs_hidden(boolean is_hidden) {
		this.is_hidden = is_hidden;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	
}

