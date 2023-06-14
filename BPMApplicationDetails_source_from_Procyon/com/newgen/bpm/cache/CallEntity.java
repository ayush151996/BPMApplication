
package com.newgen.bpm.cache;

import java.util.ArrayList;

public class CallEntity
{
    private String stage;
    private int callID;
    private String callName;
    private boolean isMandatory;
    private boolean isIgnorable;
    private String productType;
    private String executionType;
    private int executionSequence;
    private ArrayList<Integer> dependencyCallID;
    
    public String getProductType() {
        return this.productType;
    }
    
    public void setProductType(final String productType) {
        this.productType = productType;
    }
    
    public boolean isIgnorable() {
        return this.isIgnorable;
    }
    
    public void setIgnorable(final boolean isIgnorable) {
        this.isIgnorable = isIgnorable;
    }
    
    public String getStage() {
        return this.stage;
    }
    
    public void setStage(final String stage) {
        this.stage = stage;
    }
    
    public int getCallID() {
        return this.callID;
    }
    
    public void setCallID(final int callID) {
        this.callID = callID;
    }
    
    public String getCallName() {
        return this.callName;
    }
    
    public void setCallName(final String callName) {
        this.callName = callName;
    }
    
    public boolean isMandatory() {
        return this.isMandatory;
    }
    
    public void setMandatory(final boolean isMandatory) {
        this.isMandatory = isMandatory;
    }
    
    public String getExecutionType() {
        return this.executionType;
    }
    
    public void setExecutionType(final String executionType) {
        this.executionType = executionType;
    }
    
    public int getExecutionSequence() {
        return this.executionSequence;
    }
    
    public void setExecutionSequence(final int executionSequence) {
        this.executionSequence = executionSequence;
    }
    
    public ArrayList<Integer> getDependencyCallID() {
        return this.dependencyCallID;
    }
    
    public void setDependencyCallID(final ArrayList<Integer> dependencyCallID) {
        this.dependencyCallID = dependencyCallID;
    }
}
