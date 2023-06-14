// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

public class XMLParser
{
    private String parseString;
    private String copyString;
    private int IndexOfPrevSrch;
    
    public XMLParser() {
    }
    
    public XMLParser(final String paramString) {
        this.copyString = new String(paramString);
        this.parseString = this.toUpperCase(this.copyString, 0, 0);
    }
    
    public void setInputXML(final String paramString) {
        if (paramString != null) {
            this.copyString = new String(paramString);
            this.parseString = this.toUpperCase(this.copyString, 0, 0);
            this.IndexOfPrevSrch = 0;
        }
        else {
            this.parseString = null;
            this.copyString = null;
            this.IndexOfPrevSrch = 0;
        }
    }
    
    public String getServiceName() {
        try {
            return new String(this.copyString.substring(this.parseString.indexOf(this.toUpperCase("<Option>", 0, 0)) + new String(this.toUpperCase("<Option>", 0, 0)).length(), this.parseString.indexOf(this.toUpperCase("</Option>", 0, 0))));
        }
        catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
            throw localStringIndexOutOfBoundsException;
        }
    }
    
    public String getServiceName(final char paramChar) {
        try {
            if (paramChar == 'A') {
                return new String(this.copyString.substring(this.parseString.indexOf("<AdminOption>".toUpperCase()) + new String("<AdminOption>".toUpperCase()).length(), this.parseString.indexOf("</AdminOption>".toUpperCase())));
            }
            return "";
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "NoServiceFound";
        }
    }
    
    public boolean validateXML() {
        try {
            return this.parseString.indexOf("<?xml version=\"1.0\"?>".toUpperCase()) != -1;
        }
        catch (StringIndexOutOfBoundsException ex) {
            return false;
        }
    }
    
    public String getValueOf(final String paramString) {
        try {
            return new String(this.copyString.substring(this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">") + paramString.length() + 2, this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">")));
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public String getValueOf(final String paramString1, final String paramString2) {
        try {
            if (!paramString2.equalsIgnoreCase("Binary")) {
                return "";
            }
            int i = this.copyString.indexOf("<" + paramString1 + ">");
            if (i == -1) {
                return "";
            }
            final int j = this.copyString.lastIndexOf("</" + paramString1 + ">");
            i += new String("<" + paramString1 + ">").length();
            return this.copyString.substring(i, j);
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public String getValueOf(final String paramString, final boolean paramBoolean) {
        try {
            if (paramBoolean) {
                return new String(this.copyString.substring(this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">") + paramString.length() + 2, this.parseString.lastIndexOf("</" + this.toUpperCase(paramString, 0, 0) + ">")));
            }
            return new String(this.copyString.substring(this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">") + paramString.length() + 2, this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">")));
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public String getValueOf(final String paramString, final int paramInt1, final int paramInt2) {
        try {
            if (paramInt1 >= 0) {
                final int i = this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">", paramInt1);
                if (i > paramInt1 && (paramInt2 == 0 || paramInt2 >= i)) {
                    return new String(this.copyString.substring(this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">", paramInt1) + paramString.length() + 2, i));
                }
            }
            return "";
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public int getStartIndex(final String paramString, final int paramInt1, final int paramInt2) {
        try {
            if (paramInt1 >= 0) {
                final int i = this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">", paramInt1);
                if (i >= paramInt1 && (paramInt2 == 0 || paramInt2 >= i)) {
                    return i + paramString.length() + 2;
                }
            }
            return -1;
        }
        catch (StringIndexOutOfBoundsException ex) {
            return -1;
        }
    }
    
    public int getEndIndex(final String paramString, final int paramInt1, final int paramInt2) {
        try {
            if (paramInt1 >= 0) {
                final int i = this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">", paramInt1);
                if (i > paramInt1 && (paramInt2 == 0 || paramInt2 >= i)) {
                    return i;
                }
            }
            return -1;
        }
        catch (StringIndexOutOfBoundsException ex) {
            return -1;
        }
    }
    
    public int getTagStartIndex(final String paramString, final int paramInt1, final int paramInt2) {
        try {
            if (paramInt1 >= 0) {
                final int i = this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">", paramInt1);
                if (i >= paramInt1 && (paramInt2 == 0 || paramInt2 >= i)) {
                    return i;
                }
            }
            return -1;
        }
        catch (StringIndexOutOfBoundsException ex) {
            return -1;
        }
    }
    
    public int getTagEndIndex(final String paramString, final int paramInt1, final int paramInt2) {
        try {
            if (paramInt1 >= 0) {
                final int i = this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">", paramInt1);
                if (i > paramInt1 && (paramInt2 == 0 || paramInt2 >= i)) {
                    return i + paramString.length() + 3;
                }
            }
            return -1;
        }
        catch (StringIndexOutOfBoundsException ex) {
            return -1;
        }
    }
    
    public String getFirstValueOf(final String paramString) {
        try {
            this.IndexOfPrevSrch = this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">");
            return new String(this.copyString.substring(this.IndexOfPrevSrch + paramString.length() + 2, this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">")));
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public String getFirstValueOf(final String paramString, final int paramInt) {
        try {
            this.IndexOfPrevSrch = this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">", paramInt);
            return new String(this.copyString.substring(this.IndexOfPrevSrch + paramString.length() + 2, this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">", paramInt)));
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public String getNextValueOf(final String paramString) {
        try {
            this.IndexOfPrevSrch = this.parseString.indexOf("<" + this.toUpperCase(paramString, 0, 0) + ">", this.IndexOfPrevSrch + paramString.length() + 2);
            return new String(this.copyString.substring(this.IndexOfPrevSrch + paramString.length() + 2, this.parseString.indexOf("</" + this.toUpperCase(paramString, 0, 0) + ">", this.IndexOfPrevSrch)));
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public int getNoOfFields(String paramString) {
        int i = 0;
        int j = 0;
        try {
            for (paramString = String.valueOf(this.toUpperCase(paramString, 0, 0)) + ">"; this.parseString.indexOf("<" + paramString, j) != -1; j += paramString.length() + 2) {
                ++i;
                j = this.parseString.indexOf("</" + paramString, j);
                if (j != -1) {}
            }
        }
        catch (StringIndexOutOfBoundsException ex) {}
        return i;
    }
    
    public int getNoOfFields(String paramString, final int paramInt1, final int paramInt2) {
        int i = 0;
        int j = paramInt1;
        try {
            paramString = String.valueOf(this.toUpperCase(paramString, 0, 0)) + ">";
            do {
                j = this.parseString.indexOf("</" + paramString, j) + paramString.length() + 2;
                if (j != -1 && (j <= paramInt2 || paramInt2 == 0)) {
                    ++i;
                }
                if (this.parseString.indexOf("<" + paramString, j) == -1) {}
            } while (j < paramInt2 || paramInt2 == 0);
        }
        catch (StringIndexOutOfBoundsException ex) {}
        return i;
    }
    
    public String convertToSQLString(String paramString) {
        try {
            for (int i = paramString.indexOf("["); i != -1; i = paramString.indexOf("[", i + 2)) {
                paramString = String.valueOf(paramString.substring(0, i)) + "[[]" + paramString.substring(i + 1, paramString.length());
            }
        }
        catch (Exception ex) {}
        try {
            for (int j = paramString.indexOf("_"); j != -1; j = paramString.indexOf("_", j + 2)) {
                paramString = String.valueOf(paramString.substring(0, j)) + "[_]" + paramString.substring(j + 1, paramString.length());
            }
        }
        catch (Exception ex2) {}
        try {
            for (int k = paramString.indexOf("%"); k != -1; k = paramString.indexOf("%", k + 2)) {
                paramString = String.valueOf(paramString.substring(0, k)) + "[%]" + paramString.substring(k + 1, paramString.length());
            }
        }
        catch (Exception ex3) {}
        paramString = paramString.replace('?', '_');
        return paramString;
    }
    
    public String getValueOf(final String paramString1, final String paramString2, final int paramInt1, final int paramInt2) {
        try {
            if (!paramString2.equalsIgnoreCase("Binary")) {
                return "";
            }
            int i = this.copyString.indexOf("<" + paramString1 + ">", paramInt1);
            if (i == -1) {
                return "";
            }
            final int j = this.copyString.indexOf("</" + paramString1 + ">", paramInt1);
            if (j > paramInt2) {
                return "";
            }
            i += new String("<" + paramString1 + ">").length();
            return this.copyString.substring(i, j);
        }
        catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
    
    public String toUpperCase(final String paramString, final int paramInt1, final int paramInt2) throws StringIndexOutOfBoundsException {
        String str = "";
        try {
            int i = paramString.length();
            final char[] arrayOfChar = new char[i];
            paramString.getChars(0, i, arrayOfChar, 0);
            while (i-- > 0) {
                arrayOfChar[i] = Character.toUpperCase(arrayOfChar[i]);
            }
            str = new String(arrayOfChar);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return str;
    }
    
    public String changeValue(final String paramString1, final String paramString2, final String paramString3) {
        try {
            final String str1 = paramString1.toUpperCase();
            final String str2 = new String("<" + paramString2 + ">").toUpperCase();
            final int i = str1.indexOf(str2) + str2.length();
            final int j = str1.indexOf(new String("</" + paramString2 + ">").toUpperCase());
            String str3 = paramString1.substring(0, i);
            str3 = String.valueOf(str3) + paramString3 + paramString1.substring(j);
            return str3;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public void changeValue(final String paramString1, final String paramString2) {
        try {
            final String str1 = "<" + paramString1 + ">".toUpperCase();
            int i = this.parseString.indexOf(str1);
            if (i > -1) {
                i += str1.length();
                final int j = this.parseString.indexOf("</" + paramString1 + ">".toUpperCase());
                final String str2 = this.copyString.substring(0, i);
                this.copyString = String.valueOf(str2) + paramString2 + this.copyString.substring(j);
            }
            else {
                final int j;
                i = (j = this.parseString.lastIndexOf("</"));
                final String str2 = this.copyString.substring(0, i);
                this.copyString = String.valueOf(str2) + "<" + paramString1 + ">" + paramString2 + "</" + paramString1 + ">" + this.copyString.substring(j);
            }
            this.parseString = this.toUpperCase(this.copyString, 0, 0);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public String toString() {
        return this.copyString;
    }
    
    public String ParseFieldValue(final String paramString1, final String paramString2, final String paramString3) {
        try {
            int i = this.parseString.indexOf("<" + this.toUpperCase(paramString1, 0, 0) + ">" + "<" + this.toUpperCase(paramString2, 0, 0) + ">" + this.toUpperCase(paramString3, 0, 0) + "</" + this.toUpperCase(paramString2, 0, 0) + ">");
            if (i != -1) {
                i += paramString1.length() + 2;
                return new String(this.copyString.substring(i, this.parseString.indexOf("</" + this.toUpperCase(paramString1, 0, 0) + ">", i)));
            }
            return "";
        }
        catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
            localStringIndexOutOfBoundsException.printStackTrace();
            return "";
        }
    }
    
    public String getValueListXml(final String paramString) {
        try {
            final int i = this.parseString.indexOf("<NAME>" + paramString.toUpperCase() + "</NAME>");
            final int j = this.parseString.indexOf("<VALUELIST>", i);
            final int k = this.parseString.indexOf("</FIELD>", i);
            if (k != -1) {}
            return this.copyString.substring(j, k);
        }
        catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
            localStringIndexOutOfBoundsException.printStackTrace();
            return null;
        }
    }
    
    public String getFieldValue(final String paramString) {
        try {
            final StringBuffer localStringBuffer = new StringBuffer("<NAME>");
            localStringBuffer.append(paramString.toUpperCase());
            localStringBuffer.append("</NAME>");
            int i = this.parseString.indexOf(localStringBuffer.toString());
            i = this.parseString.indexOf("<VALUE>", i);
            final String str = this.copyString.substring(i + 7, this.parseString.indexOf("</VALUE>", i));
            return str;
        }
        catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
            localStringIndexOutOfBoundsException.printStackTrace();
            return null;
        }
    }
}
