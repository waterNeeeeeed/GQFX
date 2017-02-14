package GQ.Net;

import GQ.model.GQModelFactory;
import GQ.model.GQSelfInfo;
import GQ.util.GQJSONUtil;
import org.apache.log4j.PatternLayout;

import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQNetUDPFactory {
    private static InetAddress DatagramServerIP;
    private static int DatagramServerPort;
    private static InetAddress DatagramClientIP;
    private static int DatagramClientPort;
    private static GQSelfInfo selfInfo;
    private static byte[] ServerBuffer;
    private static byte[] ClientBuffer;
    private final static int BUFFER_LEN = 4096;
    private static BlockingQueue<byte[]> receiveBQ;
    private static BlockingQueue<DatagramPacket> sendBQ;
    private final static int BQ_LEN = 10;

    static {
        try{
            DatagramServerIP = InetAddress.getLocalHost();//InetAddress.getByName("127.0.0.1");
            DatagramServerPort = 36666;
            DatagramClientIP = InetAddress.getLocalHost();//InetAddress.getByName("127.0.0.1");
            DatagramClientPort = 38888;
            selfInfo = GQModelFactory.createGQSelfInfo(null, "gongtao", DatagramServerIP, DatagramServerPort);
            ServerBuffer = new byte[BUFFER_LEN];
            ClientBuffer = new byte[BUFFER_LEN];
            receiveBQ = new ArrayBlockingQueue<byte[]>(BQ_LEN);
            sendBQ = new ArrayBlockingQueue<DatagramPacket>(BQ_LEN);
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

    public static BlockingQueue<DatagramPacket> getSendBQ() {
        return sendBQ;
    }

    public static void setSendBQ(BlockingQueue<DatagramPacket> sendBQ) {
        GQNetUDPFactory.sendBQ = sendBQ;
    }

    public static BlockingQueue<byte[]> getReceiveBQ() {
        return receiveBQ;
    }

    public static void setReceiveBQ(BlockingQueue<byte[]> receiveBQ) {
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
