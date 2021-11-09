package ggc.exceptions;

public class UnknownTransactionIDException {

    private int _key;

    public UnknownTransactionIDException(int key) {
        _key = key;
    }

    public int getKey() {
        return _key;
    }
}
