// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import com.newgen.omni.wf.util.excp.NGException;
import com.newgen.bpm.logger.BPMApplicationLogger;
import com.newgen.omni.wf.util.app.NGEjbClient;

public class ExecuteXML
{
    private static NGEjbClient objNGEjbClient;
    
    static {
        ExecuteXML.objNGEjbClient = null;
    }
    
    public static void init(final String appName, final String IP, final String port) throws NGException {
        if (ExecuteXML.objNGEjbClient == null) {
            BPMApplicationLogger.logMe(1, "inside init ");
            (ExecuteXML.objNGEjbClient = NGEjbClient.getSharedInstance()).initialize(IP, port, appName);
        }
    }
    
    public static String executeXML(final String inputXML) throws NGException {
        String outputXML = "";
        try {
            BPMApplicationLogger.logMe(1, "executeXML inside ===>" + inputXML);
            outputXML = ExecuteXML.objNGEjbClient.makeCall(inputXML);
            BPMApplicationLogger.logMe(1, "OutputXML ===>" + outputXML);
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(1, "e ===>" + e);
            BPMApplicationLogger.logMe(2, e);
        }
        return outputXML;
    }
}
