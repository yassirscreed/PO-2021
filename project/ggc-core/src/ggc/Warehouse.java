package ggc;

import java.io.Serializable;
import java.text.Collator;
//import java.sql.BatchUpdateException;
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

  private int batches = 0;
  // private int _transactionID = 0;

  private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
  private Map<Integer, Batch> _batches = new TreeMap<Integer, Batch>();

  // private Map<Integer, Transaction> _transactions = new TreeMap<Integer,
  // Transaction>();

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

  // Batch and Products

  public void registerSimpleProduct(String ID, Double maxprice, int stock) {
    Product product = new SimpleProduct(ID, maxprice, stock);
    _products.put(ID, product);
  }

  public void registerDerivedProduct(String ID, Double maxprice, int stock, double agravamento, String components) {
    Product product = new DerivedProduct(ID, maxprice, stock, agravamento, components);
    _products.put(ID, product);
  }

  public String showProduct(Product p) {
    String s = "";
    s += p.toString();
    return s;
  }

  public String showProducts() {
    String s = "";

    ArrayList<String> prods = new ArrayList<String>(_products.keySet());
    Collator collator = Collator.getInstance(Locale.getDefault());
    Collections.sort(prods, collator);

    for (String e : prods) {
      s += showProduct(_products.get(e)) + "\n";
    }
    return s.replaceAll("[\n\r]$", "");
  }

  public Product getProduct(String id) {
    return _products.get(id);
  }

  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(_products.values());
  }

  public void registerBatch(Product product, String partnerID, int quantity, double price) {

    Batch batch = new Batch(product, partnerID, quantity, price);
    _batches.put(batches++, batch);
  }

  public Collection<Batch> getBatches() {
    return Collections.unmodifiableCollection(_batches.values());
  }

  public String showBatches() {
    String s = "";

    for (Map.Entry<Integer, Batch> e : _batches.entrySet()) {
      s += e.getValue().toString();

    }
    return s;
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
    Pattern batch_sPattern = Pattern.compile("^(BATCH_S)");
    Pattern batch_mPattern = Pattern.compile("^(BATCH_M)");
    if (partnerPattern.matcher(fields[0]).matches()) {
      registerPartner(fields[1], fields[2], fields[3]);
    } else if (batch_sPattern.matcher(fields[0]).matches()) {
      String prodID = fields[1];
      Double price = Double.parseDouble(fields[3]);
      int quantity = Integer.parseInt(fields[4]);
      registerSimpleProduct(prodID, price, quantity);
      registerBatch(getProduct(prodID), fields[2], quantity, price);
    } else if (batch_mPattern.matcher(fields[0]).matches()) {
      String prodID = fields[1];
      Double price = Double.parseDouble(fields[3]);
      int quantity = Integer.parseInt(fields[4]);
      Double agravamento = Double.parseDouble(fields[5]);
      String receita = fields[6];
      registerDerivedProduct(prodID, price, quantity, agravamento, receita);
      registerBatch(getProduct(prodID), fields[2], quantity, price);

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
