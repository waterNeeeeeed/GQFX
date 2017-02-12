package GQ.model;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQFriendInfo {
    private GQPersonID fid;
    private GQMessageQueue messageQueue;
    //private int indexInList;

    /*public int getIndexInList() {
        return indexInList;
    }

    public void setIndexInList(int indexInList) {
        this.indexInList = indexInList;
    }*/

    public GQPersonID getFid() {
        return fid;
    }

    public void setFid(GQPersonID fid) {
        this.fid = fid;
    }

    public GQMessageQueue getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(GQMessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }



}
