package ggc;

// Imports.
import java.io.Serializable;
import java.text.Collator;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ggc.exceptions.BadEntryException;
import ggc.exceptions.DuplicatePartnerException;
import java.util.regex.Pattern;
import ggc.Batch.SortBatches;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  /** Date. */
  private int _date = 0;

  /** Balance of warehouse. */
  private double _balance = 0;

  /** Partners of warehouse. */
  private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);

  /** Products of warehouse. */
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);

  /** Batches of warehouse. */
  private List<Batch> _batches = new LinkedList<Batch>();

  // Date

  /**
   * @return the date.
   */
  public int getDate() {
    return _date;
  }

  /**
   * @param days to advance.
   * @return the new date.
   */
  public void daysToAdvance(int days) {
    _date += days;
  }

  // Partners

  /**
   * Register a partner
   * 
   * @param int
   * @param name
   * @param address
   * @throws DuplicatePartnerException
   */
  public void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
    Partner partner = _partners.get(id);
    if (partner != null) {
      throw new DuplicatePartnerException(id);
    }

    Partner partner2 = new Partner(id, name, address);
    _partners.put(id, partner2);
  }

  /**
   * 
   * Verifies if a product id already exists in _products
   * 
   * @param id of a product
   * @return a boolean
   */
  public boolean idExists(String id) {
    return _partners.containsKey(id);
  }

  /**
   * 
   * Returns a single string containing Partner information
   * 
   * @param id
   * @return
   */
  public String showPartner(String id) {
    String s = "";
    Partner partner = _partners.get(id);

    s += partner.toString();// + "\n";

    // notification print missing

    return s;
  }

  /**
   * Returns a Collection containing Partner Values
   * 
   */
  public Collection<Partner> getPartners() {
    return Collections.unmodifiableCollection(_partners.values());
  }

  // Balance
  /**
   * Returns Warehouse Balance
   */
  public double getBalance() {
    return _balance;
  }

  // Partners and Batches

  /**
   * 
   * @param p
   * @return a string containing Product p information
   */
  public String showProduct(Product p) {
    String s = "";
    s += p.toString();
    return s;
  }

  /**
   * 
   * @return a string with all Products information
   */
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

  /**
   * 
   * @param id
   * @returns a specified Product
   */
  public Product getProduct(String id) {
    return _products.get(id);
  }

  /**
   * 
   * @returns a Collection with all Product values
   */
  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(_products.values());
  }

  /**
   * Registers a new Batch
   * 
   * @param product
   * @param partnerID
   * @param quantity
   * @param price
   */
  public void registerBatch(Product product, String partnerID, int quantity, double price) {
    Batch batch = new Batch(product, partnerID, quantity, price);
    if (_products.get(product.getProdID()) == null) {
      _products.put(product.getProdID(), product);
    } else {
      if (price > _products.get(product.getProdID()).getPrice())
        _products.get(product.getProdID()).setPrice(price);
      _products.get(product.getProdID()).addStock(quantity);
    }
    _batches.add(batch);
    Collections.sort(_batches, new SortBatches());
  }

  /**
   * 
   * @returns a String with all Batch information
   */
  public String showBatches() {
    String s = "";
    for (Batch e : _batches) {
      s += e.toString() + "\n";
    }
    return s.replaceAll("[\n\r]$", "");
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

  /**
   * Aux function to importFile
   * 
   * @param fields
   * @throws IOException
   * @throws BadEntryException
   * @throws DuplicatePartnerException
   */
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
      SimpleProduct prod = new SimpleProduct(prodID, price, quantity);
      registerBatch(prod, fields[2], quantity, price);

    } else if (batch_mPattern.matcher(fields[0]).matches()) {
      String prodID = fields[1];
      Double price = Double.parseDouble(fields[3]);
      int quantity = Integer.parseInt(fields[4]);
      Double agravamento = Double.parseDouble(fields[5]);
      String receita = fields[6];
      DerivedProduct prod = new DerivedProduct(prodID, price, quantity, agravamento, receita);
      registerBatch(prod, fields[2], quantity, price);

    } else
      throw new BadEntryException(fields[0]);

  }

}
