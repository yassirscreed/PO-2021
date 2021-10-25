package ggc;

public class SelectionStatus implements StatusState {

    @Override
    public void previousStatus(Partner partner) {
        partner.changeStatus(new NormalStatus());
    }

    @Override
    public void nextStatus(Partner partner) {
        partner.changeStatus(new EliteStatus());
    }

    /**
     * @Override public int statusPrice();
     * 
     * @Override public int Buy();
     */

    @Override
    public String getStatus() {
        return "SELECTION";
    }
}
