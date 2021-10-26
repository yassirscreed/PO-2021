package ggc;

import java.io.Serializable;

public class Transaction implements Serializable{
    // ATRIBUTOS
    private String _partnerID;
    private int _paydate;
    private int _id;

    Transaction(int paydate, int id, String partnerID){
        _partnerID = partnerID;
        _paydate = paydate;
        _id = id;
    }

    public String getPartnerID(){
        return _partnerID;
    }

    public int getId(){
        return _id;
    }

    public int getPayDate(){
        return _paydate;
    }
}