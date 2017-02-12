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
        InetAddress ip = null;
        int port;
        DatagramSocket ds = null;
        try {
            ip = InetAddress.getByName("127.0.0.1");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        ds = GQNetUDPFactory.createDatagramSocket(null, 38888);
        port = ds.getPort();
        GQSelfInfo selfInfo = GQModelFactory.createGQSelfInfo(null, "gongtao", ip, 38888);
        String json = GQJSONUtil.toJSON(selfInfo);
        byte[] buffer = json.getBytes();
        InetAddress localIP = null;
        try {
            localIP = InetAddress.getLocalHost();
            //System.out.println(localIP.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DatagramPacket packet = GQNetUDPFactory.createDatagramPacket(buffer, buffer.length, ip, 36666);
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
