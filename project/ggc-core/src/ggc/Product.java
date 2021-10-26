package ggc;

import java.io.Serializable;

public class Product implements Serializable{
    
    private String _prodname;
    private int _stock;
    private double _price;

    public Product(String name, double price, int stock){
        _prodname = name;
        _price = price;
        _stock = stock;
    }

    public int getStock(){
        return _stock;
    }

    public void addStock(int stock){
        _stock += stock;
    }

    public String getProdName(){
        return _prodname;
    }

    public void setPrice(double newprice){
        _price = newprice;
    }

    public double getPrice(){
        return _price;
    }
    
}