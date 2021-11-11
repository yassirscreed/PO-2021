package ggc;

import java.io.Serializable;

public class Breakdown extends Transaction{


    Breakdown(int id, String partnerID, String productID, int quantity, int date, String type){
        super(id, partnerID, productID, quantity, date, type);
    }

}
