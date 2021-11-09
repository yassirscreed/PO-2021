package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.exceptions.UnknownPartnerIDException;
import ggc.app.exceptions.UnknownPartnerKeyException;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    // FIXME maybe add command fields
    addStringField("ID", Prompt.partnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    // FIXME implement command
    try {
      _display.addLine(_receiver.showBatchesByPartner(stringField("ID")));
      _display.display();
    } catch (UnknownPartnerIDException e) {
      throw new UnknownPartnerKeyException(e.getKey());
    }
  }

}
