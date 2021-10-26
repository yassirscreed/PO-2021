package ggc.app.main;

import pt.tecnico.uilib.forms.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.*;
import ggc.exceptions.MissingFileAssociationException;

import java.io.FileNotFoundException;
import java.io.IOException;

import ggc.WarehouseManager;
//FIXME import classes

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  private Form _form = new Form();

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
    // FIXME maybe add command fields
    /**
     * if (_receiver.getFilename() == "") { addStringField("nome",
     * Prompt.newSaveAs()); System.out.println(_receiver.getFilename()); }
     */
  }

  @Override
  public final void execute() throws CommandException {
    // FIXME implement command
    try {
      if (_receiver.getFilename() == "") {
        _receiver.saveAs(_form.requestString(Prompt.newSaveAs()));
      } else
        _receiver.save();
    } catch (MissingFileAssociationException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}