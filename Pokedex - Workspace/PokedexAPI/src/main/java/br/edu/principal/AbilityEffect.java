package br.edu.principal;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityEffect {
	private List<EffectEntry> effect_entries;

	public List<EffectEntry> getEffect_entries() {
		return effect_entries;
	}

	public void setEffect_entries(List<EffectEntry> effect_entries) {
		this.effect_entries = effect_entries;
	}
	
}
