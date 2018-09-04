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
public class ProductModel {
    
    public int prodId;
    public String brandName;
    public String prodName;
    public float WSPrice;
    public float MRPrice;
    public String prodSize;
    public String prodCat;

    public ProductModel(int prodId, String brandName, String prodName, float WSPrice, float MRPrice, String prodSize, String prodCat) {
        this.prodId = prodId;
        this.brandName = brandName;
        this.prodName = prodName;
        this.WSPrice = WSPrice;
        this.MRPrice = MRPrice;
        this.prodSize = prodSize;
        this.prodCat = prodCat;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public float getWSPrice() {
        return WSPrice;
    }

    public void setWSPrice(float WSPrice) {
        this.WSPrice = WSPrice;
    }

    public float getMRPrice() {
        return MRPrice;
    }

    public void setMRPrice(float MRPrice) {
        this.MRPrice = MRPrice;
    }

    public String getProdSize() {
        return prodSize;
    }

    public void setProdSize(String prodSize) {
        this.prodSize = prodSize;
    }

    public String getProdCat() {
        return prodCat;
    }

    public void setProdCat(String prodCat) {
        this.prodCat = prodCat;
    }
 
}
