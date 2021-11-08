package ggc;

import java.io.Serializable;

public abstract class NotificationDelivery implements Serializable {

    public abstract Notification deliverNotification(String occasion, String pID, Double pPrice);
}
