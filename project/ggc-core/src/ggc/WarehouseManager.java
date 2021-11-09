package ggc;

import java.io.*;
import java.util.Collection;

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

  public String getFilename() {
    return _filename;
  }

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

  // Partner

  public void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
    _warehouse.registerPartner(id, name, address);
  }

  public boolean idExists(String id) {
    return _warehouse.idExists(id);
  }

  public String showPartner(String id) {
    return _warehouse.showPartner(id);
  }

  public Partner getPartner(String id) {
    return _warehouse.getPartner(id);
  }

  public Collection<Partner> getPartners() {
    return _warehouse.getPartners();
  }

  public void toggleNotifications(String partnerID, String prodID)
      throws UnknownPartnerIDException, UnknownProductIDException {
    _warehouse.toggleNotifications(partnerID, prodID);
  }

  // Products and Batches

  public String showProducts() {
    return _warehouse.showProducts();
  }

  public Product getProduct(String id) {
    return _warehouse.getProduct(id);
  }

  public boolean productExists(String id) {
    return _warehouse.productExists(id);
  }

  public Collection<Product> getProducts() {
    return _warehouse.getProducts();
  }

  public void registerBatch(Product product, String partnerID, int quantity, double price) {
    _warehouse.registerBatch(product, partnerID, quantity, price);
  }

  public String showBatches() {
    return _warehouse.showBatches();
  }

  public String showBatchesByPartner(String partnerID) throws UnknownPartnerIDException {
    return _warehouse.showBatchesByPartner(partnerID);
  }

  public String showBatchesByProduct(String productID) throws UnknownProductIDException {
    return _warehouse.showBatchesByProduct(productID);
  }

  public void registerAcquisition(String partnerID, String prodID, double price, int quantity) {
    _warehouse.registerAcquisition(partnerID, prodID, price, quantity);
  }

  public Collection<Transaction> getTransactions() {
    return _warehouse.getTransactions();
  }

  public String lookupProductBatchesUnderGivenPrice(double priceLimit) {
    return _warehouse.lookupProductBatchesUnderGivenPrice(priceLimit);
  }

  public String lookupPaymentsByPartner(String partnerID) {
    return _warehouse.lookupPaymentsByPartner(partnerID);
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
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }

  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException | DuplicatePartnerException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

}
