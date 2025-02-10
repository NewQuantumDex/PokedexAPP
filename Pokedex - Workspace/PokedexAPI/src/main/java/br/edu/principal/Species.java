package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Species extends NameUrl {
		private SpeciesDetails species;
		
		public SpeciesDetails getSpecies() {
			return species;
		}
		public void setSpecies(SpeciesDetails species) {
			this.species = species;
		}
}