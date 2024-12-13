package vendingmachine.model;

import java.util.List;

public class Items {

    private final List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }


    public Item findByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }

        throw new IllegalArgumentException("해당 하는 상품은 존재하지 않습니다.");
    }
}
