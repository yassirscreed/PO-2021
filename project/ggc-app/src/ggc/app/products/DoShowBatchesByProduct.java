package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.exceptions.UnknownProductIDException;
import ggc.app.exceptions.UnknownProductKeyException;

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    // FIXME maybe add command fields
    addStringField("ID", Prompt.productKey());
  }

  @Override
  public final void execute() throws CommandException {
    // FIXME implement command
    try {
      _display.addLine(_receiver.showBatchesByProduct(stringField("ID")));
      _display.display();
    } catch (UnknownProductIDException e) {
      throw new UnknownProductKeyException(e.getKey());
    }
  }

}
