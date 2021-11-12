package ggc;

import java.io.Serializable;

public interface ObserverSubject extends Serializable {

    /**
     * Registers a Partner (Observer) to a Product
     * 
     * @param o
     */
    public void registerObserver(ProductObserver o);

    /**
     * Removes a Partner (Observer) from a product
     * 
     * @param o
     */
    public void removeObserver(ProductObserver o);

    /**
     * Notify all Observers about the Product occasion (BARGAIN)
     * 
     * @param occasion
     */
    public void notifyBargain(int price);

    /**
     * Notify all Observers about the Product occasion (New)
     * 
     * @param occasion
     */
    public void notifyNew(int price);

}
