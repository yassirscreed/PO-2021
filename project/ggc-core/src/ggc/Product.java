package ggc;

import java.io.Serializable;

public class Product implements Serializable {

    private String _prodID;
    private int _stocktotal;
    private double _maxprice;

    public Product(String id, double maxprice, int stock) {
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

    public String getProdName() {
        return _prodID;
    }

    public void setPrice(double newprice) {
        _maxprice = newprice;
    }

    public double getPrice() {
        return _maxprice;
    }

    @Override
    public String toString(){
        return getProdName() + "|" + getPrice() + "|" + getStockTotal() + "\n";
    }
}