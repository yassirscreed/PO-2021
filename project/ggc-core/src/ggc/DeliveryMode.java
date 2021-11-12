package ggc;

public class DeliveryMode extends NotificationDelivery {

    @Override
    public Notification deliverNotification(String occasion, String pID, int price) {
        return new Notification(occasion, pID, price, "");
    }

}
