package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.exceptions.UnknownPartnerIDException;
import pt.tecnico.uilib.forms.*;

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
    String partnerID = stringField("partnerID");
    String prodID = stringField("prodID");
    double price = realField("price");
    int quantity = integerField("quantity");
    try {
      if(!_receiver.productExists(prodID)){
        String haveRecipe = Form.requestString(Prompt.addRecipe());
        if (haveRecipe.equals("yes")|haveRecipe.equals("s")){
          int numComps = Form.requestInteger(Prompt.numberOfComponents());
          double alpha = Form.requestReal(Prompt.alpha());
          String comps = "";
          for(int i = numComps; i > 0; i--){
            String prodID2 = Form.requestString(Prompt.productKey());
            int amount = Form.requestInteger(Prompt.amount());
            comps += _receiver.addRecipeCompToString(prodID2,amount);
          }
          comps = comps.substring(0, comps.length() - 1);
          _receiver.registerAcquisition(partnerID, prodID, price, quantity, alpha, comps);
        } else{
        _receiver.registerAcquisition(partnerID, prodID, price,  quantity);}
      } else{
      _receiver.registerAcquisition(partnerID, prodID, price,  quantity);}
    } 
    catch (UnknownPartnerIDException e){
      throw new UnknownPartnerKeyException(e.getKey());
    }
  }
}
