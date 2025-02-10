package br.edu.principal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
	private String name;
	private double height;
	private double weight;
	private List<Ability> abilities;
	private Sprite sprites;
	private Species species;
	private List<Stat> stats;
	private List<Move> moves;
	private List<Type> types;
	private List<String> EvolutionNames;
	private List<Damage> pokemonDamages;
	private Boolean is_default;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public List<Ability> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}
	public Sprite getSprites() {
		return sprites;
	}
	public void setSprites(Sprite sprites) {
		this.sprites = sprites;
	}
	public List<Stat> getStats() {
		return stats;
	}
	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}
	public List<Move> getMoves() {
		return moves;
	}
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	public List<Type> getTypes() {
		return types;
	}
	public void setTypes(List<Type> types) {
		this.types = types;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
	}
	public List<String> getEvolutionNames() {
		return EvolutionNames;
	}
	public void setEvolutionNames(List<String> evolutionNames) {
		EvolutionNames = evolutionNames;
	}
	public List<Damage> getPokemonDamages() {
		return pokemonDamages;
	}
	public void setPokemonDamages(List<Damage> pokemonDamages) {
		this.pokemonDamages = pokemonDamages;
	}
	
	public List<TypeDetails> getWeakness() {
		return pokemonDamages.get(0).getDamage_relations().getDouble_damage_from();
	}
	
	public List<TypeDetails> getResistance() {
		return pokemonDamages.get(0).getDamage_relations().getHalf_damage_from();
	}
	
	public List<TypeDetails> getImmunity() {
		return pokemonDamages.get(0).getDamage_relations().getNo_damage_from();
	}
	public Boolean getIs_default() {
		return is_default;
	}
	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
