package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.exceptions.UnknownPartnerIDException;
import ggc.exceptions.UnknownProductIDException;

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    // FIXME add command fields
    addStringField("key", Prompt.partnerKey());
    addStringField("ID", Prompt.productKey());
  }

  @Override
  public void execute() throws CommandException {
    // FIXME implement command
    try {
      _receiver.toggleNotifications(stringField("key"), stringField("ID"));
    } catch (UnknownPartnerIDException e) {
      throw new UnknownPartnerKeyException(e.getKey());
    } catch (UnknownProductIDException e) {
      throw new UnknownProductKeyException(e.getKey());
    }
  }

}
