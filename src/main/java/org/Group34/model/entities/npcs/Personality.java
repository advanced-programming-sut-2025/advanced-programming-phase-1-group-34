package org.Group34.model.entities.npcs;

import java.util.List;
import java.util.Map;

public class Personality {
    private Map<String, Double> traits;
    private List<String> interests;

    public Personality(Map<String, Double> traits, List<String> interests) {
        this.traits = traits;
        this.interests = interests;
    }

    public Map<String, Double> getTraits() {
        return traits;
    }

    public List<String> getInterests() {
        return interests;
    }

    public double getTrait(String traitName) {
        return traits.getOrDefault(traitName, 0.0);
    }
}
