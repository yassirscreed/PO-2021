package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.InvalidDateException;
//FIXME import classes

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    // FIXME add command fields
    addIntegerField("days", Prompt.daysToAdvance());

  }

  @Override
  public final void execute() throws CommandException {
    // FIXME implement command
    if (integerField("days") <= 0)
      throw new InvalidDateException(integerField("days"));
    else
      _receiver.daysToAdvance(integerField("days"));
  }

}
