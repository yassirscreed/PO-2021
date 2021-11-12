package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classess
import ggc.app.exceptions.UnknownTransactionKeyException;
import ggc.exceptions.UnknownTransactionIDException;

/**
 * Receive payment for sale transaction.
 */
public class DoReceivePayment extends Command<WarehouseManager> {

  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    //FIXME add command fields
    addIntegerField("transID", Prompt.transactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    try {
      _receiver.receivePayment(integerField("transID"));
    } catch (UnknownTransactionIDException e) {
      throw new UnknownTransactionKeyException(e.getKey());
    }
  }
}
