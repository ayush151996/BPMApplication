

package com.newgen.bpm.core;

import java.util.HashMap;

public interface ICallExecutor
{
    String createInputXML() throws Exception;
    
    String executeCall(final HashMap<String, String> p0) throws Exception;
    
    void responseHandler(final String p0, final String p1) throws Exception;
    
    void updateCallOutput(final String p0) throws Exception;
}
