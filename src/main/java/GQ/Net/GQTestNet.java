package GQ.Net;

import GQ.model.GQModelFactory;
import GQ.model.GQSelfInfo;

import java.net.DatagramPacket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQTestNet {
    public static void main(String[] args){
        GQBroadcastSelfInfo b = new GQBroadcastSelfInfo(GQNetUDPFactory.getSendBQ());
        new Thread(b).start();
        GQReceiveFriendInfo r = new GQReceiveFriendInfo(GQNetUDPFactory.getReceiveBQ());
        new Thread(r).start();
        new Thread(new Runnable() {
            public void run() {
                GQSelfInfo selfInfo = GQModelFactory.createGQSelfInfo(null, "gongtao",
                        GQNetUDPFactory.getDatagramServerIP(), GQNetUDPFactory.getDatagramServerPort());


                while(true){
                    try {
                        DatagramPacket sp = GQNetUDPFactory.createDatagramPacketToClientBuffer(selfInfo, GQNetUDPFactory.getDatagramServerIP(), GQNetUDPFactory.getDatagramServerPort());
                        GQNetUDPFactory.getSendBQ().put(sp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        /*new Thread(o->(){
            createDatagramPacket(selfInfo, )
        }).start();*/


        while (true){
            try{
                GQSelfInfo test = GQNetUDPFactory.convertReceiveBufferToRealObject(GQNetUDPFactory.getReceiveBQ().take(), GQSelfInfo.class);
                System.out.println(test.toString());
                //System.out.println("%6s" + dsPacket.getAddress().getHostAddress() + ":" + dsPacket.getPort());
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
