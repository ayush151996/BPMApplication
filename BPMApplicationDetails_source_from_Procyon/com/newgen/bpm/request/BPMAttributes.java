// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.request;

public class BPMAttributes
{
    private String deliveryStatus;
    private String initiationDate;
    private String productApplied;
    private String customerID;
    private String productName;
    private String EIDANumber;
    private String applicationDetails;
    private String fullName;
    private String applicationStatus;
    private String accountNumber;
    private String currency;
    private String setstatusDescription;
    public String applNo;
    public String emailID;
    public String passportNumber;
    public String mobileNumber;
    public String CID;
    
    public void setCustomerID(final String customerID) {
        this.customerID = customerID;
    }
    
    public void setEIDANumber(final String eIDANumber) {
        this.EIDANumber = eIDANumber;
    }
    
    public void setApplicationDetails(final String applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
    
    public void setCID(final String cID) {
        this.CID = cID;
    }
    
    public String getCID() {
        return this.CID;
    }
    
    public void setApplNo(final String applNo) {
        this.applNo = applNo;
    }
    
    public void setEmailID(final String emailID) {
        this.emailID = emailID;
    }
    
    public String getCustomerID() {
        return this.customerID;
    }
    
    public String getApplicationDetails() {
        return this.applicationDetails;
    }
    
    public String getApplNo() {
        return this.applNo;
    }
    
    public void setPassportNumber(final String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getSetstatusDescription() {
        return this.setstatusDescription;
    }
    
    public void setSetstatusDescription(final String setstatusDescription) {
        this.setstatusDescription = setstatusDescription;
    }
    
    public void setInitiationDate(final String initiationDate) {
        this.initiationDate = initiationDate;
    }
    
    public String getEIDANumber() {
        return this.EIDANumber;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public String getEmailID() {
        return this.emailID;
    }
    
    public String getPassportNumber() {
        return this.passportNumber;
    }
    
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public String getInitiationDate() {
        return this.initiationDate;
    }
    
    public void setProductApplied(final String productApplied) {
        this.productApplied = productApplied;
    }
    
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setApplicationStatus(final String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    
    public void setDeliveryStatus(final String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public String getProductApplied() {
        return this.productApplied;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public String getApplicationStatus() {
        return this.applicationStatus;
    }
    
    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(final String currency) {
        this.currency = currency;
    }
    
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(final String productName) {
        this.productName = productName;
    }
}
