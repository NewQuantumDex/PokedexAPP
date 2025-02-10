package br.edu.principal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonSearch {
    private List<Pokemon> pokemonsGen1;
    private List<Pokemon> pokemonsGen2;
    private List<Pokemon> pokemonsGen3;
    private List<Pokemon> pokemonsGen4;
    private List<Pokemon> pokemonsGen5;
    private List<Pokemon> pokemonsGen6;
    private List<Pokemon> pokemonsGen7;
    private List<Pokemon> pokemonsGen8;
    private List<Pokemon> pokemonsGen9;


    private List<Pokemon> allPokemon;
    
    private final ObjectMapper mapper = new ObjectMapper();
    private final String GEN1URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen1.json";
    private final String GEN2URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen2.json";
    private final String GEN3URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen3.json";
    private final String GEN4URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen4.json";
    private final String GEN5URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen5.json";
    private final String GEN6URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen6.json";
    private final String GEN7URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen7.json";
    private final String GEN8URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen8.json";
    private final String GEN9URL = "/home/israel/Pokedex - Workspace/PokedexAPI/Gen9.json";

    public PokemonSearch() {
        try {
            pokemonsGen1 = mapper.readValue(new File(GEN1URL), new TypeReference<List<Pokemon>>() {});

            pokemonsGen2 = mapper.readValue(new File(GEN2URL), new TypeReference<List<Pokemon>>() {});
            
            pokemonsGen3 = mapper.readValue(new File(GEN3URL), new TypeReference<List<Pokemon>>() {});
            
            pokemonsGen4 = mapper.readValue(new File(GEN4URL), new TypeReference<List<Pokemon>>() {});

            pokemonsGen5 = mapper.readValue(new File(GEN5URL), new TypeReference<List<Pokemon>>() {});
            
            pokemonsGen6 = mapper.readValue(new File(GEN6URL), new TypeReference<List<Pokemon>>() {});
            
            pokemonsGen7 = mapper.readValue(new File(GEN7URL), new TypeReference<List<Pokemon>>() {});

            pokemonsGen8 = mapper.readValue(new File(GEN8URL), new TypeReference<List<Pokemon>>() {});
            
            pokemonsGen9 = mapper.readValue(new File(GEN9URL), new TypeReference<List<Pokemon>>() {});

            
            allPokemon = mergeGenerations(pokemonsGen1, pokemonsGen2, pokemonsGen3, pokemonsGen4,
            		pokemonsGen5, pokemonsGen6, pokemonsGen7, pokemonsGen8, pokemonsGen9);
        } catch (IOException e) {
            System.err.println("Erro ao carregar os arquivos JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Pokemon> mergeGenerations(List<Pokemon> gen1, List<Pokemon> gen2, List<Pokemon> gen3, List<Pokemon> gen4,
    		List<Pokemon> gen5, List<Pokemon> gen6, List<Pokemon> gen7, List<Pokemon> gen8, List<Pokemon> gen9) {
        List<Pokemon> all = new java.util.ArrayList<>();
        if (gen1 != null) all.addAll(gen1);
        if (gen2 != null) all.addAll(gen2);
        if (gen3 != null) all.addAll(gen3);
        if (gen4 != null) all.addAll(gen4);
        if (gen5 != null) all.addAll(gen5);
        if (gen6 != null) all.addAll(gen6);
        if (gen7 != null) all.addAll(gen7);
        if (gen8 != null) all.addAll(gen8);
        if (gen9 != null) all.addAll(gen9);

        return all;
    }
    
    private Boolean hasType(String typeName, List<Type> pokemonTypes) {
    	for (Type type : pokemonTypes) {
    		if (type.getType().getName().equalsIgnoreCase(typeName)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private Boolean hasAbility(String abilityName, List<Ability> pokemonAbility) {
    	for (Ability ability : pokemonAbility) {
    		if (ability.getAbility().getName().equalsIgnoreCase(abilityName)) {
    			return true;
    		}
    	}
    	return false;
    }
    private Boolean hasMove(String moveName, List<Move> pokemonMove) {
    	for (Move move : pokemonMove) {
    		if (move.getMove().getName().equalsIgnoreCase(moveName)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public List<Pokemon> selectPokemonsGen1() {
        return pokemonsGen1;
    }

    public List<Pokemon> selectPokemonsGen2() {
        return pokemonsGen2;
    }

    
    public List<Pokemon> selectAllPokemon() {
        return allPokemon;
    }

    
    public List<Pokemon> selectPokemonByType(String typeName, List<Pokemon> pokemons) {
    	List<Pokemon> pokemonByType = new ArrayList<>();
    	for (Pokemon pokemon : pokemons) {
    		if (hasType(typeName, pokemon.getTypes())) {
    			pokemonByType.add(pokemon);
    		}
    	}
        return pokemonByType;
    }
    
    public List<Pokemon> selectPokemonByName(String name, List<Pokemon> pokemons) {
    	List<Pokemon> pokemonByName = new ArrayList<>();
    	for (Pokemon pokemon : pokemons) {
    		if (pokemon.getName().contains(name)) {
    			pokemonByName.add(pokemon);
    		}
    	}
    	return pokemonByName;
    }
    
    public List<Pokemon> selectPokemonByAbility(String name, List<Pokemon> pokemons) {
    	List<Pokemon> pokemonByAbility = new ArrayList<>();
    	for (Pokemon pokemon : pokemons) {
    		if (hasAbility(name, pokemon.getAbilities())) {
    			pokemonByAbility.add(pokemon);
    		}
    	}
    	return pokemonByAbility;
    }
    
    public List<Pokemon> selectPokemonByMove(String name, List<Pokemon> pokemons) {
    	List<Pokemon> pokemonByMove = new ArrayList<>();
    	for (Pokemon pokemon : pokemons) {
    		if (hasMove(name, pokemon.getMoves())) {
    			pokemonByMove.add(pokemon);
    		}
    	}
    	return pokemonByMove;
    }
    
    public List<Pokemon> searchForAllAtributes(List<Pokemon> pokemons, String name, String abilityName, String typeName, String moveName) {
        List<Pokemon> filteredPokemons = new ArrayList<>(pokemons);

        if (name != null && !name.isEmpty()) {
            filteredPokemons = selectPokemonByName(name, filteredPokemons);
        }
        if (abilityName != null && !abilityName.isEmpty()) {
            filteredPokemons = selectPokemonByAbility(abilityName, filteredPokemons);
        }
        if (typeName != null && !typeName.isEmpty()) {
            filteredPokemons = selectPokemonByType(typeName, filteredPokemons);
        }
        if (moveName != null && !moveName.isEmpty()) {
            filteredPokemons = selectPokemonByMove(moveName, filteredPokemons);
        }
        
        return filteredPokemons;
    }


	public List<Pokemon> selectPokemonsGen3() {
		return pokemonsGen3;
	}

	public void setPokemonsGen3(List<Pokemon> pokemonsGen3) {
		this.pokemonsGen3 = pokemonsGen3;
	}

	public List<Pokemon> selectPokemonsGen4() {
		return pokemonsGen4;
	}

	public void setPokemonsGen4(List<Pokemon> pokemonsGen4) {
		this.pokemonsGen4 = pokemonsGen4;
	}

	public List<Pokemon> selectPokemonsGen5() {
		return pokemonsGen5;
	}

	public void setPokemonsGen5(List<Pokemon> pokemonsGen5) {
		this.pokemonsGen5 = pokemonsGen5;
	}

	public List<Pokemon> selectPokemonsGen6() {
		return pokemonsGen6;
	}

	public void setPokemonsGen6(List<Pokemon> pokemonsGen6) {
		this.pokemonsGen6 = pokemonsGen6;
	}

	public List<Pokemon> selectPokemonsGen7() {
		return pokemonsGen7;
	}

	public void setPokemonsGen7(List<Pokemon> pokemonsGen7) {
		this.pokemonsGen7 = pokemonsGen7;
	}

	public List<Pokemon> selectPokemonsGen8() {
		return pokemonsGen8;
	}

	public void setPokemonsGen8(List<Pokemon> pokemonsGen8) {
		this.pokemonsGen8 = pokemonsGen8;
	}

	public List<Pokemon> selectPokemonsGen9() {
		return pokemonsGen9;
	}

	public void setPokemonsGen9(List<Pokemon> pokemonsGen9) {
		this.pokemonsGen9 = pokemonsGen9;
	}

	
	
	
    
}
