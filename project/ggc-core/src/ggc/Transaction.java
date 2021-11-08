package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    // ATRIBUTOS
    private String _partnerID;
    private String _productID;
    private int _quantity;
    private int _paydate;
    private int _id;

    Transaction(int id, String partnerID, String productID, int quantity, int paydate) {
        _id = id;
        _partnerID = partnerID;
        _productID = productID;
        _quantity = quantity;
        _paydate = paydate;
    }

    public int getQuantity() {
        return _quantity;
    }
    
    public String getProductID() {
        return _productID;
    }

    public String getPartnerID() {
        return _partnerID;
    }

    public int getId() {
        return _id;
    }

    public int getPayDate() {
        return _paydate;

    }
}