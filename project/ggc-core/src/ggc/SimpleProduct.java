package ggc;

import java.lang.Math;

public class SimpleProduct extends Product {

    public SimpleProduct(String id, Double maxprice, int stock) {
        super(id, maxprice, stock);
    }

    @Override
    public String toString() {
        return getProdID() + "|" + Math.round(getPrice()) + "|" + getStockTotal();
    }
}
