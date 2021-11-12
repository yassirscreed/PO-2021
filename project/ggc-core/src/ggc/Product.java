package ggc;

import java.lang.Math;
import java.util.ArrayList;
import java.io.Serializable;

public abstract class Product implements Serializable, ObserverSubject {

    private String _prodID;
    private int _stocktotal;
    private Double _maxprice;
    private ArrayList<ProductObserver> _observers = new ArrayList<ProductObserver>();
    private NotificationDelivery _send = new DeliveryMode();

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

    // Observers

    @Override
    public void registerObserver(ProductObserver ob) {
        _observers.add(ob);
    }

    @Override
    public void removeObserver(ProductObserver ob) {
        _observers.remove(ob);
    }

    public void toggleNotifications(ProductObserver ob) {
        if (_observers.contains(ob)) {
            removeObserver(ob);
        } else {
            registerObserver(ob);
        }
    }

    @Override
    public void notifyBargain(int price) {
        for (ProductObserver observer : _observers) {
            observer.updateBargain(_prodID, price, _send);
        }
    }

    @Override
    public void notifyNew(int price) {
        for (ProductObserver observer : _observers) {
            observer.updateNew(_prodID, price, _send);
        }
    }

}