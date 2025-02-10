package br.edu.principal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityDetails extends NameUrl {
	
	private AbilityEffect abilityEffect;
	
	public AbilityDetails () {
		
	}

	public AbilityEffect getAbilityEffect() {
		return abilityEffect;
	}
	public void setAbilityEffect(AbilityEffect abilityEffect) {
		this.abilityEffect = abilityEffect;
	}
	
}
