package GQ.Net;

import GQ.model.GQSelfInfo;
import GQ.util.GQJSONUtil;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQReceiveFriendInfo implements Runnable {
    private BlockingQueue<String> blockingQueue;
    public GQReceiveFriendInfo(BlockingQueue<String> bq){
        blockingQueue = bq;
    }

    public void run(){
        InetAddress ip = GQNetUDPFactory.getDatagramServerIP();
        int port = GQNetUDPFactory.getDatagramServerPort();

        //创建Server
        DatagramSocket dsServer = null;
        dsServer = GQNetUDPFactory.createDatagramSocket(ip, port);//new DatagramSocket(36666, ip);//GQNetUDPFactory.createDatagramSocket(ip, 36666);

        //创建接收buff
        byte[] buffer = new byte[4096];
        DatagramPacket dsPacket = GQNetUDPFactory.createDatagramPacket(buffer, 4096, null, -1);

        int n = 1;
        try {
            if (null != dsServer){
                System.out.println("Receive start...");
                System.out.println("Receive:" + dsServer.getInetAddress());
            }
            else {
                System.out.println("Receive failed...");
                System.exit(0);
            }

            while(true){
                dsServer.receive(dsPacket);
                GQSelfInfo selfInfo = GQJSONUtil.fromJSON(new String(buffer), GQSelfInfo.class);
                blockingQueue.put("收到了第" + n + "条：" + selfInfo.toString());
                System.out.println(dsPacket.getAddress().getHostAddress() + ":" + dsPacket.getPort());
                n++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
