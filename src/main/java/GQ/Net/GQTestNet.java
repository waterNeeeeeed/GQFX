package GQ.Net;

import GQ.model.GQModelFactory;
import GQ.model.GQSelfInfo;

import java.net.DatagramPacket;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQTestNet {
    public static void main(String[] args){

        GQReceiveFriendInfo r = new GQReceiveFriendInfo(GQNetUDPFactory.getReceiveBQ());
        new Thread(r).start();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        GQBroadcastSelfInfo b = new GQBroadcastSelfInfo(GQNetUDPFactory.getSendBQ());
        new Thread(b).start();



        int n = 0;

        new Thread(new Runnable() {
            public void run() {
                int m = 0;
                DatagramPacket tmpDP;
                while (true){
                    m++;
                    tmpDP = GQNetUDPFactory.ReceiveBQDeleteElement();
                    GQSelfInfo test = GQNetUDPFactory.convertReceiveBufferToRealObject(tmpDP.getData(), GQSelfInfo.class);
                    System.out.println("Receive:" + test.toString());
                    //System.out.println("Receive " + m + "条" + ":" + tmpDP.getAddress().getHostName()
                    //        + ":" + tmpDP.getAddress().getHostAddress() + ":" + tmpDP.getPort());
                    //System.out.println("%6s" + tmpDP.getAddress().getHostAddress() + ":" + tmpDP.getPort());
                }
            }
        }).start();



        while(true){
            n++;
            GQSelfInfo selfInfo = GQModelFactory.createGQSelfInfo(String.valueOf(n), "gongtao",
                    GQNetUDPFactory.getServerIP(), GQNetUDPFactory.getDatagramServerPort());
            DatagramPacket sp = GQNetUDPFactory.createDatagramPacketToClientBuffer(selfInfo,
                    GQNetUDPFactory.getServerIP(), GQNetUDPFactory.getDatagramServerPort());
            GQNetUDPFactory.SendBQAddElement(sp);
            System.out.println("Send " + n + "条");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
