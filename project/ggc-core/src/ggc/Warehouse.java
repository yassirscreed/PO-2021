package ggc;

import java.io.Serializable;
import java.util.*;
import java.io.IOException;
import ggc.exceptions.BadEntryException;
import ggc.exceptions.DuplicatePartnerException;

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

  private int _transactionID = 0;

  private Map<String, Partner> _partners = new TreeMap<String, Partner>();
  private Map<String, Product> _products = new TreeMap<String, Product>();
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

  // Date methods
  public int getDate() {
    return _date;
  }

  public void daysToAdvance(int days) {
    _date += days;
  }

  // Partner

  public void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
    Partner partner = _partners.get(id);
    if (partner != null) {
      throw new DuplicatePartnerException(id);
    }

    Partner partner2 = new Partner(id, name, address);
    _partners.put(id, partner2);
  }

  public boolean idExists(String id) {
    return _partners.containsKey(id);
  }

  public String showPartner(String id) {
    String s = "";
    Partner partner = _partners.get(id);

    s += partner.toString();// + "\n";

    // notification print missing

    return s;
  }

  public Collection<Partner> getPartners() {
    return Collections.unmodifiableCollection(_partners.values());
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
