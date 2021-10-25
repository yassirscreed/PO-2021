package ggc;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import ggc.exceptions.BadEntryException;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods

  private int _date = 0;

  private double _balance = 0;

  /**
   * private Map<String, Partner> _partners = new TreeMap<String, Partner>();
   * private Map<String, Product> _products = new TreeMap<String, Product>();
   * private Map<Integer, Transaction> _transactions = new TreeMap<Integer,
   * Transaction>();
   */

  // Date methods
  public int getDate() {
    return _date;
  }

  public void daysToAdvance(int days) {
    _date += days;
  }

  // Balance

  public double getBalance() {
    return _balance;
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    // FIXME implement method
  }

}
