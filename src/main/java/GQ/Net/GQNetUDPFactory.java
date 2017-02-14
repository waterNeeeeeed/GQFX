package GQ.Net;

import org.apache.log4j.PatternLayout;

import java.net.*;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQNetUDPFactory {
    private static InetAddress DatagramServerIP;
    private static int DatagramServerPort;
    private static InetAddress DatagramClientIP;
    private static int DatagramClientPort;
    private static String selfname;

    static {
        try{
            DatagramServerIP = InetAddress.getLocalHost();
            DatagramServerPort = 36666;
            DatagramClientIP = InetAddress.getLocalHost();//InetAddress.getByName("127.0.0.1");
            DatagramClientPort = 38888;
            selfname = "gongtao";
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }

    public static String getSelfname() {
        return selfname;
    }

    public static void setSelfname(String selfname) {
        GQNetUDPFactory.selfname = selfname;
    }

    /*for test*/
    public static InetAddress getDatagramClientIP() {
        return DatagramClientIP;
    }

    public static int getDatagramClientPort() {
        return DatagramClientPort;
    }//


    public static InetAddress getDatagramServerIP() {
        return DatagramServerIP;
    }

    public static int getDatagramServerPort() {
        return DatagramServerPort;
    }

    public static DatagramSocket createDatagramSocket(InetAddress ip, int port){
        try{
            if (null != ip && -1 != port){
                return new DatagramSocket(port, ip);
            }
            return new DatagramSocket();
        }catch (SocketException se){
            se.printStackTrace();
        }
        return null;
    }

    public static DatagramPacket createDatagramPacket(byte[] buffer, int length, InetAddress ip, int port){
        if (null != ip && -1 != port){
            return new DatagramPacket(buffer, length, ip, port);
        }
        return new DatagramPacket(buffer, length);
    }
}
