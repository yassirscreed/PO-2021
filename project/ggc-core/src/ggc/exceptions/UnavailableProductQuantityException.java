package ggc.exceptions;

public class UnavailableProductQuantityException extends Exception {
    private String _prodID;
    private int _requested;
    private int _available;

    public UnavailableProductQuantityException(String prodID, int requested, int available) {
        _prodID = prodID;
        _requested = requested;
        _available = available;
    }

    public String getID() {
        return _prodID;
    }

    public int getRequested() {
        return _requested;
    }

    public int getAvailable() {
        return _available;
    }

}
