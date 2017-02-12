package GQ.model;

import javafx.scene.image.Image;

import java.util.Date;
import java.util.Enumeration;

/**
 * Created by 帝 on 2017/2/12.
 */

/*用Ｆｌａｇ标记消息队列中的消息属于谁*/
enum Flag{
    SELF, FRIEDN
}
public class GQMessage {
    private Flag flag;
    private Date messagedate;
    private Object message;

    public Flag getFLAG() {
        return flag;
    }

    public void setFLAG(Flag FLAG) {
        this.flag = FLAG;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Date getMessagedate() {
        return messagedate;
    }

    public void setMessagedate(Date messagedate) {
        this.messagedate = messagedate;
    }


}
