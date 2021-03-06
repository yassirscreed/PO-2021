package ggc;

import java.io.Serializable;
import java.lang.Math.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Partner implements Serializable, ProductObserver {

    private String _id;

    private String _name;

    private String _address;

    private int _points = 0;

    private double _aquisitionValue = 0;

    private NotificationDelivery _deliveryMode;

    private List<Notification> _notifications = new ArrayList<Notification>();

    // private List<Transaction> _transactions = new ArrayList<Transaction>();

    private StatusState _status = new NormalStatus();

    public Partner(String id, String name, String address) {
        _id = id;
        _name = name;
        _address = address;
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getAddress() {
        return _address;
    }

    public int getPoints() {
        return _points;
    }

    public double getAquisitionValue() {
        return _aquisitionValue;
    }

    public void addAquisitionValue(double price) {
        _aquisitionValue += price;
    }

    public void changePoints(int points) {
        _points = points;
    }

    // Status
    public void changeStatus(StatusState status) {
        _status = status;
    }

    public void updateStatus() {
        if (_points > 2000)
            this.changeStatus(new SelectionStatus());
        else if (_points > 25000)
            this.changeStatus(new EliteStatus());
    }

    public StatusState getStatus() {
        return _status;
    }

    // public int statusPrice(){}
    // public int buy(){}

    // Notifications

    public Collection<Notification> getPartnerNotifications() {
        return Collections.unmodifiableCollection(_notifications);
    }

    public void wipeNotifications() {
        _notifications = new ArrayList<Notification>();
    }

    @Override
    public void updateBargain(String prodID, int price, NotificationDelivery delivery) {
        _notifications.add(delivery.deliverNotification("BARGAIN", prodID, price));
    }

    @Override
    public void updateNew(String prodID, int price, NotificationDelivery delivery) {
        _notifications.add(delivery.deliverNotification("NEW", prodID, price));
    }

    public String toString() {
        return getId() + "|" + getName() + "|" + getAddress() + "|" + getStatus().getStatus() + "|" + getPoints() + "|"
                + (int) Math.round(getAquisitionValue()) + "|" + 0 + "|" + 0;
    }
}
