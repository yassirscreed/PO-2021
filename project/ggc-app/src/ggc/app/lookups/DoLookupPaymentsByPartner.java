package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.exceptions.UnknownPartnerIDException;

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
    try{
      _display.addLine(_receiver.lookupPaymentsByPartner(stringField("partnerID")));
      _display.display();
    } catch (UnknownPartnerIDException e){
       throw new UnknownPartnerKeyException(e.getKey());
      } 
  }
}
