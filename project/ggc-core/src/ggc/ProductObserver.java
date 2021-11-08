package ggc;

import java.io.Serializable;

public interface ProductObserver extends Serializable {
    /**
     * 
     * @param occasion Occasion for the notification (BARGAIN or NEW)
     * @param pID      Product ID
     * @param pPrice   Product Price
     */
    public void update(String occasion, String pID, Double pPrice);

}
