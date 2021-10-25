package ggc;

import ggc.exceptions.MaxStatusException;

public class EliteStatus implements StatusState {

    @Override
    public void previousStatus(Partner partner) {
        partner.changeStatus(new SelectionStatus());
    }

    @Override
        public void nextStatus(Partner partner) throws MaxStatusException{
            throw new MaxStatusException("Partner already at max status.");
        }

    /**
     * @Override public int statusPrice();
     * 
     * @Override public int Buy();
     */

    @Override
    public String getStatus() {
        return "ELITE";
    }

}
