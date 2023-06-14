// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.request;

public class BPMApplicationDetailsRequest
{
    private String CID;
    private String applNo;
    private String mobileNumber;
    private String emailID;
    private String passportNumber;
    private String EIDANumber;
    private String fullName;
    private ApplicationAttributes[] ApplicationAttributes;
    
    public void setCID(final String cID) {
        this.CID = cID;
    }
    
    public void setApplNo(final String applNo) {
        this.applNo = applNo;
    }
    
    public String getCID() {
        return this.CID;
    }
    
    public String getApplNo() {
        return this.applNo;
    }
    
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public String getEmailID() {
        return this.emailID;
    }
    
    public String getPassportNumber() {
        return this.passportNumber;
    }
    
    public String getEIDANumber() {
        return this.EIDANumber;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public void setEmailID(final String emailID) {
        this.emailID = emailID;
    }
    
    public void setPassportNumber(final String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    public void setEIDANumber(final String eIDANumber) {
        this.EIDANumber = eIDANumber;
    }
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
