package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    // ATRIBUTOS
    private String _partnerID;
    private String _productID;
    private String _type;
    private int _quantity;
    private int _paydate;
    private int _id;
    private boolean _paid = false;

    Transaction(int id, String partnerID, String productID, int quantity, int paydate, String type) {
        _id = id;
        _partnerID = partnerID;
        _productID = productID;
        _quantity = quantity;
        _paydate = paydate;
        _type = type;
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
    
    public String getType(){
        return _type;
    }

    public int getId() {
        return _id;
    }

    public int getPayDate() {
        return _paydate;

    }

    public boolean getPaidStatus() {
        return _paid;
    }

    public void setPayDate(int date) {
        _paydate = date;
        _paid = true;
    }
}
