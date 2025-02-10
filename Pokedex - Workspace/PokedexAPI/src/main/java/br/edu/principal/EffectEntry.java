package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EffectEntry {
	private String effect;
	private Language language;
	private String short_effect;
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getShort_effect() {
		return short_effect;
	}
	public void setShort_effect(String short_effect) {
		this.short_effect = short_effect;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
}
