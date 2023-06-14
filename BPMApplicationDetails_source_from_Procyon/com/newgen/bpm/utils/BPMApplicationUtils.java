// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import com.newgen.omni.jts.cmgr.XMLParser;
import com.newgen.bpm.request.Attributes;
import com.newgen.bpm.request.AttributeDetails;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.newgen.bpm.request.ApplicationAttributes;
import com.newgen.bpm.logger.BPMApplicationLogger;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BPMApplicationUtils
{
    private static BPMApplicationUtils instance;
    
    static {
        BPMApplicationUtils.instance = null;
    }
    
    private BPMApplicationUtils() {
    }
    
    public static BPMApplicationUtils getInstance() {
        if (BPMApplicationUtils.instance == null) {
            BPMApplicationUtils.instance = new BPMApplicationUtils();
        }
        return BPMApplicationUtils.instance;
    }
    
    public String formatToBPMDate(String dt) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            final Date date = sdf.parse(dt);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            dt = sdf.format(date);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }
    
    public String formatToBPMDateTime(String dt) {
        BPMApplicationLogger.logMe(1, "BPMTODATETIME: " + dt);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            final Date date = sdf.parse(dt);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt = sdf.format(date);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }
    
    public HashMap<String, String> requestToAttributeMap(final ApplicationAttributes[] ApplicationAttributes) {
        final HashMap<String, String> attributeMap = new HashMap<String, String>();
        final Map<String, List<List<Map<String, String>>>> multipleAttributeMap = new HashMap<String, List<List<Map<String, String>>>>();
        for (final ApplicationAttributes applicationAttributes : ApplicationAttributes) {
            final AttributeDetails[] ad = applicationAttributes.getAttributeDetails();
            final List<List<Map<String, String>>> list = new ArrayList<List<Map<String, String>>>();
            if (ad.length == 1) {
                Attributes[] attributes;
                for (int length2 = (attributes = ad[0].getAttributes()).length, j = 0; j < length2; ++j) {
                    final Attributes attr = attributes[j];
                    attributeMap.put(attr.getAttributeKey(), attr.getAttributeValue().replace("'", "''"));
                }
            }
            else {
                AttributeDetails[] array;
                for (int length3 = (array = ad).length, k = 0; k < length3; ++k) {
                    final AttributeDetails attributeDetails = array[k];
                    final List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
                    Attributes[] attributes2;
                    for (int length4 = (attributes2 = attributeDetails.getAttributes()).length, l = 0; l < length4; ++l) {
                        final Attributes attr2 = attributes2[l];
                        final Map<String, String> at = new HashMap<String, String>();
                        at.put(attr2.getAttributeKey(), attr2.getAttributeValue().replace("'", "''"));
                        list2.add(at);
                    }
                    list.add(list2);
                }
            }
        }
        return attributeMap;
    }
    
    public static String[] getStringArray(String s, final String separtorOne, final String separatorTwo) {
        final int lenSpecialCharacter = s.replaceAll("[" + separtorOne + separatorTwo + "]", "").length();
        final int lenval = (s.length() - lenSpecialCharacter) / 2;
        boolean starFlag = false;
        boolean hashFlag = false;
        final String[] s2 = new String[lenval];
        final String s3 = s;
        int k = 0;
        int endIndex = 0;
        BPMApplicationLogger.logMe(1, "length of array=" + lenval);
        String s4 = s;
        try {
            if (s.contains(separtorOne)) {
                starFlag = true;
            }
            if (s.contains(separatorTwo)) {
                hashFlag = true;
            }
            while (endIndex != -1 && hashFlag) {
                BPMApplicationLogger.logMe(1, "in hashFlag");
                final int startIndex = s.substring(0).indexOf(separatorTwo);
                BPMApplicationLogger.logMe(1, s.substring(0));
                BPMApplicationLogger.logMe(1, "startIndex=" + startIndex);
                s = s.substring(startIndex + 1);
                endIndex = s.indexOf(separatorTwo);
                if (endIndex > 0 && endIndex <= s.length()) {
                    BPMApplicationLogger.logMe(1, s.substring(0, endIndex));
                    s2[k] = String.valueOf(separatorTwo) + s.substring(0, endIndex) + separatorTwo;
                    s = s.substring(endIndex + 1);
                    BPMApplicationLogger.logMe(1, "in arry=" + k + s2[k]);
                    ++k;
                }
            }
            endIndex = 0;
            while (endIndex != -1) {
                if (!starFlag) {
                    break;
                }
                BPMApplicationLogger.logMe(1, "in starflag" + s4);
                final int startIndex = s4.substring(0).indexOf(separtorOne);
                BPMApplicationLogger.logMe(1, s4.substring(0));
                BPMApplicationLogger.logMe(1, "startIndex=" + startIndex);
                s4 = s4.substring(startIndex + 1);
                endIndex = s4.indexOf(separtorOne);
                if (endIndex <= 0 || endIndex > s4.length()) {
                    continue;
                }
                BPMApplicationLogger.logMe(1, s4.substring(0, endIndex));
                s2[k] = String.valueOf(separtorOne) + s4.substring(0, endIndex) + separtorOne;
                s4 = String.valueOf(s4.substring(endIndex + 1)) + separtorOne;
                BPMApplicationLogger.logMe(1, "in array" + k + s2[k]);
                ++k;
            }
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, e);
            e.printStackTrace();
        }
        for (int i = 0; i < s2.length; ++i) {
            BPMApplicationLogger.logMe(1, "in finaly arry=" + s2[i]);
        }
        return s2;
    }
    
    public String generateSysRefNumber() {
        String sysNum = "";
        try {
            final String outputXML = APCallCreateXML.APSelect("SELECT SEQ_WEBSERVICE.NEXTVAL SYSREFNO FROM DUAL");
            final XMLParser xp = new XMLParser(outputXML);
            sysNum = xp.getValueOf("SYSREFNO");
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, e);
            e.printStackTrace();
        }
        return sysNum;
    }
}
