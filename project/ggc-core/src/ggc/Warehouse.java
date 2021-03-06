package ggc;

// Imports.
import java.io.Serializable;
import java.text.Collator;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ggc.exceptions.*;

import java.util.regex.Pattern;
import ggc.Batch.SortBatches;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  /** Warehouse Date. */
  private int _date = 0;

  /** Balance of warehouse. */
  private double _balance = 0;

  /** Number of transactions */
  private int _numtrans = 0;

  /** Partners of warehouse. */
  private Map<String, Partner> _partners = new TreeMap<String, Partner>(String.CASE_INSENSITIVE_ORDER);

  /** Products of warehouse. */
  private Map<String, Product> _products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);

  /** Batches of warehouse. */
  private List<Batch> _batches = new LinkedList<Batch>();

  /** Transactions of warehouse. */
  private Map<Integer, Transaction> _transactions = new TreeMap<Integer, Transaction>();

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
    addPartnerNotifications(partner2);
    _partners.put(id, partner2);
  }

  /**
   * Adds a new Partner to product notifications (observer)
   * 
   * @param p
   */
  public void addPartnerNotifications(Partner p) {
    for (Map.Entry<String, Product> entry : _products.entrySet()) {
      entry.getValue().registerObserver(p);
    }
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
   * @param id
   * @returns a specified Partner
   */
  public Partner getPartner(String id) {
    return _partners.get(id);
  }

  /**
   * 
   * Returns a single string containing Partner information
   * 
   * @param id
   * @return
   */
  public String showPartner(String id) throws UnknownPartnerIDException {
    if (_partners.containsKey(id)) {
      String s = "";
      Partner partner = _partners.get(id);

      s += partner.toString();

      for (var n : partner.getPartnerNotifications()) {
        s += "\n" + n.getString();
      }
      partner.wipeNotifications();
      return s;
    } else
      throw new UnknownPartnerIDException(id);
  }

  /**
   * Returns a Collection containing Partner Values
   * 
   */
  public Collection<Partner> getPartners() {
    return Collections.unmodifiableCollection(_partners.values());
  }

  /**
   * Toggles notifications (on/off) for a specific partner and product
   * 
   * @param partnerID
   * @param prodID
   * @throws UnknownPartnerIDException
   * @throws UnknownProductIDException
   */
  public void toggleNotifications(String partnerID, String prodID)
      throws UnknownPartnerIDException, UnknownProductIDException {
    if (_products.get(prodID) != null) {
      if (_partners.get(partnerID) != null) {
        _products.get(prodID).toggleNotifications(_partners.get(partnerID));
      } else {
        throw new UnknownPartnerIDException(partnerID);
      }
    } else {
      throw new UnknownProductIDException(prodID);
    }
  }

  // Balance
  /**
   * Returns Warehouse Balance
   */
  public double getBalance() {
    return _balance;
  }

  /* Returns Warehouse Balance */
  public double getCBalance() {
    return _balance;
  }

  /* Reduces Warehouse balance with the acquisition price */
  public void acquisitionBalance(double price) {
    _balance -= price;
  }

  /* Adds Warehouse balance with the sale price */
  public void addSaleBalance(double price) {
    _balance += price;
  }

  /**
   * Registers payment for a specific Transaction
   * 
   * @param transID
   * @throws UnknownTransactionIDException
   */
  public void receivePayment(int transID) throws UnknownTransactionIDException {
    if (_transactions.containsKey(transID)) {
      if (_transactions.get(transID).getType().equals("Sale")) {
        Transaction trans = _transactions.get(transID);
        Sale sale = (Sale) trans;
        if (sale.getPaidStatus() == false) {
          sale.itsPaid();
          addSaleBalance(sale.getPayValue());
        }
      }
    } else
      throw new UnknownTransactionIDException(transID);
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

  // Checks if product exists
  public boolean productExists(String id) {
    return _products.containsKey(id);
  }

  /**
   * Shows Partner Acquisitions
   * 
   * @param partnerID
   * @return
   * @throws UnknownPartnerIDException
   */
  public String showPartnerAcquisitions(String partnerID) throws UnknownPartnerIDException {
    if (_partners.containsKey(partnerID)) {
      String s = "";
      ArrayList<Integer> _Transactions = new ArrayList<Integer>(_transactions.keySet());
      for (int transID : _Transactions) {
        if (_transactions.get(transID).getType().equals("Acquisition")) {
          if (_transactions.get(transID).getPartnerID().equals(partnerID))
            s += _transactions.get(transID).toString() + "\n";
        }
      }
      return s.replaceAll("[\n\r]$", "");
    } else
      throw new UnknownPartnerIDException(partnerID);
  }

  /**
   * Shows Partner Sales
   * 
   * @param partnerID
   * @return
   * @throws UnknownPartnerIDException
   */
  public String showPartnerSales(String partnerID) throws UnknownPartnerIDException {
    if (_partners.containsKey(partnerID)) {
      String s = "";
      ArrayList<Integer> _Transactions = new ArrayList<Integer>(_transactions.keySet());
      for (int transID : _Transactions) {
        if (_transactions.get(transID).getType().equals("Sale")) {
          if (_transactions.get(transID).getPartnerID().equals(partnerID))
            s += _transactions.get(transID).toString() + "\n";
        }
      }
      return s.replaceAll("[\n\r]$", "");
    } else
      throw new UnknownPartnerIDException(partnerID);
  }

  /**
   * 
   * @returns a Collection with all Product values
   */
  public Collection<Product> getProducts() {
    return Collections.unmodifiableCollection(_products.values());
  }

  /**
   * Adds a new product to Partner notifications
   * 
   * @param p
   */
  public void addProductNotifications(Product p) {
    for (Map.Entry<String, Partner> entry : _partners.entrySet()) {
      p.registerObserver(entry.getValue());
    }
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
    List<Batch> _newBatchSort = _batches;
    Collections.sort(_newBatchSort, new Comparator<Batch>() {
      public int compare(Batch p1, Batch p2) {
        return p1.getProd().getProdID().compareToIgnoreCase(p2.getProd().getProdID());
      }
    });
    for (Batch e : _newBatchSort) {
      s += e.toString() + "\n";
    }
    return s.replaceAll("[\n\r]$", "");
  }

  /**
   * Shows all Batches by a Partner
   * 
   * @param partnerID
   * @return
   * @throws UnknownPartnerIDException
   */
  public String showBatchesByPartner(String partnerID) throws UnknownPartnerIDException {
    if (_partners.containsKey(partnerID)) {
      String s = "";
      List<Batch> _BatchPartnerSort = new ArrayList<Batch>();
      for (Batch e : _batches) {
        if (e.getPartnerID().equals(partnerID)) {
          _BatchPartnerSort.add(e);
        }
      }
      for (Batch e : _BatchPartnerSort) {
        s += e.toString() + "\n";
      }
      return s.replaceAll("[\n\r]$", "");
    } else
      throw new UnknownPartnerIDException(partnerID);
  }

  /**
   * Shows all Batches by a Product
   * 
   * @param productID
   * @return
   * @throws UnknownProductIDException
   */
  public String showBatchesByProduct(String productID) throws UnknownProductIDException {
    if (_products.containsKey(productID)) {
      String s = "";
      List<Batch> _BatchProductSort = new ArrayList<Batch>();
      for (Batch e : _batches) {
        if (e.getProd().getProdID().equals(productID)) {
          _BatchProductSort.add(e);
        }
      }
      for (Batch e : _BatchProductSort) {
        s += e.toString() + "\n";
      }
      return s.replaceAll("[\n\r]$", "");
    } else
      throw new UnknownProductIDException(productID);
  }

  /**
   * Returns all Batches by a product
   * 
   * @param productID
   * @return
   */
  public List<Batch> getBatchesByProduct(String productID) {
    List<Batch> _BatchProductSort = new ArrayList<Batch>();
    for (Batch e : _batches) {
      if (e.getProd().getProdID().equals(productID)) {
        _BatchProductSort.add(e);
      }
    }
    return _BatchProductSort;
  }

  /**
   * Checks if new batch is eligible for Bargain notification
   * 
   * @param batch
   * @param BatchProduct
   * @return
   */
  public Boolean checkforBargain(Batch batch, List<Batch> BatchProduct) {
    boolean check = false;
    int price = (int) Math.round(batch.getPrice());
    for (Batch e : BatchProduct) {
      if ((int) Math.round(e.getPrice()) < price) {
        check = false;
        break;
      } else {
        check = true;
      }
    }
    return check;
  }

  /**
   * Returns a the cheaper Batch for the product
   * 
   * @param prodID
   * @return
   */
  public Batch getCheaperBatch(String prodID) {
    Batch _lowerbatch = _batches.get(0);
    for (Batch _batch : _batches) {
      if (_batch.getPrice() < _lowerbatch.getPrice()) {
        _lowerbatch = _batch;
      }
    }
    return _lowerbatch;
  }

  // Transactions

  // For Simple Products
  public void registerAcquisition(String partnerID, String prodID, double price, int quantity)
      throws UnknownPartnerIDException {
    if (_partners.containsKey(partnerID)) {
      double payvalue = price * quantity;
      acquisitionBalance(payvalue);
      Acquisition acquisition = new Acquisition(_numtrans, partnerID, prodID, quantity, getDate(), "Acquisition",
          payvalue);
      getPartner(partnerID).addAquisitionValue(payvalue);
      SimpleProduct product = new SimpleProduct(prodID, price, quantity);
      Batch batch = new Batch(product, partnerID, quantity, price);
      registerBatch(product, partnerID, quantity, price);
      if (checkforBargain(batch, getBatchesByProduct(prodID))) {
        // _partners.get(partnerID).updateBargain(prodID, );
        _products.get(prodID).notifyBargain((int) Math.round(price));
      }
      _transactions.put(_numtrans, acquisition);
      _numtrans += 1;
    } else
      throw new UnknownPartnerIDException(partnerID);
  }

  // For Derived Products
  public void registerAcquisition(String partnerID, String prodID, double price, int quantity, double alpha,
      String components) throws UnknownPartnerIDException {
    if (_partners.containsKey(partnerID)) {
      double payvalue = price * quantity;
      acquisitionBalance(payvalue);
      Acquisition acquisition = new Acquisition(_numtrans, partnerID, prodID, quantity, getDate(), "Acquisition",
          payvalue);
      getPartner(partnerID).addAquisitionValue(payvalue);
      DerivedProduct product = new DerivedProduct(prodID, price, quantity, alpha, components);
      product.insertHashMap(components);
      registerBatch(product, partnerID, quantity, price);
      _transactions.put(_numtrans, acquisition);
      _numtrans += 1;
    } else
      throw new UnknownPartnerIDException(partnerID);
  }

  // Registers a Sale Transaction
  public void registerSale(String partnerID, String prodID, int deadline, int quantity)
      throws UnavailableProductQuantityException {
    if (_products.get(prodID).getStockTotal() >= quantity) {
      int i = quantity;
      double price = 0;
      while (i > 0) {
        Batch batch = getCheaperBatch(prodID);
        if (batch.getQuantity() > i) {
          batch.reduceQuantity(i);
          price += i * batch.getPrice();
          i = 0;
        } else { // quantidade do batch for <=
          int batchquantity = batch.getQuantity();
          price += batchquantity * batch.getPrice();
          i -= batchquantity;
          _batches.remove(batch);
        }
      }
      _products.get(prodID).addStock(-quantity);
      Sale sale = new Sale(_numtrans, partnerID, prodID, quantity, getDate(), "Sale", price, deadline);
      _transactions.put(_numtrans, sale);
      _numtrans += 1;
    } else
      throw new UnavailableProductQuantityException(prodID, quantity, _products.get(prodID).getStockTotal());
  }

  // Registers a Breakdown Transaction
  public void registerBreakdown(String partnerID, String prodID, int quantity)
      throws UnknownPartnerIDException, UnknownProductIDException, UnavailableProductQuantityException {
    if (_partners.containsKey(partnerID)) {
      if (_products.containsKey(prodID)) {
        int availableProducts = 0;
        for (Batch b : _batches) {
          if (b.getProd().getProdID().equals(prodID))
            availableProducts += b.getQuantity();
        }
        if (availableProducts - quantity < 0)
          throw new UnavailableProductQuantityException(prodID, quantity, availableProducts);
      } else
        throw new UnknownProductIDException(prodID);

    } else
      throw new UnknownPartnerIDException(partnerID);
  }

  /* Returns a Collection containing Transaction information */
  public Collection<Transaction> getTransactions() {
    return Collections.unmodifiableCollection(_transactions.values());
  }

  // Adds a recipe to String
  public String addRecipeCompToString(String prodID, int quantity) {
    return prodID + ":" + quantity + "#";
  }

  /**
   * 
   * Verifies if a transaction id already exists in _transactions
   * 
   * @param id of a transaction
   * @return a boolean
   */
  public boolean transIdExists(int id) {
    return _transactions.containsKey(id);
  }

  /**
   * Shows the Transaction id information
   * 
   * @param id
   * @return
   * @throws UnknownTransactionIDException
   */
  public String showTransaction(int id) throws UnknownTransactionIDException {
    if (_transactions.containsKey(id)) {
      return _transactions.get(id).toString();
    } else
      throw new UnknownTransactionIDException(id);
  }

  // Lookups

  /**
   * Returns all Batches under a given price
   * 
   * @param priceLimit
   * @return
   */
  public String lookupProductBatchesUnderGivenPrice(double priceLimit) {
    String s = "";
    for (Batch e : _batches) {
      if (e.getPrice() < priceLimit)
        s += e.toString() + "\n";
    }
    return s.replaceAll("[\n\r]$", "");
  }

  /**
   * Returns all payments made by a Partner
   * 
   * @param partnerID
   * @return
   * @throws UnknownPartnerIDException
   */
  public String lookupPaymentsByPartner(String partnerID) throws UnknownPartnerIDException {
    if (_partners.containsKey(partnerID)) {
      String s = "";
      for (Transaction trans : getTransactions()) {
        if (trans.getType().equals("Sale")) {
          if (trans.getPartnerID().equals(partnerID))
            s += trans.toString() + "\n";
        }
      }
      return s.replaceAll("[\n\r]$", "");
    } else
      throw new UnknownPartnerIDException(partnerID);
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
      addProductNotifications(prod);
      registerBatch(prod, fields[2], quantity, price);

    } else if (batch_mPattern.matcher(fields[0]).matches()) {
      String prodID = fields[1];
      Double price = Double.parseDouble(fields[3]);
      int quantity = Integer.parseInt(fields[4]);
      Double agravamento = Double.parseDouble(fields[5]);
      String receita = fields[6];
      DerivedProduct prod = new DerivedProduct(prodID, price, quantity, agravamento, receita);
      addProductNotifications(prod);
      registerBatch(prod, fields[2], quantity, price);

    } else
      throw new BadEntryException(fields[0]);

  }

}
