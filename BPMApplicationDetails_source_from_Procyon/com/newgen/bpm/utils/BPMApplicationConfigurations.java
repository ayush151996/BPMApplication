// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.newgen.omni.jts.cmgr.XMLParser;
import java.io.FileInputStream;
import com.newgen.bpm.logger.BPMApplicationLogger;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

public class BPMApplicationConfigurations
{
    public String CabinetName;
    public String JTSIP;
    public int JTSPort;
    public String ServerIP;
    public String socketIP;
    public String ServerPort;
    public String Server;
    public String UserName;
    public String Password;
    public int volumeID;
    public String ProTradeReceiptDoc;
    public String ProTradeCustomerDoc;
    public String docFolderName;
    public String moveDocFolderName;
    public String EDMS;
    public String trsdUrl;
    public int processDefIdAO;
    public int processDefIdTFO;
    private static BPMApplicationConfigurations instance;
    private Map<String, String> bpmServiceMap;
    public ConnectSocket socket;
    public long sessionInterval;
    public String SwiftDocName;
    public String InitiateFromActivityName;
    public String InitiateFromActivityId;
    public String SwiftDocFolderName;
    
    static {
        BPMApplicationConfigurations.instance = new BPMApplicationConfigurations();
    }
    
    private BPMApplicationConfigurations() {
        this.bpmServiceMap = new HashMap<String, String>();
        try {
            this.loadConfiguration();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static BPMApplicationConfigurations getInstance() {
        return BPMApplicationConfigurations.instance;
    }
    
    public void loadConfiguration() throws FileNotFoundException, IOException, Exception {
        final Properties p = new Properties();
        final StringBuilder configFile = new StringBuilder(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("WebServiceConf").append(System.getProperty("file.separator")).append("BPMModify").append(System.getProperty("file.separator")).append("LAPS_config.properties");
        BPMApplicationLogger.logMe(1, "loadConfiguration- configFile: " + (Object)configFile);
        final InputStream stream = new FileInputStream(configFile.toString());
        if (stream == null) {
            BPMApplicationLogger.logMe(2, "LAPS_config.properties not found");
        }
        p.load(stream);
        stream.close();
        this.CabinetName = p.getProperty("CabinetName");
        this.JTSIP = p.getProperty("sJtsIp");
        this.JTSPort = Integer.parseInt(p.getProperty("iJtsPort"));
        this.ServerIP = p.getProperty("IP");
        this.ServerPort = p.getProperty("Port");
        this.Server = p.getProperty("Server");
        this.UserName = p.getProperty("Username");
        this.Password = p.getProperty("Password");
        this.docFolderName = p.getProperty("docFolderName");
        this.moveDocFolderName = p.getProperty("moveDocFolderName");
        this.ProTradeReceiptDoc = p.getProperty("ProTradeReceiptDoc");
        this.ProTradeCustomerDoc = p.getProperty("ProTradeCustomerDoc");
        this.trsdUrl = p.getProperty("TRSDURL");
        this.socketIP = p.getProperty("socketIP");
        this.volumeID = Integer.parseInt(p.getProperty("volumeID"));
        this.processDefIdAO = Integer.parseInt(p.getProperty("ProcessDefIdAO"));
        this.processDefIdTFO = Integer.parseInt(p.getProperty("ProcessDefIdTFO"));
        BPMApplicationLogger.logMe(1, "loadConfiguration- configFile: ::" + this.JTSIP + "::" + this.JTSPort + "::" + this.volumeID + "::" + "END");
        this.SwiftDocName = p.getProperty("SwiftDocName");
        this.InitiateFromActivityName = p.getProperty("InitiateFromActivityName");
        this.InitiateFromActivityId = p.getProperty("InitiateFromActivityId");
        this.SwiftDocFolderName = p.getProperty("SwiftDocFolderName");
        this.EDMS = p.getProperty("EDMS");
        this.sessionInterval = Long.parseLong(p.getProperty("sessionInterval")) * 60L * 1000L;
        BPMApplicationLogger.logMe(1, "CabinetName: " + this.CabinetName + ",  ServerIP: " + this.ServerIP + ", ServerPort: " + this.ServerPort + ", UserName: " + this.UserName + ", ProcessDefIdAO: " + this.processDefIdAO + ", processDefIdTFO: " + this.processDefIdTFO);
        BPMApplicationLogger.logMe(1, "Configuration file loaded successfuly");
        ExecuteXML.init(this.Server, this.ServerIP, this.ServerPort);
        ProdCreateXML.init(this.CabinetName);
        APCallCreateXML.init(this.CabinetName);
        try {
            final String outputXML = APCallCreateXML.APSelect("SELECT VALUE FROM BPM_SERVICE_CONFIG WHERE KEY = 'SOCKETPORT'");
            BPMApplicationLogger.logMe(1, "outputxml is: " + outputXML);
            final XMLParser xp = new XMLParser(outputXML);
            final String port = xp.getValueOf("VALUE");
            BPMApplicationLogger.logMe(1, "value: " + port);
            BPMApplicationLogger.logMe(1, "socketIP: " + this.socketIP + ", socketPort: " + Integer.parseInt(port));
            this.socket = ConnectSocket.getReference(this.socketIP, Integer.parseInt(port));
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, "Error in ConnectSocket.initSocket: ");
            BPMApplicationLogger.logMe(2, e);
        }
    }
}
