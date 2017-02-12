package GQ.model;

import sun.net.util.IPAddressUtil;

import java.net.InetAddress;
import java.util.Date;
//import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQModelFactory {

    public static GQPersonID createGQPersonID(String id, String username, InetAddress ip, int port){
        GQPersonID pid = new GQPersonID();

        pid.setIp(ip);
        pid.setPort(port);
        pid.setUsername(username);
        if (null == id){
            id = ip.getHostAddress() + ":" +  String.valueOf(port);
        }
        pid.setId(id);/*id由ip和端口组成，所以最后设置*/
        return pid;
    }

    public static GQMessage createGQMessage(Flag flag, Date date, Object message){
        GQMessage m = new GQMessage();
        m.setFLAG(flag);
        m.setMessagedate(date);
        m.setMessage(message);
        return m;
    }

    public static GQMessageQueue createGQMessageQueue(){
        GQMessageQueue mq = new GQMessageQueue();
        LinkedList<GQMessage> read = new LinkedList<GQMessage>();
        LinkedList<GQMessage> unread = new LinkedList<GQMessage>();
        mq.setMessagesListRead(read);
        mq.setMessagesListUnread(unread);
        return mq;
    }

    public static GQFriendInfo createGQFriendInfo(int index, String id, String username, InetAddress ip, int port){
        GQFriendInfo f = new GQFriendInfo();
        //f.setIndexInList(index);
        f.setFid(createGQPersonID(id, username, ip, port));
        f.setMessageQueue(createGQMessageQueue());
        return f;
    }

    public static GQFriendList createGQFriendList(){
        GQFriendList fl = new GQFriendList();
        fl.setFriendList(new LinkedList<GQFriendInfo>());
        return fl;
    }

    public static GQSelfInfo createGQSelfInfo(String id, String username, InetAddress ip, int port){
        /*GQSelfInfo.getInstance().setSid();*/
        GQSelfInfo selfInfo = new GQSelfInfo();
        selfInfo.setSid(createGQPersonID(id, username, ip, port));

        return selfInfo;
    }
}
