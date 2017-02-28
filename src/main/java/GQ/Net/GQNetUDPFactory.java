package GQ.Net;

import GQ.model.GQModelFactory;
import GQ.model.GQSelfInfo;
import GQ.util.GQJSONUtil;

import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQNetUDPFactory {
    private static InetAddress ServerIP;
    private static int DatagramServerPort;
    private static InetAddress DatagramClientIP;
    private static int DatagramClientPort;
    private static GQSelfInfo selfInfo;
    private static byte[] ServerBuffer;
    private static byte[] ClientBuffer;
    private final static int BUFFER_LEN = 4096;
    private static BlockingQueue<DatagramPacket> receiveBQ;
    private static BlockingQueue<DatagramPacket> sendBQ;
    private final static int BQ_LEN = 10;

    static {
        try{
            ServerIP = InetAddress.getLocalHost();//InetAddress.getByName("127.0.0.1");
            DatagramServerPort = 36666;
            DatagramClientIP = InetAddress.getLocalHost();//InetAddress.getByName("127.0.0.1");
            DatagramClientPort = 38888;
            selfInfo = GQModelFactory.createGQSelfInfo(null, "gongtao", ServerIP, DatagramServerPort);
            ServerBuffer = new byte[BUFFER_LEN];
            ClientBuffer = new byte[BUFFER_LEN];
            receiveBQ = new LinkedBlockingDeque<DatagramPacket>();
            sendBQ = new LinkedBlockingDeque<DatagramPacket>();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }

    //send
    public static<T> DatagramPacket createDatagramPacketToClientBuffer(T content, InetAddress ip, int port){
        convertSendInfoToClientBuffer(content);
        return GQNetUDPFactory.createDatagramPacket(GQNetUDPFactory.getClientBuffer(), GQNetUDPFactory.getClientBuffer().length,  ip, port);
    }

    //send
    public static<T> void convertSendInfoToClientBuffer(T content){
        String json = GQJSONUtil.toJSON(content);
        setClientBuffer(json.getBytes());
    }

    //receive
    public static<T> T convertReceiveBufferToRealObject(byte[] content, Class<T> obj){
        return GQJSONUtil.fromJSON(new String(content), obj);
    }


    public static void SendBQAddElement(DatagramPacket e){
        try {
            sendBQ.put(e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public static DatagramPacket SendBQDeleteElement(){
        try{
            return sendBQ.take();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static void ReceiveBQAddElement(DatagramPacket e){
        try {
            receiveBQ.put(e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public static DatagramPacket ReceiveBQDeleteElement(){
        try {
            return receiveBQ.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BlockingQueue<DatagramPacket> getSendBQ() {
        return sendBQ;
    }

    public static void setSendBQ(BlockingQueue<DatagramPacket> sendBQ) {
        GQNetUDPFactory.sendBQ = sendBQ;
    }

    public static BlockingQueue<DatagramPacket> getReceiveBQ() {
        return receiveBQ;
    }

    public static void setReceiveBQ(BlockingQueue<DatagramPacket> receiveBQ) {
        GQNetUDPFactory.receiveBQ = receiveBQ;
    }

    public static byte[] getServerBuffer() {
        return ServerBuffer;
    }

    public static void setServerBuffer(byte[] serverBuffer) {
        ServerBuffer = serverBuffer;
    }

    public static byte[] getClientBuffer() {
        return ClientBuffer;
    }

    public static void setClientBuffer(byte[] clientBuffer) {
        ClientBuffer = clientBuffer;
    }

    public static GQSelfInfo getSelfInfo() {
        return selfInfo;
    }

    public static void setSelfInfo(GQSelfInfo selfInfo) {
        GQNetUDPFactory.selfInfo = selfInfo;
    }

    /*for test*/
    public static InetAddress getDatagramClientIP() {
        return DatagramClientIP;
    }

    public static int getDatagramClientPort() {
        return DatagramClientPort;
    }//


    public static InetAddress getServerIP() {
        return ServerIP;
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
