package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownPartnerKeyException;
//FIXME import classes

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    // FIXME add command fields
    addStringField("id", Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    // FIXME implement command
    if (!_receiver.idExists(stringField("id")))
      throw new UnknownPartnerKeyException(stringField("id"));
    else
      _display.add(_receiver.showPartner(stringField("id")));
    _display.display();
  }

}
