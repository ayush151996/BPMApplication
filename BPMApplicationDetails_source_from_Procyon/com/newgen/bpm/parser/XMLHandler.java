// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.parser;

import java.util.EmptyStackException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.Stack;
import org.xml.sax.ext.DefaultHandler2;

public class XMLHandler extends DefaultHandler2
{
    private RequestXML requestXML;
    private String textWithinNode;
    Stack<RequestXMLNode> stack;
    
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.requestXML = new RequestXML();
        this.stack = new Stack<RequestXMLNode>();
    }
    
    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException, EmptyStackException {
        super.startElement(uri, localName, qName, attributes);
        this.textWithinNode = null;
        final RequestXMLNode requestXMLNode = new RequestXMLNode();
        requestXMLNode.setXMLNodeName(qName);
        for (int i = 0; i < attributes.getLength(); ++i) {
            final RequestXMLAttribute xmlAttribute = new RequestXMLAttribute();
            xmlAttribute.setAttributeName(attributes.getQName(i));
            xmlAttribute.setAttributeValue(attributes.getValue(i));
            requestXMLNode.addToAttributes(xmlAttribute);
        }
        if (!this.stack.empty()) {
            final RequestXMLNode parentNode = this.stack.peek();
            parentNode.addToChildConfigurationItems(requestXMLNode);
        }
        else {
            this.requestXML.setRequestXmlNode(requestXMLNode);
        }
        this.stack.push(requestXMLNode);
    }
    
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException, EmptyStackException {
        super.endElement(uri, localName, qName);
        final RequestXMLNode configurationNode = this.stack.pop();
        configurationNode.setTextWithinNode(this.textWithinNode);
    }
    
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
    
    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        super.characters(ch, start, length);
        if (this.textWithinNode == null) {
            this.textWithinNode = new String(ch, start, length).trim();
        }
        else {
            this.textWithinNode = String.valueOf(this.textWithinNode) + new String(ch, start, length).trim();
        }
    }
    
    public RequestXML getRequestXML() {
        return this.requestXML;
    }
}
