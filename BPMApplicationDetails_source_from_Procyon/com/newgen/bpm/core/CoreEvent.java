

package com.newgen.bpm.core;

import com.newgen.bpm.request.BPMApplicationDetailsRequest;

public class CoreEvent
{
    private String CID;
    private String applNo;
    private String mobileNumber;
    private String emailID;
    private String passportNumber;
    private String EIDANumber;
    private String fullName;
    public BPMApplicationDetailsRequest request;
    
    public CoreEvent(final String CID, final String applNo, final String mobileNumber, final String emailID, final String passportNumber, final String EIDANumber, final String fullName, final BPMApplicationDetailsRequest request) {
        this.CID = CID;
        this.applNo = applNo;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.passportNumber = passportNumber;
        this.EIDANumber = EIDANumber;
        this.fullName = fullName;
    }
    
    public String getCID() {
        return this.CID;
    }
    
    public void setCID(final String cID) {
        this.CID = cID;
    }
    
    public String getApplNo() {
        return this.applNo;
    }
    
    public void setApplNo(final String applNo) {
        this.applNo = applNo;
    }
    
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getEmailID() {
        return this.emailID;
    }
    
    public void setEmailID(final String emailID) {
        this.emailID = emailID;
    }
    
    public String getPassportNumber() {
        return this.passportNumber;
    }
    
    public void setPassportNumber(final String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
    public String getEIDANumber() {
        return this.EIDANumber;
    }
    
    public void setEIDANumber(final String eIDANumber) {
        this.EIDANumber = eIDANumber;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
