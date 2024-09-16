package com.Food_Delivery_App.Model;

import java.util.HashMap;

public class Cart {

    // First Integer for Item_id and second integer for quantity
    private HashMap<Integer, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void Add(CartItem item) {
        int item_id = item.getItem_id();

        if (items.containsKey(item_id)) {
            CartItem existingItem = items.get(item_id);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            existingItem.setSubtotal(existingItem.getQuantity() * existingItem.getPrice());
        } else {
            items.put(item_id, item);
        }
    }

    public void updateItem(int item_id, int quantity) {
        if (items.containsKey(item_id)) {
            if (quantity <= 0) {
                items.remove(item_id);
            } else {
                CartItem item = items.get(item_id);
                item.setQuantity(quantity);
                item.setSubtotal(item.getQuantity() * item.getPrice());
            }
        }
    }

    public void removeItem(int item_id) {
        items.remove(item_id);
    }

    public void clear() {
        items.clear();
    }

    // Method to return items
    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    // Method to calculate the total amount
    public float getTotalAmount() {
        float totalAmount = 0;
        for (CartItem item : items.values()) {
            totalAmount += item.getSubtotal();
        }
        return totalAmount;
    }
}
