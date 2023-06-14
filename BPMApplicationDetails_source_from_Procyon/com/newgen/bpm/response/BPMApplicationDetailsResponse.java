// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.response;

import com.newgen.bpm.request.BPMApplicationAttributes;

public class BPMApplicationDetailsResponse
{
    public String applicationDetailsList;
    public String applicationDetailsCount;
    public String applicationDetails;
    public String accountNumber;
    private String applicationStatus;
    private String setstatusDescription;
    private BPMApplicationAttributes[] ApplicationAttributes;
    
    public void setApplicationDetailsList(final String applicationDetailsList) {
        this.applicationDetailsList = applicationDetailsList;
    }
    
    public void setApplicationDetails(final String applicationDetails) {
        this.applicationDetails = applicationDetails;
    }
    
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setApplicationStatus(final String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
    
    public String getSetstatusDescription() {
        return this.setstatusDescription;
    }
    
    public String getApplicationDetailsList() {
        return this.applicationDetailsList;
    }
    
    public String getApplicationDetails() {
        return this.applicationDetails;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public String getApplicationStatus() {
        return this.applicationStatus;
    }
    
    public void setSetstatusDescription(final String setstatusDescription) {
        this.setstatusDescription = setstatusDescription;
    }
    
    public void setApplicationDetailsCount(final String applicationDetailsCount) {
        this.applicationDetailsCount = applicationDetailsCount;
    }
    
    public String getApplicationDetailsCount() {
        return this.applicationDetailsCount;
    }
    
    public BPMApplicationAttributes[] getApplicationAttributes() {
        return this.ApplicationAttributes;
    }
    
    public void setApplicationAttributes(final BPMApplicationAttributes[] attrDetails) {
        this.ApplicationAttributes = attrDetails;
    }
}
