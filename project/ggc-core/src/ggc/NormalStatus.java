package ggc;

import ggc.exceptions.MinStatusException;

public class Normal implements StatusState {

    @Override
    public void previousStatus(Partner partner) throws MinStatusException{
        throw new MinRankException("Partner already at bottom status.")
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
