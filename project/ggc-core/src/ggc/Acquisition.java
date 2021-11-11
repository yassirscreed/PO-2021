package ggc;

import java.io.Serializable;
import java.lang.Math.*;

public class Acquisition extends Transaction{

    private double _payvalue;

    public Acquisition(int id, String partnerID, String productID, int quantity, int paydate, String type, double payvalue){
        super(id, partnerID, productID, quantity, paydate, type);
        _payvalue = payvalue;
    }

    public double getPayValue(){
        return _payvalue;
    }

    public void setPayValue(int payvalue){
        _payvalue = payvalue;
    }

    
    @Override
    public String toString(){
        return "COMPRA|" + getId() + "|" + getPartnerID() + "|" + getProductID() + "|" + getQuantity() + "|" + (int)Math.round(getPayValue()) + "|" + getDate();
    }
}
