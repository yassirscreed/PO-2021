package ggc;

import java.io.Serializable;

public interface ProductObserver extends Serializable {
    /**
     * 
     * 
     * @param pID    Product ID
     * @param pPrice Product Price
     */
    public void updateBargain(String pID, int price, NotificationDelivery delivery);

    public void updateNew(String pID, int price, NotificationDelivery delivery);

}
