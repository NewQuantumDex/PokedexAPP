package br.edu.principal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {
    private int slot;
    private TypeDetails type;

    // Getters e Setters
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public TypeDetails getType() {
        return type;
    }

    public void setType(TypeDetails type) {
        this.type = type;
    }
}
