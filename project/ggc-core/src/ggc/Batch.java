package ggc;

import java.io.Serializable;
import java.util.Comparator;
import java.lang.Math;

public class Batch implements Serializable{

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
    public static class SortBatches implements Serializable, Comparator<Batch>{

        public int compare(Batch Batch1, Batch Batch2){
            if (Batch1.getProd().getProdID().equals(Batch2.getProd().getProdID())){
                if(Batch1.getPartnerID().equals(Batch2.getPartnerID())){
                    if (((int)Math.round(Batch1.getPrice()) - (int)Math.round(Batch2.getPrice()))==0)
                        return Batch1.getQuantity() - Batch2.getQuantity();
                    return ((int)Math.round(Batch1.getPrice()) - (int)Math.round(Batch2.getPrice()));
                    
                }
                return Batch1.getPartnerID().compareTo(Batch2.getPartnerID());
            }
            return Batch1.getProd().getProdID().compareTo(Batch2.getProd().getProdID());
        }
    }
}
