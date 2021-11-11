package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.exceptions.UnavailableProductQuantityException;
import ggc.app.exceptions.UnavailableProductException;

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    // FIXME maybe add command fields
    addStringField("partnerID", Prompt.partnerKey());
    addIntegerField("date", Prompt.paymentDeadline());
    addStringField("prodID", Prompt.productKey());
    addIntegerField("quantity", Prompt.amount());
  }

  @Override
  public final void execute() throws CommandException {
    // FIXME implement command
    try {
      _receiver.registerSale(stringField("partnerID"), stringField("prodID"), integerField("date"),
          integerField("quantity"));
    } catch (UnavailableProductQuantityException e) {
      throw new UnavailableProductException(e.getID(), e.getRequested(), e.getAvailable());
    }
  }

}
