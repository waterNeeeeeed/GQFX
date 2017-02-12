package GQ.model;


import java.util.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQMessageQueue {
    private LinkedList<GQMessage> messagesListRead;
    private LinkedList<GQMessage> messagesListUnread;


    public LinkedList<GQMessage> getMessagesListRead() {
        return messagesListRead;
    }

    public void setMessagesListRead(LinkedList<GQMessage> messagesListReaded) {
        this.messagesListRead = messagesListReaded;
    }

    public LinkedList<GQMessage> getMessagesListUnread() {
        return messagesListUnread;
    }

    public void setMessagesListUnread(LinkedList<GQMessage> messagesListUnread) {
        this.messagesListUnread = messagesListUnread;
    }

    /*public void addMessageFromeListUnreadToListRead(){
        while(!messagesListUnread.isEmpty()){
            messagesListRead.offer(messagesListUnread.poll());
        }
    }*/


}
