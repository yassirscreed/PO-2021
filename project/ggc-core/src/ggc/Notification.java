package ggc;

import java.io.Serializable;

public class Notification implements Serializable {

    private String _occasion;

    private String _pID;

    private int _price;

    private String _deliveryMode;

    public Notification(String occasion, String pID, int price, String deliveryMode) {
        _occasion = occasion;
        _pID = pID;
        _price = price;
        _deliveryMode = deliveryMode;
    }

    public String getOccasion() {
        return _occasion;
    }

    public String getpID() {
        return _pID;
    }

    public int getPrice() {
        return _price;
    }

    public String getDeliveryMode() {
        return _deliveryMode;
    }

    public String getString() {
        return getOccasion() + "|" + getpID() + "|" + getPrice();
    }

    public String ToString() {
        return getDeliveryMode() + getOccasion() + "|" + getpID() + "|" + getPrice();
    }

}
