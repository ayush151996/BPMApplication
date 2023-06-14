// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.service;

import javax.jws.WebResult;
import javax.jws.WebMethod;
import com.newgen.bpm.request.BPMApplicationAttributes;
import com.newgen.bpm.request.BPMAttributes;
import com.newgen.bpm.utils.XMLParser;
import com.newgen.bpm.utils.APCallCreateXML;
import com.newgen.bpm.logger.BPMApplicationLogger;
import com.newgen.bpm.utils.BPMApplicationUtils;
import com.newgen.bpm.utils.SingleUserConnection;
import com.newgen.bpm.utils.BPMApplicationConfigurations;
import com.newgen.bpm.request.ApplicationAttributes;
import com.newgen.bpm.cache.CallEntity;
import java.util.ArrayList;
import java.util.List;
import com.newgen.bpm.response.BPMApplicationDetailsResponse;
import com.newgen.bpm.request.BPMApplicationDetailsRequest;
import javax.jws.WebService;

@WebService(serviceName = "BPMApplicationProcessStatus")
public class BPMApplicationProcessStatus
{
    private BPMApplicationDetailsRequest request;
    private BPMApplicationDetailsResponse response;
    List<BPMApplicationDetailsResponse> resp;
    private String CID;
    private String ApplNo;
    private String mobileNumber;
    private String emailID;
    public String passportNumber;
    public String EIDANumber;
    public String fullName;
    private String refNo;
    public ArrayList<CallEntity> callMap;
    private static String sessionid;
    private ApplicationAttributes[] ApplicationAttributes;
    
    public BPMApplicationProcessStatus() {
        this.response = new BPMApplicationDetailsResponse();
        this.resp = new ArrayList<BPMApplicationDetailsResponse>();
        this.CID = "";
        this.ApplNo = "";
        this.mobileNumber = "";
        this.emailID = "";
        this.passportNumber = "";
        this.EIDANumber = "";
        this.fullName = "";
    }
    
