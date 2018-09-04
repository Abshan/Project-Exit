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
public class SalesModel {
    
    public int SONo;
    public String cusName;
    public String cusPhone;
    public String reqDate;
    public String repName;
    public String region;
    public String orderCreator;
    public String status;

    public SalesModel(int SONo, String cusName, String cusPhone, String reqDate, String repName, String region, String orderCreator, String status) {
        this.SONo = SONo;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.reqDate = reqDate;
        this.repName = repName;
        this.region = region;
        this.orderCreator = orderCreator;
        this.status = status;
    }

    public int getSONo() {
        return SONo;
    }

    public void setSONo(int SONo) {
        this.SONo = SONo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
