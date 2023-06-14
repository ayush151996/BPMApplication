// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import com.newgen.omni.jts.cmgr.XMLParser;
import java.util.ArrayList;
import java.util.List;
import com.newgen.omni.wf.util.excp.NGException;
import com.newgen.bpm.logger.BPMApplicationLogger;

public class APCallCreateXML
{
    private static String cabinetName;
    
    public static void init(final String cabName) {
        BPMApplicationLogger.logMe(1, "cabName " + cabName);
        APCallCreateXML.cabinetName = cabName;
    }
    
    public static String APDelete(final String tableName, final String sWhere, final String sessionId) {
        final StringBuilder sInputXML = new StringBuilder();
        String outputXML = "";
        try {
            sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APDelete_Input>").append("\n").append("<Option>APDelete</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<TableName>" + tableName + "</TableName>").append("\n").append("<WhereClause>" + sWhere + "</WhereClause>").append("\n").append("</APDelete_Input>");
            outputXML = ExecuteXML.executeXML(sInputXML.toString());
        }
        catch (Exception ex) {}
        return outputXML;
    }
    
    public static String APInsert(final String tableName, final String columnName, final String strValues, final String sessionId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APInsert_Input>").append("\n").append("<Option>APInsert</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<TableName>" + tableName + "</TableName>").append("\n").append("<ColName>" + columnName + "</ColName>").append("\n").append("<Values>" + strValues + "</Values>").append("\n").append("</APInsert_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
    
    public static String APSelect(final String Query) throws NGException {
        String outputXML = "";
        try {
            final StringBuilder sInputXML = new StringBuilder();
            sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APSelectWithColumnNames_Input>").append("\n").append("<Option>APSelectWithColumnNames</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<QueryString>" + Query + "</QueryString>").append("\n").append("</APSelectWithColumnNames_Input>");
            BPMApplicationLogger.logMe(1, "sInputXML " + (Object)sInputXML);
            outputXML = ExecuteXML.executeXML(sInputXML.toString());
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(1, e);
            BPMApplicationLogger.logMe(2, e);
        }
        return outputXML;
    }
    
    public static String APUpdate(final String tableName, final String columnName, final String strValues, final String whereClause, final String sessionId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APUpdate_Input>").append("\n").append("<Option>APUpdate</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<TableName>" + tableName + "</TableName>").append("\n").append("<ColName>" + columnName + "</ColName>").append("\n").append("<Values>" + strValues + "</Values>").append("\n").append("<WhereClause>" + whereClause + "</WhereClause>").append("\n").append("</APUpdate_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
    
    public static String APTemplate(final String wiName, final String sessionId) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APTemplate_Input>").append("\n").append("<Option>APTemplate</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<Query>" + wiName + "</Query>").append("\n").append("</APTemplate_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
    
    public static String APProcedure(final String sessionId, final String ProcName, final String InValues, final int NoOfCols) throws NGException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APProcedure_Input>").append("\n").append("<Option>APProcedure</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<ProcName>" + ProcName + "</ProcName>").append("\n").append("<Params>" + InValues + "</Params>").append("\n").append("<NoOfCols>" + NoOfCols + "</NoOfCols>").append("\n").append("</APProcedure_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        return outputXML;
    }
    
    public static String Webservice(final String sessionId) throws NGException, InterruptedException {
        final StringBuilder sInputXML = new StringBuilder();
        sInputXML.append("<?xml version=\"1.0\"?>").append("\n").append("<APWebService_Input>").append("\n").append("<Option>WebService</Option>").append("\n").append("<EngineName>" + APCallCreateXML.cabinetName + "</EngineName>").append("\n").append("<SessionId>" + sessionId + "</SessionId>").append("\n").append("<Calltype>CustEIDAInfo</Calltype>").append("\n").append("<EIDANum>784199173063076</EIDANum>").append("\n").append("<REF_No>9999</REF_No>").append("\n").append("</APWebService_Input>");
        final String outputXML = ExecuteXML.executeXML(sInputXML.toString());
        System.out.println(outputXML);
        return outputXML;
    }
    
    public static List getDataFromDB(final String columns, final String tableName, final String whereClause, final String tagName) {
        final List<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        String query = "select " + columns + " from " + tableName;
        if (!"".equals(whereClause) && whereClause != null) {
            query = String.valueOf(query) + " where " + whereClause;
        }
        BPMApplicationLogger.logMe(1, "query :" + query);
        try {
            final String outputXML = APSelect(query);
            BPMApplicationLogger.logMe(1, "outputXML :" + outputXML);
            final XMLParser xp = new XMLParser(outputXML);
            final int mainCode = Integer.parseInt(xp.getValueOf("MainCode"));
            BPMApplicationLogger.logMe(1, "mainCode :" + mainCode);
            final String[] tagNames = tagName.split(",");
            BPMApplicationLogger.logMe(1, "after split :" + tagNames.length);
            if (mainCode == 0) {
                int start = xp.getStartIndex("Records", 0, 0);
                final int deadEnd = xp.getEndIndex("Records", start, 0);
                final int noOfFields = xp.getNoOfFields("Record", start, deadEnd);
                int end = 0;
                for (int i = 0; i < noOfFields; ++i) {
                    start = xp.getStartIndex("Record", end, 0);
                    end = xp.getEndIndex("Record", start, 0);
                    final ArrayList<String> outputInner = new ArrayList<String>();
                    for (int j = 0; j < tagNames.length; ++j) {
                        final String tagValue = xp.getValueOf(tagNames[j].trim(), start, end);
                        BPMApplicationLogger.logMe(1, "tagValue :" + tagValue);
                        outputInner.add(tagValue);
                        BPMApplicationLogger.logMe(1, "outputInner :" + outputInner);
                    }
                    output.add(i, outputInner);
                    BPMApplicationLogger.logMe(1, "output :" + output);
                }
            }
            BPMApplicationLogger.logMe(1, "output GETDATAFROMDB :" + output);
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, "error in getDataFromDB: ");
            BPMApplicationLogger.logMe(2, e);
        }
        return output;
    }
}
