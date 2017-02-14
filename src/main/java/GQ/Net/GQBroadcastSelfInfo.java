package GQ.Net;

import GQ.model.GQModelFactory;
import GQ.model.GQSelfInfo;
import GQ.util.GQJSONUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQBroadcastSelfInfo implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private GQSelfInfo selfInfo;

    public GQBroadcastSelfInfo(BlockingQueue<String> bq){
        blockingQueue = bq;
    }

    public void run(){
        InetAddress ServerIP = GQNetUDPFactory.getDatagramServerIP();
        int ServerPort = GQNetUDPFactory.getDatagramServerPort();
        InetAddress ip = GQNetUDPFactory.getDatagramClientIP();
        int port = GQNetUDPFactory.getDatagramClientPort();

        //创建接收Socket
        DatagramSocket ds = null;
        ds = GQNetUDPFactory.createDatagramSocket(ip, port);

        //创建自己的信息
        GQSelfInfo selfInfo = GQModelFactory.createGQSelfInfo(null, GQNetUDPFactory.getSelfname(), ServerIP, ServerPort);

        //创建数据报
        String json = GQJSONUtil.toJSON(selfInfo);
        byte[] buffer = json.getBytes();
        DatagramPacket packet = GQNetUDPFactory.createDatagramPacket(buffer, buffer.length, ServerIP, ServerPort);

        try {
            if (null != ds){
                System.out.println("Broadcast start...");
            }
            else {
                System.out.println("Broadcast failed...");
                System.exit(0);
            }
            while (true){
                ds.send(packet);
                Thread.sleep(1000);
                System.out.println("send");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
