// 
// Decompiled by Procyon v0.5.36
// 

package com.newgen.bpm.utils;

import java.nio.charset.StandardCharsets;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import com.newgen.bpm.logger.BPMApplicationLogger;

public class ConnectSocket
{
    private static String socketIP;
    private static int socketPort;
    private static ConnectSocket socket;
    
    static {
        ConnectSocket.socket = null;
    }
    
    private ConnectSocket(final String socketIP, final int socketPort) {
        this.initSocket(socketIP, socketPort);
    }
    
    public static ConnectSocket getReference(final String socketIP, final int socketPort) {
        if (ConnectSocket.socket == null) {
            ConnectSocket.socket = new ConnectSocket(socketIP, socketPort);
        }
        return ConnectSocket.socket;
    }
    
    private void setSocketIP(final String socketIP) {
        ConnectSocket.socketIP = socketIP;
    }
    
    private void setSocketPort(final int socketPort) {
        ConnectSocket.socketPort = socketPort;
    }
    
    public static String connectToSocket(final String inputXml) {
        String response = "";
        BPMApplicationLogger.logMe(1, "Socket- socketIP: " + ConnectSocket.socketIP + "; socketPort:" + ConnectSocket.socketPort);
        try {
            Throwable t = null;
            try {
                final Socket s = new Socket(ConnectSocket.socketIP, ConnectSocket.socketPort);
                try {
                    BPMApplicationLogger.logMe(1, "inside try block");
                    final DataInputStream din = new DataInputStream(s.getInputStream());
                    final DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    writeDataToSocket(dout, inputXml);
                    response = readDataFromSocket(din);
                    BPMApplicationLogger.logMe(1, "connectToSocket response: " + response);
                }
                finally {
                    if (s != null) {
                        s.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable exception;
                    t = exception;
                }
                else {
                    final Throwable exception;
                    if (t != exception) {
                        t.addSuppressed(exception);
                    }
                }
            }
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, "Error in socket connection: ");
            BPMApplicationLogger.logMe(2, e);
        }
        return response;
    }
    
    private static boolean writeDataToSocket(final DataOutputStream dataOutputStream, final String data) {
        boolean bFlag = false;
        try {
            if (data != null && data.length() > 0) {
                dataOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
                bFlag = true;
            }
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, "Error in socket read/write: ");
            BPMApplicationLogger.logMe(2, e);
        }
        return bFlag;
    }
    
    private static String readDataFromSocket(final DataInputStream dataInputStream) {
        final StringBuilder data = new StringBuilder();
        try {
            final byte[] buffer = new byte[99999];
            final int length = dataInputStream.read(buffer, 0, 99999);
            byte[] arrayBytes = new byte[length];
            System.arraycopy(buffer, 0, arrayBytes, 0, length);
            data.append(new String(arrayBytes, StandardCharsets.UTF_8));
            int len = 0;
            while ((len = dataInputStream.read(buffer)) > 0) {
                arrayBytes = new byte[len];
                System.arraycopy(buffer, 0, arrayBytes, 0, len);
                data.append(new String(arrayBytes, StandardCharsets.UTF_8));
                if (dataInputStream.available() <= 0) {
                    break;
                }
            }
        }
        catch (Exception e) {
            BPMApplicationLogger.logMe(2, "Error in socket read/write: ");
            BPMApplicationLogger.logMe(2, e);
        }
        return data.toString();
    }
    
    public void initSocket(final String socketIP, final int socketPort) {
        this.setSocketIP(socketIP);
        this.setSocketPort(socketPort);
    }
}
