package ggc;

import java.util.*;

public class Batch extends Product {

    // preco negativo exception
    // remover quando o batch é esgotado
    // ainda por implementar

    private Partner _partner;

    private int _quantity;

    private double _price;

    public Batch(Partner partner, int quantity, double price) {
        super("a", 0, 0);
    }

}
