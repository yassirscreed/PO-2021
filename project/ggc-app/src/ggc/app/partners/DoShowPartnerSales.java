package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.exceptions.UnknownPartnerIDException;

/**
 * Show all transactions for a specific partner.
 */
class DoShowPartnerSales extends Command<WarehouseManager> {

  DoShowPartnerSales(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER_SALES, receiver);
    //FIXME add command fields
    addStringField("id", Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    //FIXME implement command
    try {
      _display.add(_receiver.showPartnerSales(stringField("id")));
      _display.display();
    } catch (UnknownPartnerIDException e){
      throw new UnknownPartnerKeyException(e.getKey());
    }
  }

}
