/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author User
 */
public class PurchaseItemsModel {

    private int batchNo;
    private int prodID;
    private String prodName;
    private String manfDate;
    private String expDate;
    private int quantity;
    private double price;

    public PurchaseItemsModel(int batchNo, int prodID, String prodName, String manfDate, String expDate, int quantity, double price) {
        this.batchNo = batchNo;
        this.prodID = prodID;
        this.prodName = prodName;
        this.manfDate = manfDate;
        this.expDate = expDate;
        this.quantity = quantity;
        this.price = price;
    }

    public int getBatchNo() {
        return batchNo;
    }

    public int getProdID() {
        return prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public String getManfDate() {
        return manfDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

}
