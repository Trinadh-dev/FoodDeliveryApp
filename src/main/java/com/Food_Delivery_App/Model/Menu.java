package com.Food_Delivery_App.Model;

public class Menu {
    
    private int menu_id;
    private int restaurant_id;
    private String menuname;
    private Float price;
    private String description;
    private boolean isavailable;
    private byte[] imagepath; // Storing image as byte array

    public Menu() {}

    public Menu(int menu_id, int restaurant_id, String menuname, Float price, String description, boolean isavailable, byte[] imagepath) {
        this.menu_id = menu_id;
        this.restaurant_id = restaurant_id;
        this.menuname = menuname;
        this.price = price;
        this.description = description;
        this.isavailable = isavailable;
        this.imagepath = imagepath;
    }

    public Menu(int restaurant_id, String menuname, Float price, String description, boolean isavailable, byte[] imagepath) {
        this.restaurant_id = restaurant_id;
        this.menuname = menuname;
        this.price = price;
        this.description = description;
        this.isavailable = isavailable;
        this.imagepath = imagepath;
    }

    // Getters and Setters for all fields
    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }

    public byte[] getImagepath() {
        return imagepath;
    }

    public void setImagepath(byte[] imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString() {
        return "Menu [menu_id=" + menu_id + ", restaurant_id=" + restaurant_id + ", menuname=" + menuname + ", price="
                + price + ", description=" + description + ", isavailable=" + isavailable + "]";
    }
}
