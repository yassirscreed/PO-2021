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

  public double getCBalance() {
    return _warehouse.getCBalance();
  }

  public void acquisitionBalance(double price) {
    _warehouse.acquisitionBalance(price);
  }

  // Partner

  public void registerPartner(String id, String name, String address) throws DuplicatePartnerException {
    _warehouse.registerPartner(id, name, address);
  }

  public boolean idExists(String id) {
    return _warehouse.idExists(id);
  }

  public String showPartner(String id) throws UnknownPartnerIDException {
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

  public String showPartnerAcquisitions(String partnerID) throws UnknownPartnerIDException {
    return _warehouse.showPartnerAcquisitions(partnerID);
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

  public Batch getBatchByProductandPartner(String partnerID, String prodID) {
    return _warehouse.getBatchByProductandPartner(partnerID, prodID);
  }

  public void registerAcquisition(String partnerID, String prodID, double price, int quantity)
      throws UnknownPartnerIDException {
    _warehouse.registerAcquisition(partnerID, prodID, price, quantity);
  }

  public void registerAcquisition(String partnerID, String prodID, double price, int quantity, double alpha,
      String Comps) throws UnknownPartnerIDException {
    _warehouse.registerAcquisition(partnerID, prodID, price, quantity, alpha, Comps);
  }

  public void registerSale(String partnerID, String prodID, int deadline, int quantity)
      throws UnavailableProductQuantityException {
    _warehouse.registerSale(partnerID, prodID, deadline, quantity);
  }
  
  public void registerBreakdown(String partnerID,String prodID,int quantity) throws UnknownPartnerIDException,UnknownProductIDException,UnavailableProductCoreException {
    _warehouse.registerBreakdown(partnerID, prodID, quantity);
  }

  public Collection<Transaction> getTransactions() {
    return _warehouse.getTransactions();
  }

  public String addRecipeCompToString(String prodID, int quantity) {
    return _warehouse.addRecipeCompToString(prodID, quantity);
  }

  public boolean transIdExists(int id) {
    return _warehouse.transIdExists(id);
  }

  public String showTransaction(int id) throws UnknownTransactionIDException {
    return _warehouse.showTransaction(id);
  }

  public String lookupProductBatchesUnderGivenPrice(double priceLimit) {
    return _warehouse.lookupProductBatchesUnderGivenPrice(priceLimit);
  }

  public String lookupPaymentsByPartner(String partnerID) throws UnknownPartnerIDException {
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
