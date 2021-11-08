package ggc.exceptions;

public class UnknownPartnerIDException extends Exception {

    private String _key;

    public UnknownPartnerIDException(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}
