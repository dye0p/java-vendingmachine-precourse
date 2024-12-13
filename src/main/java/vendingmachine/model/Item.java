package vendingmachine.model;

import java.util.Objects;

public class Item {

    private final String name;
    private final int price;
    private int quantity;

    public Item(String name, int price, int quantity) {

        if (price < 1000) {
            throw new IllegalArgumentException("상품 가격은 1000원 부터 시작해야 합니다.");
        }

        if (price % 10 != 0) {
            throw new IllegalArgumentException("상품 가격은 10원 단위여야 합니다.");
        }

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}


