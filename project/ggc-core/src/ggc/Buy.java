package ggc;

import java.util.TreeMap;
import java.util.Map;

public class Buy extends Transaction{
    
    // string -> nome do produto, int -> quantidade
    private Map<String, Integer> _prodsforbuy = new TreeMap<String, Integer>();
    // preço total a pagar
    private double _pricePay = 0;
    

    public Buy(int date, int id, String partnerID){
        super(date,id,partnerID);
    }

    public void addProdForBuy(String productID, int quant, double price){
        _prodsforbuy.add(productID,quant);
        _pricePay += (price*quant);
    }

    public void remProdForBuy(String productID){
        //falta exception de n haver este produto
        _prodsforbuy.remove(productID);
    }

    public double getprice(){
        return _pricePay;
    }

    public TreeMap<String, Integer> getProdsForBuy(){
        return _prodsforbuy;
    }

    //faltam os preços
    @Override
    public String toString(){
        String bufferString = "";
        for(Map.Entry<String, Integer> entry : _prodsforbuy.entrySet()){
            bufferString += "COMPRA|" + getId() + "|" + getPartnerID() + "|" + "|" + 
            entry.getKey() + "|" + entry.getValue() + "|" + getPrice() + "|" + getPayDate() + 
            "\n";
        }
        return bufferString;
    }
}
