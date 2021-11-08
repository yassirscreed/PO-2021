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
     * Wipes all notications public void wipeNotifications();
     */

    /**
     * Notify all Observers about the Product occasion (BARGAIN or NEW)
     * 
     * @param occasion
     */
    public void notifyObservers(String occasion);
}
