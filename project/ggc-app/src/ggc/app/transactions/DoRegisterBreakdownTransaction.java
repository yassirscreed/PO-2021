package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.exceptions.UnknownPartnerIDException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.exceptions.UnknownProductIDException;
import ggc.app.exceptions.UnavailableProductException;
import ggc.exceptions.UnavailableProductQuantityException;
import pt.tecnico.uilib.forms.*;

/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    //FIXME maybe add command fields
    addStringField("partnerID", Prompt.partnerKey());
    addStringField("prodID", Prompt.productKey());
    addIntegerField("quantity", Prompt.amount());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    String partnerID = stringField("partnerID");
    String prodID = stringField("prodID");
    int quantity = integerField("quantity");
    try{
      _receiver.registerBreakdown(partnerID,prodID,quantity);
    } catch (UnknownPartnerIDException e){
      throw new UnknownPartnerKeyException(e.getKey());
    } catch (UnknownProductIDException e){
      throw new UnknownProductKeyException(e.getKey());
    } catch (UnavailableProductQuantityException e){
      throw new UnavailableProductException(e.getID(), e.getRequested(), e.getAvailable());
    }

  }
}
