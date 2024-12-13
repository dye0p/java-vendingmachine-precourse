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

    public boolean isAllItemOutOfStock() {
        //모든 상품의 재고가 차감된 경우
        int count = 0;

        for (Item item : items) {
            count += item.getQuantity();
        }

        return count == 0;
    }

    public boolean isMinPriceItemMore(int amount) {
        int minPrice = items.get(0).getPrice();

        for (Item item : items) {
            int price = item.getPrice();
            if (minPrice > price) {
                minPrice = price;
            }
        }

        return minPrice > amount;
    }

    public int purchase(Item purchaseItem, int amount) {
        for (Item item : items) {
            if (item.getName().equals(purchaseItem.getName())) {
                item.discountQuantity();

                amount -= item.getPrice();
            }
        }

        return amount;
    }
}
