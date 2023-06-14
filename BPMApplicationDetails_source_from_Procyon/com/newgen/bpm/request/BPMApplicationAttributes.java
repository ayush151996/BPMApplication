// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.request;

public class BPMApplicationAttributes
{
    private BPMAttributes[] BPMApplicationDetails;
    
    public BPMAttributes[] getBPMAttributes() {
        return this.BPMApplicationDetails;
    }
    
    public void setBPMAttributes(final BPMAttributes[] bpmAttributes) {
        this.BPMApplicationDetails = bpmAttributes;
    }
}
