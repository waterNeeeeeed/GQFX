package GQ.model.action;

import GQ.model.GQFriendInfo;
import GQ.model.GQFriendList;
import GQ.model.GQMessage;

import java.util.LinkedList;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQModelAction {

    /*
    public static void getListLastIndex(GQFriendList friendList){
        LinkedList.lastIndexOf()
    }*/

    /*将朋友信息添加进列表*/
    public static void addFriendToList(GQFriendInfo friendInfo, GQFriendList friendList)
            throws Exception {
        if (null == friendInfo){
            throw new Exception("朋友信息不存在");
        }
        if (friendList.getFriendList().contains(friendInfo)){
            throw new Exception("朋友信息已存在");
        }
        friendList.getFriendList().offer(friendInfo);
    }
    /*将朋友从列表中删除*/
    public static void deleteFriendFromList(GQFriendInfo fino, GQFriendList friendList)
            throws Exception {
        if (!friendList.getFriendList().remove(fino)){
            throw new Exception("将要删除的朋友并不存在");
        }
    }

    /*检查消息未读队列是否为空*/
    public static boolean msgListUnreadisEmpty(LinkedList<GQMessage> messagesListUnread){
        return messagesListUnread.isEmpty();
    }

    /*将消息添加到未读队列*/
    public static void addMessageToListUnread(GQMessage msg, LinkedList<GQMessage> messagesListUnread) throws Exception {
        if (null == msg){
            throw new Exception("消息不存在");
        }
        if (null == messagesListUnread){
            throw new Exception("未读消息队列不存在");
        }
        messagesListUnread.offer(msg);
    }

    /*将消息从未读队列转移到已读队列*/
    public static void addMessageFromeListUnreadToListRead(LinkedList<GQMessage> messagesListRead, LinkedList<GQMessage> messagesListUnread)
            throws Exception {
        if (null == messagesListRead || null == messagesListUnread){
            throw new Exception("消息队列不存在");
        }
        while(!messagesListUnread.isEmpty()){
            messagesListRead.offer(messagesListUnread.poll());
        }

    }
}
