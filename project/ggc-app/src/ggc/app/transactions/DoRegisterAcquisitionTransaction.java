package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    //FIXME maybe add command fields
    addStringField("partnerID", Prompt.partnerKey());
    addStringField("prodID", Prompt.productKey());
    addRealField("price", Prompt.price());
    addIntegerField("quantity", Prompt.amount());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    
      _receiver.registerAcquisition(stringField("partnerID"), stringField("prodID"), realField("price"), integerField("quantity"));
    
  }
}
