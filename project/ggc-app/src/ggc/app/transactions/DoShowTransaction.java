package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownTransactionKeyException;
import ggc.exceptions.UnknownTransactionIDException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    //FIXME maybe add command fields
    addIntegerField("transactionID", Prompt.transactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    try {
      _display.add(_receiver.showTransaction(integerField("transactionID")));
      _display.display();
    } catch (UnknownTransactionIDException e) {
      throw new UnknownTransactionKeyException(e.getKey());
    }
  }
}
