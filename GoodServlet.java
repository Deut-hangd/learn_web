package com.test;

public class GoodServlet {
    private String name;
    private float price;
    private int number;
    public GoodServlet(String name, float price, int number){
        this.name = name;
        this.price = price;
        this.number = number;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price){ this.price = price; }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }
}
