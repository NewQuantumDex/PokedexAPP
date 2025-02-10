package br.edu.principal;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokedexAPI {
    private final String POKEMON_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private final String TYPE_DAMAGE_BASE_URL = "https://pokeapi.co/api/v2/type/";
    private final String SPECIES_DETAILS_BASE_URL = "https://pokeapi.co/api/v2/pokemon-species/";
    private final String ABILITY_TEXT_BASE_URL = "https://pokeapi.co/api/v2/ability/";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    
    private void mapearEvolucoes(List<EvolvesTo> evolvesToList, List<String> evolutionNames) {
        for (EvolvesTo evolvesTo : evolvesToList) {
            evolutionNames.add(evolvesTo.getSpecies().getName());

            if (evolvesTo.getEvolves_to() != null && !evolvesTo.getEvolves_to().isEmpty()) {
                mapearEvolucoes(evolvesTo.getEvolves_to(), evolutionNames);
            }
        }
    }

    
    
    public Pokemon GET_POKEMON(int id) {
        String url = POKEMON_BASE_URL + id ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Pokemon pokemon = mapper.readValue(response.body(), Pokemon.class);

                SpeciesDetails speciesDetails = GET_SPECIES_DETAILS(pokemon.getSpecies().getName());
                if (speciesDetails != null) {
                    pokemon.getSpecies().setSpecies(speciesDetails);

                    EvolutionChain evolutionChain = speciesDetails.getEvolution_chain();
                    if (evolutionChain != null) {
                        EvolutionChainDetails evolutionChainDetails = GET_EVOLUTION_CHAIN_DETAILS(evolutionChain.getUrl());
                        evolutionChain.setEvolution_chain_details(evolutionChainDetails);

                        List<String> pokemonEvolutionNames = new ArrayList<>();

                        if (evolutionChainDetails != null && evolutionChainDetails.getChain() != null) {
                            pokemonEvolutionNames.add(evolutionChainDetails.getChain().getSpecies().getName());

                            mapearEvolucoes(evolutionChainDetails.getChain().getEvolves_to(), pokemonEvolutionNames);
                        }

                        pokemon.setEvolutionNames(pokemonEvolutionNames);
                    }
                }

                List<Damage> pokemonDamages = GET_TYPE_DAMAGE(pokemon);
                pokemon.setPokemonDamages(pokemonDamages);
                for (Ability ability : pokemon.getAbilities()) {
                    AbilityEffect abilityEffect = GET_ABILITY_TEXT(ability.getAbility().getName());
                    ability.getAbility().setAbilityEffect(abilityEffect);
                }
                List<PokemonTextEntry> englishTextEntry = new ArrayList<>();
                for (PokemonTextEntry textEntry : pokemon.getSpecies().getSpecies().getFlavor_text_entries()) {
                	if( textEntry.getLanguage().getName().equalsIgnoreCase("en")) {
                		englishTextEntry.add(textEntry);
                	}
                }
                pokemon.getSpecies().getSpecies().setFlavor_text_entries(englishTextEntry);
                return pokemon;
            } else {
                System.err.println("Erro na API: Código " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar a requisição: " + e.getMessage());
            return null;
        }
    }

    
    Boolean typeInList(String typeName, List<TypeDetails> list) {
    	for (TypeDetails type : list) {
    		if (type.getName().equalsIgnoreCase(typeName)) {
    			return true;
    		}
    	}
    	return false;
    	
    }
    
    public Pokemon GET_POKEMON(String name) {
        String url = POKEMON_BASE_URL + name ;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Pokemon pokemon = mapper.readValue(response.body(), Pokemon.class);

                SpeciesDetails speciesDetails = GET_SPECIES_DETAILS(pokemon.getSpecies().getName());
                if (speciesDetails != null) {
                    pokemon.getSpecies().setSpecies(speciesDetails);

                    EvolutionChain evolutionChain = speciesDetails.getEvolution_chain();
                    if (evolutionChain != null) {
                        EvolutionChainDetails evolutionChainDetails = GET_EVOLUTION_CHAIN_DETAILS(evolutionChain.getUrl());
                        evolutionChain.setEvolution_chain_details(evolutionChainDetails);

                        List<String> pokemonEvolutionNames = new ArrayList<>();

                        if (evolutionChainDetails != null && evolutionChainDetails.getChain() != null) {
                            pokemonEvolutionNames.add(evolutionChainDetails.getChain().getSpecies().getName());

                            mapearEvolucoes(evolutionChainDetails.getChain().getEvolves_to(), pokemonEvolutionNames);
                        }

                        pokemon.setEvolutionNames(pokemonEvolutionNames);
                    }
                }

                List<Damage> pokemonDamages = GET_TYPE_DAMAGE(pokemon);
                pokemon.setPokemonDamages(pokemonDamages);
                for (Ability ability : pokemon.getAbilities()) {
                    AbilityEffect abilityEffect = GET_ABILITY_TEXT(ability.getAbility().getName());
                    ability.getAbility().setAbilityEffect(abilityEffect);
                }
                List<PokemonTextEntry> englishTextEntry = new ArrayList<>();
                for (PokemonTextEntry textEntry : pokemon.getSpecies().getSpecies().getFlavor_text_entries()) {
                	if( textEntry.getLanguage().getName().equalsIgnoreCase("en")) {
                		englishTextEntry.add(textEntry);
                	}
                }
                pokemon.getSpecies().getSpecies().setFlavor_text_entries(englishTextEntry);
                return pokemon;
            } else {
                System.err.println("Erro na API: Código " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar a requisição: " + e.getMessage());
            return null;
        }
    }

    
    
    	
    
    
    public AbilityEffect GET_ABILITY_TEXT(String name) {

    	String url = ABILITY_TEXT_BASE_URL + name;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), AbilityEffect.class);
                
            } else {
                System.err.println("Erro na API: Código " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar a requisição: " + e.getMessage());
        }
        return null;
        
    }
   public EvolutionChainDetails GET_EVOLUTION_CHAIN_DETAILS(String url) {

       HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(url))
               .GET()
               .build();
       try {
           HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
           if (response.statusCode() == 200) {
               return mapper.readValue(response.body(), EvolutionChainDetails.class);
           } else {
               System.err.println("Erro na API: Código " + response.statusCode());
           }
       } catch (Exception e) {
           System.err.println("Erro ao processar a requisição: " + e.getMessage());
       }
       return null;
       
   }
   public SpeciesDetails GET_SPECIES_DETAILS(String name) {
	   String url =  SPECIES_DETAILS_BASE_URL + name;

       HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(url))
               .GET()
               .build();
       try {
           HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
           if (response.statusCode() == 200) {
               return mapper.readValue(response.body(), SpeciesDetails.class);
           } else {
               System.err.println("Erro na API: Código " + response.statusCode());
           }
       } catch (Exception e) {
           System.err.println("Erro ao processar a requisição: " + e.getMessage());
       }
       return null;
       
   }
    public List<Damage> GET_TYPE_DAMAGE(Pokemon pokemon) {
        List<Damage> pokemonDamages = new ArrayList<>();

        for (Type type : pokemon.getTypes()) {
            String url = TYPE_DAMAGE_BASE_URL + type.getType().getName();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    Damage damage = mapper.readValue(response.body(), Damage.class);
                    pokemonDamages.add(damage); 
                } else {
                    System.err.println("Erro na API: Código " + response.statusCode() + " para URL " + url);
                }
            } catch (Exception e) {
                System.err.println("Erro ao processar a requisição para o tipo " + type.getType().getName() + ": " + e.getMessage());
            }
        }
        if (pokemonDamages.size() == 1) {
            return pokemonDamages;
        }
        else {
        	List<TypeDetails> half_damage_from0 = pokemonDamages.get(0).getDamage_relations().getHalf_damage_from();
        	List<TypeDetails> half_damage_from1 = pokemonDamages.get(1).getDamage_relations().getHalf_damage_from();
        	List<TypeDetails> double_damage_from0 = pokemonDamages.get(0).getDamage_relations().getDouble_damage_from();
        	List<TypeDetails> double_damage_from1 = pokemonDamages.get(1).getDamage_relations().getDouble_damage_from();
        	List<TypeDetails> immunity0 = pokemonDamages.get(0).getDamage_relations().getNo_damage_from();
        	List<TypeDetails> immunity1 = pokemonDamages.get(1).getDamage_relations().getNo_damage_from();


        	for (TypeDetails type : new ArrayList<>(half_damage_from0)) {
        		if (typeInList(type.getName(), double_damage_from1)) {
        			half_damage_from0.remove(type);
        			for (TypeDetails type2 : new ArrayList<>(double_damage_from1)) {
        				if (type2.getName().equalsIgnoreCase(type.getName())) {
        					double_damage_from1.remove(type2);
        				}
        			}
        		}
        	}
        	for (TypeDetails type : new ArrayList<>(double_damage_from0)) {
        		if (typeInList(type.getName(), half_damage_from1)) {
        			double_damage_from0.remove(type);
        			for (TypeDetails type2 : new ArrayList<>(half_damage_from1)) {
        				if (type2.getName().equalsIgnoreCase(type.getName())) {
        					half_damage_from1.remove(type2);
        				}
        			}
        		}
        	}
      
        	DamageRelations damageRelations = new DamageRelations();
        	List<TypeDetails> half_damage_from = new ArrayList<>();
        	half_damage_from0.addAll(half_damage_from1);
        	half_damage_from.addAll(half_damage_from0);
        	
        	List<TypeDetails> double_damage_from = new ArrayList<>();
        	double_damage_from0.addAll(double_damage_from1);
        	double_damage_from.addAll(double_damage_from0);
        	
        	List<TypeDetails> no_damage_from = new ArrayList<>();
        	immunity0.addAll(immunity1);
        	no_damage_from.addAll(immunity0);
        	
        	for (TypeDetails type : new ArrayList<>(no_damage_from)) {
        		if (typeInList(type.getName(), double_damage_from)) {
        			for (TypeDetails type2 : new ArrayList <>(double_damage_from)) {
        				if (type2.getName().equalsIgnoreCase(type.getName())) {
        					double_damage_from.remove(type2);
        				}
        			}
        		}
        		if (typeInList(type.getName(), half_damage_from)) {
        			for (TypeDetails type2 : new ArrayList <>(half_damage_from)) {
        				if (type2.getName().equalsIgnoreCase(type.getName())) {
        					half_damage_from.remove(type2);
        				}
        			}
        		}
        		
        	}
        	
        	damageRelations.setHalf_damage_from(half_damage_from);
        	damageRelations.setDouble_damage_from(double_damage_from);
        	damageRelations.setNo_damage_from(no_damage_from);
        	
        	Damage damage = new Damage();
        	damage.setDamage_relations(damageRelations);
        	List<Damage> newPokemonDamages = new ArrayList<>();
        	newPokemonDamages.add(damage);
        	return newPokemonDamages;	
        }
    }



}


