package GQ.model;

import java.io.Serializable;

/**
 * Created by 帝 on 2017/2/12.
 * 自己的信息设计成单例类
 */
public class GQSelfInfo
    implements Serializable{

    /*private static GQSelfInfo instance = new GQSelfInfo();*/
    private GQPersonID sid;

    /*private GQSelfInfo(){

    }*/

    /*public static GQSelfInfo getInstance(){
        return instance;
    }*/

    public GQPersonID getSid() {
        return sid;
    }

    public void setSid(GQPersonID sid) {
        this.sid = sid;
    }

    @Override
    public String toString(){
        return "[" + sid.getId() + ", " + sid.getUsername() + "]";
    }
}
