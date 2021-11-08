package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductBatchesUnderGivenPrice extends Command<WarehouseManager> {

  public DoLookupProductBatchesUnderGivenPrice(WarehouseManager receiver) {
    super(Label.PRODUCTS_UNDER_PRICE, receiver);
    //FIXME add command fields
    addRealField("priceLimit", Prompt.priceLimit());
  }

  @Override
  public void execute() throws CommandException {
    //FIXME implement command
    _display.addLine(_receiver.lookupProductBatchesUnderGivenPrice(realField("priceLimit")));
    _display.display();
  }

}
