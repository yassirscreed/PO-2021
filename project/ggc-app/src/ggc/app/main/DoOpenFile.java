package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.*;
import ggc.exceptions.UnavailableFileException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    // FIXME maybe add command fields
    addStringField("nome", Prompt.openFile());
  }

  @Override
  public final void execute() throws CommandException {
    try {
      // FIXME implement command
      _receiver.load(stringField("nome"));
    } catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    }

  }

}
