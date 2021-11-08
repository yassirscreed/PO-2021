package ggc.exceptions;

public class UnknownProductIDException extends Exception {

    private String _key;

    public UnknownProductIDException(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}
