// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.parser;

public class RequestXMLAttribute
{
    private String attributeName;
    private String attributeValue;
    
    public String getAttributeName() {
        return this.attributeName;
    }
    
    public void setAttributeName(final String attributeName) {
        this.attributeName = attributeName;
    }
    
    public String getAttributeValue() {
        return this.attributeValue;
    }
    
    public void setAttributeValue(final String attributeValue) {
        this.attributeValue = attributeValue;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
