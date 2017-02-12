package GQ.util;

import GQ.model.GQModelFactory;
import GQ.model.GQSelfInfo;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQJSONUtilTest {
    @Test
    public void toJSON() throws Exception {

        String json = GQJSONUtil.toJSON(GQModelFactory.createGQSelfInfo(null, "gongtao", InetAddress.getByName("192.168.1.1"), 1000));
        System.out.println(json);
    }

    @Test
    public void fromJSON() throws Exception{
        String json = GQJSONUtil.toJSON(GQModelFactory.createGQSelfInfo(null, "gongtao", InetAddress.getByName("192.168.1.1"), 1000));
        GQSelfInfo test = GQJSONUtil.fromJSON(json, GQSelfInfo.class);
        System.out.print(test.getSid().getId() + ":" + test.getSid().getUsername() + ":" + test.getSid().getIp().toString());
    }
}