package ggc;

import java.io.Serializable;

public class Acquisition extends Transaction{

    private int _payvalue;

    public Acquisition(int id, String partnerID, String productID, int quantity, int paydate, int payvalue){
        super(id, partnerID, productID, quantity, paydate);
        _payvalue = payvalue;
    }

    public int getPayValue(){
        return _payvalue;
    }

    public void setPayValue(int payvalue){
        _payvalue = payvalue;
    }
    
    @Override
    public String toString(){
        return "COMPRA|" + getId() + "|" + getPartnerID() + "|" + getProductID() + "|" + getQuantity() + "|" + getPayValue() + "|" + getPayDate();
    }
}
