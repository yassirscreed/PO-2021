package ggc;

import java.io.Serializable;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ggc.exceptions.BadEntryException;
import ggc.exceptions.DuplicatePartnerException;
import java.util.regex.Pattern;

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

  private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);
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
  void importFile(String txtfile)
      throws IOException, BadEntryException, DuplicatePartnerException /* FIXME maybe other exceptions */ {
    // FIXME implement method
    try {
      BufferedReader in = new BufferedReader(new FileReader(txtfile));
      String s;
      while ((s = in.readLine()) != null) {
        String line = new String(s.getBytes(), "UTF-8");

        String[] fields = line.split("\\|");
        registerFromFields(fields);
      }
      in.close();
    } catch (IOException | BadEntryException | DuplicatePartnerException e) {
      e.printStackTrace();
    }
  }

  void registerFromFields(String[] fields) throws IOException, BadEntryException, DuplicatePartnerException {
    Pattern partnerPattern = Pattern.compile("^(PARTNER)");
    // Pattern batch_sPattern = Pattern.compile("^(BATCH_S)") ainda n foi
    // implementado
    // Pattern batch_mPattern = Pattern.compile("^(BATCH_M)")
    if (partnerPattern.matcher(fields[0]).matches()) {
      registerPartner(fields[1], fields[2], fields[3]);
    }
    /**
     * else if(batch_sPattern.matcher(field[0]).matches()){
     * 
     * } else if(batch_mPattern.matcher(field[0]).matches()){
     * 
     * }
     */
    else
      throw new BadEntryException(fields[0]);

  }

}
