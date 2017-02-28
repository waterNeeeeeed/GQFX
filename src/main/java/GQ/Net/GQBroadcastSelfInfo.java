package GQ.Net;

import GQ.model.GQSelfInfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQBroadcastSelfInfo implements Runnable{
    private BlockingQueue<DatagramPacket> blockingQueue;
    private GQSelfInfo selfInfo;
    private byte[] buffer;// = GQNetUDPFactory.getClientBuffer();

    public GQBroadcastSelfInfo(BlockingQueue<DatagramPacket> bq){
        blockingQueue = bq;
    }

    public void run(){
        InetAddress ServerIP = GQNetUDPFactory.getServerIP();
        int ServerPort = GQNetUDPFactory.getDatagramServerPort();
        InetAddress ip = GQNetUDPFactory.getDatagramClientIP();
        int port = GQNetUDPFactory.getDatagramClientPort();
        selfInfo = GQNetUDPFactory.getSelfInfo();

        //创建接收Socket
        DatagramSocket ds = null;
        ds = GQNetUDPFactory.createDatagramSocket(ip, port);

        try {
            if (null != ds){
                System.out.println("Broadcast start...");
            }
            else {
                System.out.println("Broadcast failed...");
                System.exit(0);
            }
            while (true){
                System.out.println("Client send...");
                DatagramPacket sendPacket = GQNetUDPFactory.SendBQDeleteElement();
                ds.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
