package ggc.exceptions;

public class DuplicatePartnerException extends Exception {

    private String _key;

    public DuplicatePartnerException(String id) {
        _key = id;
    }

    public String getPartnerKey() {
        return _key;
    }
}
