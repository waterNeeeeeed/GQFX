package GQ.model;

import java.net.InetAddress;

/**
 * Created by 帝 on 2017/2/12.
 */
public class GQPersonID {
    private String id;
    private String username;
    private InetAddress ip;
    private int port;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
