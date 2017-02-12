package GQ.model;

import java.util.LinkedList;

/**
 * Created by 帝 on 2017/2/12.
 * 考虑到朋友分组问题，不再设计成单例类
 */
public class GQFriendList {
    //private static GQFriendList instance = new GQFriendList();
    private LinkedList<GQFriendInfo> friendList;

    /*private GQFriendList(){

    }*/

    /*public static GQFriendList getInstance() {
        return instance;
    }*/

    public LinkedList<GQFriendInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(LinkedList<GQFriendInfo> friendList) {
        this.friendList = friendList;
    }


}