    @WebMethod(operationName = "BPMApplicationProcessStatus")
    @WebResult(name = "BPMApplicationDetailProcessStatusResponse")
    public BPMApplicationDetailsResponse BPMApplicationDetailProcessStatus(final BPMApplicationDetailsRequest request) {
        Label_3991: {
            try {
                BPMApplicationConfigurations.getInstance().loadConfiguration();
                final SingleUserConnection instance = SingleUserConnection.getInstance(1000);
                BPMApplicationProcessStatus.sessionid = instance.getSession(BPMApplicationConfigurations.getInstance().CabinetName, BPMApplicationConfigurations.getInstance().UserName, BPMApplicationConfigurations.getInstance().Password);
                this.refNo = BPMApplicationUtils.getInstance().generateSysRefNumber();
                this.CID = request.getCID();
                this.ApplNo = request.getApplNo();
                this.mobileNumber = request.getMobileNumber();
                this.emailID = request.getEmailID();
                this.passportNumber = request.getPassportNumber();
                this.EIDANumber = request.getEIDANumber();
                this.fullName = request.getFullName();
                BPMApplicationLogger.logMe(1, ", CID is: " + this.CID);
                BPMApplicationLogger.logMe(1, " , ApplNo is: " + this.ApplNo);
                BPMApplicationLogger.logMe(1, " , mobileNumber is: " + this.mobileNumber);
                BPMApplicationLogger.logMe(1, ", emailID is: " + this.emailID);
                BPMApplicationLogger.logMe(1, ", passportNumber is: " + this.passportNumber);
                BPMApplicationLogger.logMe(1, ", EIDANumber is: " + this.EIDANumber);
                BPMApplicationLogger.logMe(1, ", fullName is: " + this.fullName);
                BPMApplicationLogger.logMe(1, "request is: " + request);
                String wi_name = "";
                StringBuffer strwhereclauseCBG = new StringBuffer();
                StringBuffer strwhereAO = new StringBuffer();
                StringBuffer tabledataAO = new StringBuffer();
                tabledataAO.append(',');
                if (!this.mobileNumber.isEmpty() && !this.mobileNumber.equalsIgnoreCase("?")) {
                    final String aoapplquery = "select wi_name from acc_relation_repeater where mobile = '" + this.mobileNumber + "'";
                    String sApplCBGmob = " select WI_NAME from EXT_CBG_CUST_ONBOARDING where CUSTOMER_MOBILE_NO = '" + this.mobileNumber + "'";
                    final String unionquery = String.valueOf(aoapplquery) + "UNION" + sApplCBGmob;
                    final String aoapplOutput = APCallCreateXML.APSelect(unionquery);
                    final XMLParser xpaoappl = new XMLParser(aoapplOutput);
                    BPMApplicationLogger.logMe(1, "xpaoappl - AO - Ayush mobile change unionquery" + unionquery);
                    BPMApplicationLogger.logMe(1, "xpaoappl - AO - Ayush mobile change" + xpaoappl);
                    this.ApplNo = xpaoappl.getValueOf("wi_name");
                    BPMApplicationLogger.logMe(1, "ApplNo - AO - Ayush mobile change" + this.ApplNo);
                    final int TotalRetrieved = Integer.parseInt(xpaoappl.getValueOf("TotalRetrieved"));
                    if (TotalRetrieved == 0) {
                        sApplCBGmob = " select WI_NAME from EXT_CBG_CUST_ONBOARDING where CUSTOMER_MOBILE_NO = '" + this.mobileNumber + "'";
                        final String outputXMLCBGappl = APCallCreateXML.APSelect(sApplCBGmob);
                        BPMApplicationLogger.logMe(1, "outputXMLCBGappl - CBG- Ayush mobile change: " + outputXMLCBGappl);
                        final XMLParser xpCBGAppl = new XMLParser(outputXMLCBGappl);
                        this.ApplNo = xpCBGAppl.getValueOf("WI_NAME");
                        BPMApplicationLogger.logMe(1, "ApplNo - CBG- Ayush mobile change: " + this.ApplNo);
                    }
                    BPMApplicationLogger.logMe(1, "ApplNo retrived - Ayush mobile change: " + this.ApplNo);
                    if (this.ApplNo.startsWith("DJ")) {
                        if (!this.CID.isEmpty()) {
                            strwhereclauseCBG.append("customer_id = '" + this.CID + "'").append(" and ");
                        }
                        if (!this.emailID.isEmpty()) {
                            strwhereclauseCBG.append("CUSTOMER_EMAIL = '" + this.emailID + "'").append(" and ");
                        }
                        if (!this.passportNumber.isEmpty()) {
                            strwhereclauseCBG.append("PASSPORT_NUMBER = '" + this.passportNumber + "'").append(" and ");
                        }
                        if (!this.EIDANumber.isEmpty()) {
                            strwhereclauseCBG.append("EIDA_NUMBER = '" + this.EIDANumber + "'").append(" and ");
                        }
                        if (!this.fullName.isEmpty()) {
                            strwhereclauseCBG.append("customer_full_name = '" + this.fullName + "'").append(" and ");
                        }
                        if (this.CID.isEmpty() && this.emailID.isEmpty() && this.passportNumber.isEmpty() && this.EIDANumber.isEmpty() && this.fullName.isEmpty()) {
                            strwhereclauseCBG = null;
                        }
                        BPMApplicationLogger.logMe(1, "strwhereclauseCBG - CBG - Ayush other conditions" + (Object)strwhereclauseCBG);
                    }
                    if (this.ApplNo.startsWith("AO")) {
                        int countAO = 0;
                        int countAOusr = 0;
                        if (!this.CID.isEmpty()) {
                            strwhereAO.append("E.CUST_ID = '" + this.CID + "'").append(" and ");
                            if (tabledataAO.indexOf("ext_ao") == 0 || countAO == 0) {
                                tabledataAO.append("ext_ao E ");
                                BPMApplicationLogger.logMe(1, "tabledataAO - Ayush Inside CID Table entry");
                                ++countAO;
                            }
                        }
                        if (!this.emailID.isEmpty()) {
                            strwhereAO.append("E.CUST_EMAIL = '" + this.emailID + "'").append(" and ");
                            if (tabledataAO.indexOf("ext_ao") == 0 || countAO == 0) {
                                BPMApplicationLogger.logMe(1, "tabledataAO - Ayush Inside EMail Table entry");
                                tabledataAO.append("ext_ao E ");
                                ++countAO;
                            }
                        }
                        if (!this.passportNumber.isEmpty()) {
                            strwhereAO.append("D.manual_pass_no = '" + this.passportNumber + "'").append(" and ");
                            if (tabledataAO.indexOf("usr_0_cust_txn") == 0 || countAOusr == 0) {
                                BPMApplicationLogger.logMe(1, "tabledataAO - Ayush Inside passportNumber Table entry");
                                tabledataAO.append("usr_0_cust_txn D");
                                ++countAOusr;
                            }
                        }
                        if (!this.EIDANumber.isEmpty()) {
                            strwhereAO.append("B.EIDA = '" + this.EIDANumber + "'").append(" and ");
                        }
                        if (!this.fullName.isEmpty()) {
                            strwhereAO.append("E.CUST_FULL_NAME = '" + this.fullName + "'").append(" and ");
                            if (tabledataAO.indexOf("ext_ao") == 0 || countAO == 0) {
                                BPMApplicationLogger.logMe(1, "tabledataAO - Ayush Inside fullName Table entry");
                                tabledataAO.append("ext_ao E ");
                                ++countAO;
                            }
                        }
                        if (this.CID.isEmpty() && this.emailID.isEmpty() && this.passportNumber.isEmpty() && this.EIDANumber.isEmpty() && this.fullName.isEmpty()) {
                            BPMApplicationLogger.logMe(1, "tabledataAO - Ayush Inside all fields empty");
                            strwhereAO = null;
                            tabledataAO = null;
                        }
                        countAOusr = 0;
                        countAO = 0;
                        if (tabledataAO != null && tabledataAO.indexOf("ext_ao") != 0) {
                            BPMApplicationLogger.logMe(1, "tabledataAO - Ayush If ext_ao already available.");
                            strwhereAO.append("A.WI_NAME  = E.WI_NAME ").append(" and ");
                        }
                        BPMApplicationLogger.logMe(1, "strwhereAO - AO - Ayush where other conditions: " + (Object)strwhereAO);
                        BPMApplicationLogger.logMe(1, "tabledataAO - AO - Ayush other table conditions: " + (Object)tabledataAO);
                        BPMApplicationLogger.logMe(1, "mobileNumber - Ayush common:" + this.mobileNumber);
                        BPMApplicationLogger.logMe(1, "ApplNo(if available):" + this.ApplNo);
                        final String query1 = "select A.WI_NAME as wiName from usr_0_product_selected A,acc_relation_repeater B where B.WI_NAME=A.WI_NAME AND B.MOBILE = '" + this.mobileNumber + "'";
                        final String sOutput = APCallCreateXML.APSelect(query1);
                        final XMLParser xp1 = new XMLParser(sOutput);
                        final int mainCode2 = Integer.parseInt(xp1.getValueOf("MainCode"));
                        BPMApplicationLogger.logMe(1, "mainCode1" + mainCode2);
                        if (mainCode2 == 0 && Integer.parseInt(xp1.getValueOf("TotalRetrieved")) > 0) {
                            wi_name = xp1.getValueOf("wiName");
                        }
                    }
                    else if (this.ApplNo.startsWith("DJ")) {
                        final String query2 = "select WI_NAME from EXT_CBG_CUST_ONBOARDING where customer_mobile_no  = '" + this.mobileNumber + "'";
                        final String sOutput2 = APCallCreateXML.APSelect(query2);
                        BPMApplicationLogger.logMe(1, "sOutput1 - CBG" + sOutput2);
                        final XMLParser xp2 = new XMLParser(sOutput2);
                        final int mainCode3 = Integer.parseInt(xp2.getValueOf("MainCode"));
                        BPMApplicationLogger.logMe(1, "mainCode1:" + mainCode3);
                        if (mainCode3 == 0 && Integer.parseInt(xp2.getValueOf("TotalRetrieved")) > 0) {
                            wi_name = xp2.getValueOf("WI_NAME");
                        }
                    }
                    if (wi_name.startsWith("AO")) {
                        BPMApplicationLogger.logMe(1, "Ayush change - wi_name" + wi_name);
                        BPMApplicationLogger.logMe(1, "Ayush change - Inside AO");
                        String query3 = "";
                        if (strwhereAO == null) {
                            query3 = "select COUNT(1) as workitemCount from usr_0_product_selected A,acc_relation_repeater B where  B.WI_NAME=A.WI_NAME AND B.MOBILE = '" + this.mobileNumber + "'";
                        }
                        else {
                            query3 = "select COUNT(1) as workitemCount from usr_0_product_selected A,acc_relation_repeater B " + (Object)tabledataAO + " " + "where " + (Object)strwhereAO + " B.WI_NAME=A.WI_NAME AND B.MOBILE = '" + this.mobileNumber + "'";
                        }
                        final String outputXML = APCallCreateXML.APSelect(query3);
                        int totalCount = 0;
                        final XMLParser xp3 = new XMLParser(outputXML);
                        BPMAttributes[] bpmAttributes = null;
                        final int mainCode4 = Integer.parseInt(xp3.getValueOf("MainCode"));
                        String sTotal = "";
                        if (mainCode4 == 0) {
                            totalCount = Integer.parseInt(xp3.getValueOf("workitemCount"));
                            sTotal = xp3.getValueOf("workitemCount");
                            if (totalCount > 0) {
                                String sQuery = "";
                                String sQueryInput = "select CUST_ID,CUST_EMAIL,CUST_FULL_NAME from ext_ao;";
                                sQueryInput = "";
                                if (strwhereAO == null) {
                                    sQuery = "select A.WI_NAME,A.ACC_OPEN_DT AS ACC_OPEN_DT,A.PROD_DESC AS PROD_DESC,A.ACC_NO AS ACC_NO,A.CURRENCY AS CURRENCY,A.ACC_STATUS AS ACC_STATUS from usr_0_product_selected A,acc_relation_repeater B  where B.WI_NAME=A.WI_NAME AND B.MOBILE =  '" + this.mobileNumber + "'";
                                }
                                else {
                                    sQuery = "select A.WI_NAME,A.ACC_OPEN_DT AS ACC_OPEN_DT,A.PROD_DESC AS PROD_DESC,A.ACC_NO AS ACC_NO,A.CURRENCY AS CURRENCY,A.ACC_STATUS AS ACC_STATUS from usr_0_product_selected A,acc_relation_repeater B " + (Object)tabledataAO + " where " + (Object)strwhereAO + " B.WI_NAME=A.WI_NAME AND B.MOBILE =  '" + this.mobileNumber + "'";
                                }
                                BPMApplicationLogger.logMe(1, "sOutputXML" + sQuery);
                                final String sOutputXML = APCallCreateXML.APSelect(sQuery);
                                final XMLParser xmlParser = new XMLParser(sOutputXML);
                                final int sMainCodeAO = Integer.parseInt(xmlParser.getValueOf("MainCode"));
                                BPMApplicationLogger.logMe(1, "sMainCode" + sMainCodeAO);
                                String accOpenDate = "";
                                String prodDesc = "";
                                String winame = "";
                                String currency = "";
                                String accNo = "";
                                String accStatus = "";
                                if (sMainCodeAO == 0) {
                                    bpmAttributes = new BPMAttributes[totalCount];
                                    final int sCount = Integer.parseInt(xmlParser.getValueOf("TotalRetrieved"));
                                    BPMApplicationLogger.logMe(1, "sCount" + sCount);
                                    if (sCount > 0) {
                                        int start = xmlParser.getStartIndex("Records", 0, 0);
                                        final int deadEnd = xmlParser.getEndIndex("Records", start, 0);
                                        int end = 0;
                                        for (int i = 0; i < sCount; ++i) {
                                            start = xmlParser.getStartIndex("Record", end, 0);
                                            end = xmlParser.getEndIndex("Record", start, 0);
                                            winame = xmlParser.getValueOf("WI_NAME", start, end);
                                            accOpenDate = xmlParser.getValueOf("ACC_OPEN_DT", start, end);
                                            prodDesc = xmlParser.getValueOf("PROD_DESC", start, end);
                                            currency = xmlParser.getValueOf("CURRENCY", start, end);
                                            accNo = xmlParser.getValueOf("ACC_NO", start, end);
                                            accStatus = xmlParser.getValueOf("ACC_STATUS", start, end);
                                            BPMApplicationLogger.logMe(1, "currency" + currency);
                                            final String workitemname = xmlParser.getValueOf("WI_NAME", start, end);
                                            BPMApplicationLogger.logMe(1, "Ayush-New Change AO");
                                            final String applstatusquery = "select CUST_ID,CUST_EMAIL,CUST_FULL_NAME,curr_ws_name from ext_ao where wi_name = '" + workitemname + "'";
                                            final String passportquery = "select CUST_PASS_NO from usr_0_ao_search_audit where wi_name = '" + workitemname + "'";
                                            final String sOutputpassstatusXML = APCallCreateXML.APSelect(passportquery);
                                            final XMLParser xmlparseapppass = new XMLParser(sOutputpassstatusXML);
                                            final String passstatus = xmlparseapppass.getValueOf("CUST_PASS_NO", start, end);
                                            BPMApplicationLogger.logMe(1, "applstatusquery - Ayush" + applstatusquery);
                                            final String sOutputapplstatusXML = APCallCreateXML.APSelect(applstatusquery);
                                            final XMLParser xmlparseappl = new XMLParser(sOutputapplstatusXML);
                                            BPMApplicationLogger.logMe(1, "xmlparseappl - Ayush" + xmlparseappl);
                                            String applstatus = xmlparseappl.getValueOf("curr_ws_name", start, end);
                                            final String cust_id = xmlparseappl.getValueOf("CUST_ID", start, end);
                                            final String cust_email = xmlparseappl.getValueOf("CUST_EMAIL", start, end);
                                            final String cust_fullname = xmlparseappl.getValueOf("CUST_FULL_NAME", start, end);
                                            if (applstatus.equalsIgnoreCase("Work Exit1")) {
                                                applstatus = "Completed";
                                            }
                                            else if (applstatus.equalsIgnoreCase("Cancelled")) {
                                                applstatus = "Cancelled";
                                            }
                                            else if (!applstatus.equalsIgnoreCase("Close") && !applstatus.equalsIgnoreCase("Work Exit1")) {
                                                applstatus = "In Progress";
                                            }
                                            final BPMAttributes attr = new BPMAttributes();
                                            attr.setApplicationStatus(applstatus);
                                            attr.setProductName(prodDesc);
                                            attr.setCurrency(currency);
                                            attr.setAccountNumber(accNo);
                                            attr.setInitiationDate(accOpenDate);
                                            BPMApplicationLogger.logMe(1, "Ayush-New Change set AO");
                                            attr.setCID(cust_id);
                                            attr.setApplNo(workitemname);
                                            attr.setMobileNumber(this.mobileNumber);
                                            attr.setPassportNumber(passstatus);
                                            attr.setFullName(cust_fullname);
                                            attr.setEmailID(cust_email);
                                            bpmAttributes[i] = attr;
                                        }
                                    }
                                }
                            }
                            final BPMApplicationAttributes[] attrDetails = { null };
                            final BPMApplicationAttributes bulkattr = new BPMApplicationAttributes();
                            bulkattr.setBPMAttributes(bpmAttributes);
                            attrDetails[0] = bulkattr;
                            this.response.setApplicationAttributes(attrDetails);
                            this.response.setApplicationDetailsCount(sTotal);
                        }
                    }
                    else if (wi_name.startsWith("DJ")) {
                        BPMApplicationLogger.logMe(1, "Ayush change - wi_name" + wi_name);
                        BPMApplicationLogger.logMe(1, "Ayush change - Inside CBG");
                        String cbgquery = "";
                        if (strwhereclauseCBG == null) {
                            cbgquery = "select COUNT(1) as workitemCount from EXT_CBG_CUST_ONBOARDING where customer_mobile_no  = '" + this.mobileNumber + "'";
                        }
                        else {
                            cbgquery = "select COUNT(1) as workitemCount from EXT_CBG_CUST_ONBOARDING where " + (Object)strwhereclauseCBG + " customer_mobile_no  = '" + this.mobileNumber + "'";
                        }
                        BPMApplicationLogger.logMe(1, "Ayush change - cbgquery" + cbgquery);
                        final String outputXML = APCallCreateXML.APSelect(cbgquery);
                        int totalCount = 0;
                        final XMLParser xp3 = new XMLParser(outputXML);
                        BPMAttributes[] bpmAttributes = null;
                        final int mainCode4 = Integer.parseInt(xp3.getValueOf("MainCode"));
                        String sTotal = "";
                        if (mainCode4 == 0) {
                            totalCount = Integer.parseInt(xp3.getValueOf("workitemCount"));
                            sTotal = xp3.getValueOf("workitemCount");
                            if (totalCount > 0) {
                                String sQueryCBG = "";
                                if (strwhereclauseCBG == null) {
                                    sQueryCBG = "SELECT WI_NAME as workitem_number, initiation_datetime as INIT_DATE_TIME,product_desc AS Product_Description,account_number AS accountNumber,account_currency AS currency, application_dedupe as APPLSTATUS FROM EXT_CBG_CUST_ONBOARDING  where customer_mobile_no  =  '" + this.mobileNumber + "'";
                                }
                                else {
                                    sQueryCBG = "SELECT WI_NAME as workitem_number, initiation_datetime as INIT_DATE_TIME,product_desc AS Product_Description,account_number AS accountNumber,account_currency AS currency, application_dedupe as APPLSTATUS FROM EXT_CBG_CUST_ONBOARDING  where " + (Object)strwhereclauseCBG + " customer_mobile_no  =  '" + this.mobileNumber + "'";
                                }
                                BPMApplicationLogger.logMe(1, "sOutputXML - Ayush(CBG Upgrade)" + sQueryCBG);
                                final String sOutputXML2 = APCallCreateXML.APSelect(sQueryCBG);
                                final XMLParser xmlParser2 = new XMLParser(sOutputXML2);
                                final int sMainCodeCBG = Integer.parseInt(xmlParser2.getValueOf("MainCode"));
                                BPMApplicationLogger.logMe(1, "sMainCodeCBG" + sMainCodeCBG);
                                String accOpenDate2 = "";
                                String prodDesc2 = "";
                                String currency2 = "";
                                String accNo2 = "";
                                final String accStatus2 = "";
                                if (sMainCodeCBG == 0) {
                                    bpmAttributes = new BPMAttributes[totalCount];
                                    final int sCount2 = Integer.parseInt(xmlParser2.getValueOf("TotalRetrieved"));
                                    BPMApplicationLogger.logMe(1, "sCount" + sCount2);
                                    if (sCount2 > 0) {
                                        int start2 = xmlParser2.getStartIndex("Records", 0, 0);
                                        final int deadEnd2 = xmlParser2.getEndIndex("Records", start2, 0);
                                        int end2 = 0;
                                        for (int j = 0; j < sCount2; ++j) {
                                            start2 = xmlParser2.getStartIndex("Record", end2, 0);
                                            end2 = xmlParser2.getEndIndex("Record", start2, 0);
                                            accOpenDate2 = xmlParser2.getValueOf("INIT_DATE_TIME", start2, end2);
                                            prodDesc2 = xmlParser2.getValueOf("Product_Description", start2, end2);
                                            currency2 = xmlParser2.getValueOf("currency", start2, end2);
                                            accNo2 = xmlParser2.getValueOf("accountNumber", start2, end2);
                                            BPMApplicationLogger.logMe(1, "currency" + currency2);
                                            final String workitemname = xmlParser2.getValueOf("WORKITEM_NUMBER", start2, end2);
                                            final String applstatusquery = "select customer_id,CUSTOMER_EMAIL,PASSPORT_NUMBER,EIDA_NUMBER,customer_full_name,stage_name from EXT_CBG_CUST_ONBOARDING where '" + (Object)strwhereclauseCBG + "' wi_name = '" + workitemname + "'";
                                            BPMApplicationLogger.logMe(1, "applstatusquery - Ayush" + applstatusquery);
                                            final String sOutputapplstatusXML2 = APCallCreateXML.APSelect(applstatusquery);
                                            BPMApplicationLogger.logMe(1, "sOutputapplstatusXML - Ayush" + sOutputapplstatusXML2);
                                            final XMLParser xmlparseappl2 = new XMLParser(sOutputapplstatusXML2);
                                            String applstatus2 = xmlparseappl2.getValueOf("curr_ws_name", start2, end2);
                                            BPMApplicationLogger.logMe(1, "Ayush-New Change CBG");
                                            final String cust_idcbg = xmlparseappl2.getValueOf("customer_id", start2, end2);
                                            final String cust_emailcbg = xmlparseappl2.getValueOf("CUSTOMER_EMAIL", start2, end2);
                                            final String cust_passcbg = xmlparseappl2.getValueOf("PASSPORT_NUMBER", start2, end2);
                                            final String cust_eida = xmlparseappl2.getValueOf("EIDA_NUMBER", start2, end2);
                                            final String cust_full = xmlparseappl2.getValueOf("customer_full_name", start2, end2);
                                            if (applstatus2.equalsIgnoreCase("Exit")) {
                                                applstatus2 = "Completed";
                                            }
                                            else if (applstatus2.equalsIgnoreCase("Discard1")) {
                                                applstatus2 = "Cancelled";
                                            }
                                            else if (!applstatus2.equalsIgnoreCase("Discard1") && !applstatus2.equalsIgnoreCase("Exit")) {
                                                applstatus2 = "In Progress";
                                            }
                                            final BPMAttributes attr2 = new BPMAttributes();
                                            attr2.setApplicationStatus(applstatus2);
                                            attr2.setProductName(prodDesc2);
                                            attr2.setCurrency(currency2);
                                            attr2.setAccountNumber(accNo2);
                                            attr2.setInitiationDate(accOpenDate2);
                                            bpmAttributes[j] = attr2;
                                            BPMApplicationLogger.logMe(1, "Ayush-New Change set");
                                            attr2.setCID(cust_idcbg);
                                            attr2.setApplNo(workitemname);
                                            attr2.setMobileNumber(this.mobileNumber);
                                            attr2.setPassportNumber(cust_passcbg);
                                            attr2.setFullName(cust_full);
                                            attr2.setEmailID(cust_emailcbg);
                                            attr2.setEIDANumber(cust_eida);
                                            bpmAttributes[j] = attr2;
                                        }
                                    }
                                }
                                final BPMApplicationAttributes[] attrDetails2 = { null };
                                final BPMApplicationAttributes bulkattr2 = new BPMApplicationAttributes();
                                bulkattr2.setBPMAttributes(bpmAttributes);
                                attrDetails2[0] = bulkattr2;
                                this.response.setApplicationAttributes(attrDetails2);
                                this.response.setApplicationDetailsCount(sTotal);
                            }
                        }
                    }
                }
                else {
                    BPMApplicationLogger.logMe(1, "Ayush change - Inside Mobile Number Not Found");
                    this.response.setSetstatusDescription("Mobile Number Cannot be blank");
                }
            }
            catch (Exception e) {
                BPMApplicationLogger.logMe(1, e);
                break Label_3991;
            }
            finally {
                BPMApplicationLogger.logMe(1, "data - Ayush " + this.response);
            }
            BPMApplicationLogger.logMe(1, "data - Ayush " + this.response);
        }
        BPMApplicationLogger.logMe(1, "data - Ayush " + this.response);
        BPMApplicationLogger.logMe(1, "data - Ayush " + this.response.toString());
        return this.response;
    }
}
