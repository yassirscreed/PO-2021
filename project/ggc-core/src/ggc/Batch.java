package ggc;

import java.io.Serializable;

public class Batch implements Serializable{

    private String _prodID;
    private String _partnerID;
    private int _quantity;
    private double _price;

    public Batch(String prodID, String partnerID, int quantity, double price){
        _prodID = prodID;
        _partnerID = partnerID;
        _quantity = quantity;
        _price = price;
    }

    public int getQuantity(){
        return _quantity;
    }

    public String getPartnerID(){
        return _partnerID;
    }

    public String getProdID(){
        return _prodID;
    }

    public double getPrice(){
        return _price;
    }

    @Override
    public String toString(){
        return getProdID() + "|" + getPartnerID() + "|" + getPrice() + "|" + getQuantity() + "\n"; 
    }
    
}
