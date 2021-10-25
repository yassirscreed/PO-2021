package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.DuplicatePartnerKeyException;
//FIXME import classes

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    //FIXME add command fields
    addStringField("id", Prompt.partnerKey());
    addStringField("name", Prompt.partnerName());
    addStringField("address", Prompt.partnerAddress());

  }

  @Override
  public void execute() throws CommandException {
    //FIXME implement command
    if(_receiver.idExists(stringField("id")))
        throw new DuplicatePartnerKeyException(stringField("id"));
    else  
        _receiver.registerPartner(stringField("id"), stringField("name"), stringField("address"));  

  }

}
