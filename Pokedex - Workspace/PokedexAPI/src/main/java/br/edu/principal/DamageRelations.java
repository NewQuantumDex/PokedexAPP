package br.edu.principal;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DamageRelations {
    private List<TypeDetails> double_damage_from; // Fraquezas
    private List<TypeDetails> half_damage_from;   // Resistências
    private List<TypeDetails> no_damage_from;     // Imunidades

    public List<TypeDetails> getDouble_damage_from() {
        return double_damage_from;
    }

    public void setDouble_damage_from(List<TypeDetails> double_damage_from) {
        this.double_damage_from = double_damage_from;
    }

    public List<TypeDetails> getHalf_damage_from() {
        return half_damage_from;
    }

    public void setHalf_damage_from(List<TypeDetails> half_damage_from) {
        this.half_damage_from = half_damage_from;
    }

    public List<TypeDetails> getNo_damage_from() {
        return no_damage_from;
    }

    public void setNo_damage_from(List<TypeDetails> no_damage_from) {
        this.no_damage_from = no_damage_from;
    }

}
