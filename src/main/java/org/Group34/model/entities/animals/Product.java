package org.Group34.model.entities.animals;

import org.Group34.model.items.Item;

public record Product(String name, int price, int reqFriendship) implements Item {
    @Override
    public String getName() {
        return name;
    }
}
