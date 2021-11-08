package ggc;

public class DeliveryMode extends NotificationDelivery {

    @Override
    public Notification deliverNotification(String occasion, String pID, Double pPrice) {
        return new Notification(occasion, pID, pPrice, "");
    }

}
