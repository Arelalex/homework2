package com.dmdev.java_core.oop.io_stream.Auxiliary;

public class Items {
    private String id;
    private String name;
    private String price;

    public Items() {

    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
