package ggc.exceptions;

public class UnknownTransactionIDException extends Exception {

    private int _key;

    public UnknownTransactionIDException(int key) {
        _key = key;
    }

    public int getKey() {
        return _key;
    }
}
