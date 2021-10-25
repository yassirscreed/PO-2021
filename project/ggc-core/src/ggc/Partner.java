package ggc;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Partner {

    private String _id;

    private String _name;

    private String _address;

    private int _points = 0;

    private List<Transaction> _transactions = new ArrayList<Transaction>();

    private Status _status = new NormalStatus();

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

    public void changePoints(int points) {
        _points = points;
    }

    // Status
    public void changeStatus(Status status) {
        _status = status;
    }

    public void updateStatus() {
        if (_points > 2000)
            _status.changeStatus(new SelectionStatus());
        else if (_points > 25000)
            _status.changeStatus(new EliteStatus());
    }

    public Status getStatus() {
        return _status;
    }

    // public int statusPrice(){}
    // public int buy(){}
}
