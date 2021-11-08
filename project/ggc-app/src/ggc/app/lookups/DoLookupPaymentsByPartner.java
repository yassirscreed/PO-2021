package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownPartnerKeyException;
//FIXME import classes

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    //FIXME add command fields
    addStringField("partnerID", Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    //FIXME implement command
    if (!_receiver.idExists(stringField("partnerID")))
      throw new UnknownPartnerKeyException(stringField("partnerID"));
    else{
    _display.addLine(_receiver.lookupPaymentsByPartner(stringField("partnerID")));
    _display.display();
    }
  }
}
