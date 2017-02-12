package GQ.Net;

import org.apache.log4j.PatternLayout;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQNetUDPFactory {
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
