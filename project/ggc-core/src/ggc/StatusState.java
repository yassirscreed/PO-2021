package ggc;

import ggc.exceptions.MaxStatusException;
import ggc.exceptions.MinStatusException;

public interface StatusState {
    void nextStatus(Partner partner) throws MaxStatusException;

    void previousStatus(Partner partner) throws MinStatusException;

    String getStatus();

    // int statusPrice();
    // int buy();
}
