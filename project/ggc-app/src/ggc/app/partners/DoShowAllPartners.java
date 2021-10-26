package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Show all partners.
 */
class DoShowAllPartners extends Command<WarehouseManager> {

  DoShowAllPartners(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PARTNERS, receiver);
  }

  @Override
  public void execute() throws CommandException {
    // FIXME implement command
    for (var partner : _receiver.getPartners()) {
      _display.addLine(partner.toString());
    }
    _display.display();
  }

}
