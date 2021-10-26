package ggc;

import java.io.Serializable;

import ggc.exceptions.MinStatusException;

public class NormalStatus implements StatusState, Serializable {

    @Override
    public void previousStatus(Partner partner) throws MinStatusException {
        throw new MinStatusException("Partner already at bottom status.");
    }

    @Override
    public void nextStatus(Partner partner) {
        partner.changeStatus(new SelectionStatus());
    }

    /**
     * @Override public int statusPrice();
     * 
     * @Override public int Buy();
     */

    @Override
    public String getStatus() {
        return "NORMAL";
    }
}
