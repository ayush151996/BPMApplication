// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.parser;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;

public class RequestXML
{
    private String requestXmlText;
    private RequestXMLNode requestXmlNode;
    
    public String getRequestXmlText() {
        return this.requestXmlText;
    }
    
    public void setRequestXmlText(final String requestXmlText) {
        this.requestXmlText = requestXmlText;
    }
    
    public RequestXMLNode getRequestXmlNode() {
        return this.requestXmlNode;
    }
    
    public void setRequestXmlNode(final RequestXMLNode requestXmlNode) {
        this.requestXmlNode = requestXmlNode;
    }
    
    public RequestXML requestXMLToRequest() {
        String requestText = this.getRequestXmlText();
        RequestXML toReturnRequest = null;
        if (requestText != null) {
            requestText = requestText.trim();
            if (!"".equals(requestText)) {
                ByteArrayInputStream byteArrayInputStream = null;
                StringBuffer parseXMLString = new StringBuffer("");
                try {
                    parseXMLString.append(requestText.trim());
                    byteArrayInputStream = new ByteArrayInputStream(parseXMLString.toString().getBytes());
                    final XMLHandler essentialMastersProviderXMLHandler = new XMLHandler();
                    final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    final SAXParser saxParser = saxParserFactory.newSAXParser();
                    saxParser.parse(byteArrayInputStream, essentialMastersProviderXMLHandler);
                    toReturnRequest = essentialMastersProviderXMLHandler.getRequestXML();
                }
                catch (SAXException e6) {
                    parseXMLString = new StringBuffer("");
                    parseXMLString.append("<Config>");
                    parseXMLString.append(requestText.trim());
                    parseXMLString.append("</Config>");
                    byteArrayInputStream = new ByteArrayInputStream(parseXMLString.toString().getBytes());
                    final XMLHandler essentialMastersProviderXMLHandler2 = new XMLHandler();
                    final SAXParserFactory saxParserFactory2 = SAXParserFactory.newInstance();
                    try {
                        final SAXParser saxParser2 = saxParserFactory2.newSAXParser();
                        saxParser2.parse(byteArrayInputStream, essentialMastersProviderXMLHandler2);
                        toReturnRequest = essentialMastersProviderXMLHandler2.getRequestXML();
                    }
                    catch (ParserConfigurationException e1) {
                        e1.printStackTrace();
                    }
                    catch (SAXException e2) {
                        e2.printStackTrace();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                catch (Exception e4) {
                    e4.printStackTrace();
                }
                finally {
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        }
                        catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        byteArrayInputStream = null;
                    }
                    if (toReturnRequest == null) {
                        toReturnRequest = new RequestXML();
                    }
                    toReturnRequest.setRequestXmlText(requestText);
                }
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    }
                    catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    byteArrayInputStream = null;
                }
                if (toReturnRequest == null) {
                    toReturnRequest = new RequestXML();
                }
                toReturnRequest.setRequestXmlText(requestText);
            }
            return this;
        }
        return toReturnRequest;
    }
}
