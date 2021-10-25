package ggc;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ggc.exceptions.*;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

/** Façade for access. */
public class WarehouseManager {

  /** Name of file storing current store. */
  private String _filename = "";

  /** The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  // FIXME define other attributes
  // FIXME define constructor(s)
  // FIXME define other methods

  // Date
  public int getDate() {
    return _warehouse.getDate();
  }

  public void daysToAdvance(int days) {

    _warehouse.daysToAdvance(days);
  }

  // Balance

  public double getBalance() {
    return _warehouse.getBalance();
  }

  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    // FIXME implement serialization method
    ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
    ous.writeObject(_warehouse);
    ous.close();
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException {
    // FIXME implement serialization method
    try {
      ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      _filename = filename;
      _warehouse = (Warehouse) ois.readObject();
      ois.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

}
