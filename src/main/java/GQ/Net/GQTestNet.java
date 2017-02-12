package GQ.Net;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQTestNet {
    public static void main(String[] args){
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(10);

        GQBroadcastSelfInfo b = new GQBroadcastSelfInfo(bq);
        new Thread(b).start();
        GQReceiveFriendInfo r = new GQReceiveFriendInfo(bq);
        new Thread(r).start();

        while (true){
            try{
                System.out.println(bq.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
