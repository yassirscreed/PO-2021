package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<WarehouseManager> {

  DoShowGlobalBalance(WarehouseManager receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    // FIXME implement command
    _display.addLine(Message.currentBalance(_receiver.getBalance(),_receiver.getCBalance()));
    // Saldo contabilistico ainda por implementar
    _display.display();
  }

}
