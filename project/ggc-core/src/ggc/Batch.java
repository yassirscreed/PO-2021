package ggc;

import java.io.Serializable;
import java.util.*;
//import ggc.exceptions.UnknownPartnerKeyException;

public class Batch implements Serializable {

    // preco negativo exception
    // remover quando o batch é esgotado
    // ainda por implementar

    private String _prodID;
    private String _partnerID;
    private Product _product;
    private int _quantity;
    private double _price;

    public Batch(String prodID, String partnerID, int quantity, double price) {
        _prodID = prodID;
        _partnerID = partnerID;
        _quantity = quantity;
        _price = price;
        // n sei bem como vou buscar o maplist a warehouse para procurar o product
        // Product _product = _products.get(_prodID);
        /**
         * if (_product == null) throw new UnknownPartnerKeyException();
         */
    }

    public int getQuantity() {
        return _quantity;
    }

    public String getPartnerID() {
        return _partnerID;
    }

    public String getProdID() {
        return _prodID;
    }

    public double getPrice() {
        return _price;
    }

    @Override
    public String toString() {
        return _product.getProdName() + "|" + getPartnerID() + "|" + getPrice() + "|" + getQuantity();
    }
}
