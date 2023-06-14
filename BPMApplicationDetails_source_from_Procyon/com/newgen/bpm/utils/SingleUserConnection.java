// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import com.newgen.omni.wf.util.xml.XMLParser;
import com.newgen.omni.wf.util.excp.NGException;
import com.newgen.bpm.logger.BPMApplicationLogger;

public class SingleUserConnection
{
    private static SingleUserConnection suc;
    private int iUserCount;
    private static long sessionUpdateTime;
    
    static {
        SingleUserConnection.sessionUpdateTime = System.currentTimeMillis();
    }
    
    private SingleUserConnection(final int iUserCount) {
        this.iUserCount = iUserCount;
    }
    
    public static synchronized SingleUserConnection getInstance(final int iUserCount) {
        if (SingleUserConnection.suc == null) {
            synchronized (SingleUserConnection.class) {
                if (SingleUserConnection.suc == null) {
                    SingleUserConnection.suc = new SingleUserConnection(iUserCount);
                }
            }
            // monitorexit(SingleUserConnection.class)
        }
        return SingleUserConnection.suc;
    }
    
    public synchronized String getSession(final String cabinetName, final String sUsername, final String sPassword) throws NGException {
        BPMApplicationLogger.logMe(1, "Inside getSession");
        String sessionId = "";
        String sessionIdNew = "";
        int isValid = 0;
        int mainCode = 0;
        final Object[] countCon = this.countAndGetCurrentConnection(sUsername);
        if ((int)countCon[0] > 0) {
            sessionId = (String)countCon[1];
            isValid = this.ValidateSession(sessionId);
            if (isValid == 0) {
                mainCode = this.updateConnection("USR_0_BPM_APPCONNECTION", "USERNAME, SESSIONID, DATETIME", "'" + sUsername + "', '" + sessionId + "' , SYSDATE", sessionId, isValid);
                if (mainCode != 0) {
                    return "-3";
                }
                return sessionId;
            }
            else {
                sessionIdNew = this.makeNewConnection(sUsername, sPassword);
                mainCode = this.deleteConnection("USR_0_BPM_APPCONNECTION", " SESSIONID = '" + sessionId + "'", sessionIdNew);
                if (mainCode != 0) {
                    return "-4";
                }
                sessionId = sessionIdNew;
            }
        }
        else {
            sessionId = this.makeNewConnection(sUsername, sPassword);
        }
        return sessionId;
    }
    
    private int ValidateSession(final String sessionId) throws NGException {
        BPMApplicationLogger.logMe(1, "Inside ValidateSession");
        final String sOutputXML = ProdCreateXML.IsSessionValid(sessionId);
        final XMLParser xp = new XMLParser(sOutputXML);
        final int mainCode = Integer.parseInt(xp.getValueOf("MainCode"));
        return mainCode;
    }
    
    private String makeNewConnection(final String sUsername, final String sPassword) throws NGException {
        BPMApplicationLogger.logMe(1, "Inside makeNewConnection");
        String sessionId = "-1";
        final String sOutputXML = ProdCreateXML.WMConnect(sUsername, sPassword);
        final XMLParser xp = new XMLParser(sOutputXML);
        int mainCode = Integer.parseInt(xp.getValueOf("MainCode"));
        if (mainCode == 0) {
            sessionId = xp.getValueOf("SessionId");
            mainCode = this.updateConnection("USR_0_BPM_APPCONNECTION", "USERNAME, SESSIONID, DATETIME", "'" + sUsername + "', '" + sessionId + "' , SYSDATE", sessionId, -1);
            if (mainCode != 0) {
                return "-2";
            }
            return sessionId;
        }
        else {
            if (mainCode == 1) {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException e) {
                    BPMApplicationLogger.logMe(1, "Inside catch InterruptedExceptione" + e.getMessage());
                }
                final Object[] countConn = this.countAndGetCurrentConnection(sUsername);
                sessionId = (String)countConn[1];
                return sessionId;
            }
            return sessionId;
        }
    }
    
    private Object[] countAndGetCurrentConnection(final String sUsername) throws NGException {
        BPMApplicationLogger.logMe(1, "Inside countAndGetCurrentConnection");
        final Object[] obj = new Object[2];
        final String outputXML = APCallCreateXML.APSelect("SELECT SESSIONID FROM USR_0_BPM_APPCONNECTION WHERE USERNAME = '" + sUsername + "'  order by DATETIME desc");
        final XMLParser xp = new XMLParser(outputXML);
        final int mainCode = Integer.parseInt(xp.getValueOf("MainCode"));
        if (mainCode == 0) {
            final int count = Integer.parseInt(xp.getValueOf("TotalRetrieved"));
            if (count > 0) {
                obj[0] = count;
                obj[1] = xp.getValueOf("SESSIONID");
            }
            else {
                obj[0] = -1;
                obj[1] = "-1";
            }
        }
        return obj;
    }
    
    private int updateConnection(final String tableName, final String columnNames, final String values, final String sessionId, final int isValid) throws NGException {
        BPMApplicationLogger.logMe(1, "Inside updateConnection");
        int mainCode = 0;
        if (isValid != 0) {
            final String outputXML = APCallCreateXML.APInsert(tableName, columnNames, values, sessionId);
            final XMLParser xp = new XMLParser(outputXML);
            mainCode = Integer.parseInt(xp.getValueOf("MainCode"));
        }
        try {
            final long prevTime = System.currentTimeMillis() - BPMApplicationConfigurations.getInstance().sessionInterval;
            BPMApplicationLogger.logMe(1, "updateConnection prevTime: " + prevTime);
            BPMApplicationLogger.logMe(1, "updateConnection sessionUpdateTime: " + SingleUserConnection.sessionUpdateTime);
            if (SingleUserConnection.sessionUpdateTime < prevTime) {
                SingleUserConnection.sessionUpdateTime = System.currentTimeMillis();
                APCallCreateXML.APProcedure(sessionId, "BPM_SESSION_UPDATE", "'" + sessionId + "'", 1);
            }
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, "exception in updateConnection BPM_SESSION_UPDATE");
            BPMApplicationLogger.logMe(2, e);
        }
        return mainCode;
    }
    
    private int deleteConnection(final String tableName, final String where, final String sessionId) throws NGException {
        BPMApplicationLogger.logMe(1, "Inside deleteConnection");
        final String outputXML = APCallCreateXML.APDelete(tableName, where, sessionId);
        final XMLParser xp = new XMLParser(outputXML);
        final int mainCode = Integer.parseInt(xp.getValueOf("MainCode"));
        return mainCode;
    }
}
