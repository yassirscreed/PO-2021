package ggc;

public class Sale extends Transaction {

    private int _deadline;

    private Double _payvalue;

    private Double _payModded = _payvalue;

    public Sale(int id, String partnerID, String productID, int quantity, int paydate, String type, Double payvalue, int deadline) {
        super(id, partnerID, productID, quantity, paydate);
        _payvalue = payvalue;
        _deadline = deadline;
    }

    public Double getPayValue() {
        return _payvalue;
    }

    public int getDeadline() {
        return _deadline;
    }

    public Double getPayModded() {
        return _payModded;
    }

    public void setPayValue(Double value) {
        _payvalue = value;
    }

    public void pay(int value) {
        _payModded -= value;
    }

    @Override
    public String toString() {
        return "VENDA|" + getId() + "|" + getPartnerID() + "|" + getProductID() + "|" + getQuantity() + "|"
                + (int) Math.round(getPayValue()) + "|" + (int) Math.round(getPayValue()) + "|" + _deadline
                + (getPaidStatus() == true ? "|" + getPayDate() : "");
    }
}
