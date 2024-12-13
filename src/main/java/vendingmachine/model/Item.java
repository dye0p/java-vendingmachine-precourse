package vendingmachine.model;

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
}
