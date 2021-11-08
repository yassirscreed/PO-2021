package ggc;

import java.io.Serializable;

public class Notification implements Serializable {

    private String _occasion;

    private String _pID;

    private Double _pPrice;

    private String _deliveryMode;

    public Notification(String occasion, String pID, Double pPrice, String deliveryMode) {
        _occasion = occasion;
        _pID = pID;
        _pPrice = pPrice;
        _deliveryMode = deliveryMode;
    }

    public String getOccasion() {
        return _occasion;
    }

    public String getpID() {
        return _pID;
    }

    public Double getpPrice() {
        return _pPrice;
    }

    public String getDeliveryMode() {
        return _deliveryMode;
    }

    public String ToString() {
        return getDeliveryMode() + getOccasion() + "|" + getpID() + "|" + getpPrice();
    }

}
