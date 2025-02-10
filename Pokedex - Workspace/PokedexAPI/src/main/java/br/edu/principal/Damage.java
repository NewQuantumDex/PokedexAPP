package br.edu.principal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Damage {
    private DamageRelations damage_relations;

    // Getter e Setter
    public DamageRelations getDamage_relations() {
        return damage_relations;
    }

    public void setDamage_relations(DamageRelations damage_relations) {
        this.damage_relations = damage_relations;
    }
}
