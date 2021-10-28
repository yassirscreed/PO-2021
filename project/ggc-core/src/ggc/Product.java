package ggc;

import java.lang.Math;
import java.io.Serializable;

public abstract class Product implements Serializable {

    private String _prodID;
    private int _stocktotal;
    private Double _maxprice;

    public Product(String id, Double maxprice, int stock) {
        _prodID = id;
        _maxprice = maxprice;
        _stocktotal = stock;
    }

    public int getStockTotal() {
        return _stocktotal;
    }

    public void addStock(int stock) {
        _stocktotal += stock;
    }

    public String getProdID() {
        return _prodID;
    }

    public void setPrice(Double newprice) {
        _maxprice = newprice;
    }

    public double getPrice() {
        return _maxprice;
    }

    @Override
    public String toString() {
        return getProdID() + "|" + Math.round(getPrice()) + "|" + getStockTotal(); // + "\n";
    }
}