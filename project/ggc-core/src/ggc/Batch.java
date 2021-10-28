package ggc;

import java.io.Serializable;

public class Batch implements Serializable {

    // private String _prodID;
    private Product _product;
    private String _partnerID;
    private int _quantity;
    private double _price;

    public Batch(Product product, String partnerID, int quantity, double price) {
        _product = product;
        _partnerID = partnerID;
        _quantity = quantity;
        _price = price;
    }

    public int getQuantity() {
        return _quantity;
    }

    public String getPartnerID() {
        return _partnerID;
    }

    /**
     * public String getProdID() { return _prodID; }
     */

    public Product getProd() {
        return _product;
    }

    public double getPrice() {
        return _price;
    }

    @Override
    public String toString() {
        return _product.getProdID() + "|" + getPartnerID() + "|" + Math.round(getPrice()) + "|" + getQuantity(); // +
                                                                                                                 // "\n";
    }

}
