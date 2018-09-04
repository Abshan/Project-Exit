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
public class PurchaseModel {
    
    public int PONo;
    public String vendorName;
    public String batchNo;
    public String purchaseDate;
    public String manufacDate;
    public String expiryDate;

    public PurchaseModel(int PONo, String vendorName, String batchNo, String purchaseDate, String manufacDate, String expiryDate) {
        
        this.PONo = PONo;
        this.vendorName = vendorName;
        this.batchNo = batchNo;
        this.purchaseDate = purchaseDate;
        this.manufacDate = manufacDate;
        this.expiryDate = expiryDate;
    }



    public int getPONo() {
        return PONo;
    }

    public void setPONo(int PONo) {
        this.PONo = PONo;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getManufacDate() {
        return manufacDate;
    }

    public void setManufacDate(String manufacDate) {
        this.manufacDate = manufacDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    
}
