package GQ.Net;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQReceiveFriendInfo implements Runnable {
    private BlockingQueue<DatagramPacket> blockingQueue;
    byte[] buffer = GQNetUDPFactory.getServerBuffer();

    public GQReceiveFriendInfo(BlockingQueue<DatagramPacket> bq){
        blockingQueue = bq;
    }

    public void run(){
        InetAddress ip = GQNetUDPFactory.getServerIP();
        int port = GQNetUDPFactory.getDatagramServerPort();

        //创建Server
        DatagramSocket dsServer = GQNetUDPFactory.createDatagramSocket(ip, port);
        //创建接收buff
        DatagramPacket dsPacket = GQNetUDPFactory.createDatagramPacket(buffer, buffer.length, null, -1);

        int n = 1;
        try {
            if (null != dsServer){
                System.out.println("Receive start...");
            }
            else {
                System.out.println("Receive failed...");
                System.exit(0);
            }

            int m = 0;
            while(true){
                m++;
                dsServer.receive(dsPacket);
                //GQNetUDPFactory.ReceiveBQAddElement(dsPacket);
                /*DatagramPacket p = GQNetUDPFactory.createDatagramPacket(new byte[4096], 4096, dsPacket.getAddress(), dsPacket.getPort());
                dsServer.send(p);*/
                System.out.println("Receive " + m + "条" + ":" + dsPacket.getAddress().getHostName()
                        + ":" + dsPacket.getAddress().getHostAddress() + ":" + dsPacket.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
