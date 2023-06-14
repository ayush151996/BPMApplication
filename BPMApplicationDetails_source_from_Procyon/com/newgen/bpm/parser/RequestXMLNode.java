// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.parser;

import org.apache.commons.jxpath.JXPathContext;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class RequestXMLNode
{
    private String xmlNodeName;
    private String textWithinNode;
    private Set<RequestXMLAttribute> attributes;
    private Set<RequestXMLNode> childXMLItems;
    
    public RequestXMLNode() {
        this.attributes = new LinkedHashSet<RequestXMLAttribute>();
        this.childXMLItems = new LinkedHashSet<RequestXMLNode>();
    }
    
    public String getXmlNodeName() {
        return this.xmlNodeName;
    }
    
    public void setXMLNodeName(final String xmlNodeName) {
        this.xmlNodeName = xmlNodeName;
    }
    
    public String getTextWithinNode() {
        return this.textWithinNode;
    }
    
    public void setTextWithinNode(final String textWithinNode) {
        this.textWithinNode = textWithinNode;
    }
    
    public Set<RequestXMLAttribute> getAttributes() {
        return this.attributes;
    }
    
    public void setAttributes(final Set<RequestXMLAttribute> attributes) {
        this.attributes = attributes;
    }
    
    public Set<RequestXMLNode> getChildXMLItems() {
        return this.childXMLItems;
    }
    
    public void setChildConfigurationItems(final Set<RequestXMLNode> childXMLItems) {
        this.childXMLItems = childXMLItems;
    }
    
    public void addToAttributes(final RequestXMLAttribute resourceConfigurationAttributes) {
        if (this.attributes == null) {
            this.attributes = new LinkedHashSet<RequestXMLAttribute>();
        }
        this.attributes.add(resourceConfigurationAttributes);
    }
    
    public void addToChildConfigurationItems(final RequestXMLNode resourceConfiguration) {
        if (this.childXMLItems == null) {
            this.childXMLItems = new LinkedHashSet<RequestXMLNode>();
        }
        this.childXMLItems.add(resourceConfiguration);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final RequestXMLNode clone = (RequestXMLNode)super.clone();
        if (this.attributes != null) {
            clone.setAttributes(new LinkedHashSet<RequestXMLAttribute>(this.attributes.size()));
            for (final RequestXMLAttribute attribute : this.attributes) {
                clone.addToAttributes((RequestXMLAttribute)attribute.clone());
            }
        }
        if (this.childXMLItems != null) {
            clone.setChildConfigurationItems(new LinkedHashSet<RequestXMLNode>(this.childXMLItems.size()));
            for (final RequestXMLNode node : this.childXMLItems) {
                clone.addToChildConfigurationItems((RequestXMLNode)node.clone());
            }
        }
        return clone;
    }
    
    public List<?> searchObjectGraph(final String xpathExpression) throws Exception {
        return (List<?>)JXPathContext.newContext((Object)this).selectNodes(xpathExpression);
    }
}
