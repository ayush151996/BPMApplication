// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import com.newgen.omni.wf.util.xml.XMLParser;
import com.newgen.bpm.logger.BPMApplicationLogger;
import com.newgen.omni.wf.util.excp.NGException;

public class ProdCreateXML
{
    private static String cabinetName;
    
    public static void init(final String cabName) {
        ProdCreateXML.cabinetName = cabName;
    }
    
    public static String WMConnect(final String sUsername, final String sPassword) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<WMConnect_Input>").append("\n").append("<Option>WMConnect</Option>").append("\n").append("<EngineName>" + ProdCreateXML.cabinetName + "</EngineName>").append("\n").append("<Name>" + sUsername + "</Name>").append("\n").append("<Password>" + sPassword + "</Password>").append("\n").append("<UserExist>Y</UserExist>").append("\n").append("</WMConnect_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
    
    public static String IsSessionValid(final String sessionId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<WFIsSessionValid_Input>").append("\n").append("<Option>WFIsSessionValid</Option>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<EngineName>" + ProdCreateXML.cabinetName + "</EngineName>").append("\n").append("</WFIsSessionValid_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
    
    public static String WMCompleteWorkItem(final String sessionId, final String ProcessInstanceId, final int WorkitemId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<WMCompleteWorkItem_Input>").append("\n").append("<Option>WMCompleteWorkItem</Option>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<EngineName>" + ProdCreateXML.cabinetName + "</EngineName>").append("\n").append("<ProcessInstanceId>" + ProcessInstanceId + "</ProcessInstanceId>").append("\n").append("<WorkitemId>" + WorkitemId + "</WorkitemId>").append("\n").append("</WMCompleteWorkItem_Input>");
        WMGetWorkItem(sessionId, ProcessInstanceId, WorkitemId);
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        BPMApplicationLogger.logMe(1, "WMCompleteWorkItem OutputXML ===>" + outputXML);
        return outputXML;
    }
    
    public static String WMGetWorkItem(final String sessionId, final String ProcessInstanceId, final int WorkitemId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<WMGetWorkItem_Input>").append("\n").append("<Option>WMGetWorkItem</Option>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<EngineName>" + ProdCreateXML.cabinetName + "</EngineName>").append("\n").append("<ProcessInstanceId>" + ProcessInstanceId + "</ProcessInstanceId>").append("\n").append("<WorkitemId>" + WorkitemId + "</WorkitemId>").append("\n").append("</WMGetWorkItem_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        BPMApplicationLogger.logMe(1, "WMGetWorkItem OutputXML ===>" + outputXML);
        final int mainCode = Integer.parseInt(new XMLParser(outputXML).getValueOf("MainCode"));
        if (mainCode == 16) {
            WMUnlockWorkItem(sessionId, ProcessInstanceId, WorkitemId);
        }
        return outputXML;
    }
    
    public static String WMUnlockWorkItem(final String sessionId, final String ProcessInstanceId, final int WorkitemId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<WMUnlockWorkItem_Input>").append("\n").append("<Option>WMUnlockWorkItem</Option>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<EngineName>" + ProdCreateXML.cabinetName + "</EngineName>").append("\n").append("<UnlockOption>W</UnlockOption>").append("\n").append("<ProcessInstanceId>" + ProcessInstanceId + "</ProcessInstanceId>").append("\n").append("<WorkitemId>" + WorkitemId + "</WorkitemId>").append("\n").append("</WMUnlockWorkItem_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
}
