package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    // ATRIBUTOS
    private String _partnerID;
    private int _paydate;
    private int _id;
    // private int _initialPrice;

    Transaction(int paydate, int id, String partnerID) {
        _partnerID = partnerID;
        _paydate = paydate;
        _id = id;
        // _initialPrice = initialPrice;
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

    /**
     * public int getinitialPrice() { return _initialPrice; }
     * 
     * public void setPrice(int p) { _initialPrice = p; }
     */
}