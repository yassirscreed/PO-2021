package ggc;

import java.util.HashMap;

public class DerivedProduct extends Product {
    private double _agravamento;
    private String _components;
    private HashMap<String, Integer> _derivates = new HashMap<String, Integer>();

    public DerivedProduct(String id, Double maxprice, int stock, double agravamento, String components) {
        super(id, maxprice, stock);
        _agravamento = agravamento;
        _components = components;
    }

    public double getAgravamento() {
        return _agravamento;
    }

    public String getComponents() {
        return _components;
    }

    public void insertHashMap(String components) {
        String[] allcomp = components.split("#");
        for (String comp : allcomp) {
            String[] comp_quant = comp.split(":");
            // System.out.println(comp_quant[0] + comp_quant[1]);
            _derivates.put(comp_quant[0], Integer.parseInt(comp_quant[1]));
        }
    }

    // missing getHashmap aka collections...

    @Override
    public String toString() {
        return getProdID() + "|" + Math.round(getPrice()) + "|" + getStockTotal() + "|" + getAgravamento() + "|"
                + getComponents();
    }

}
