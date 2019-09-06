package com.common.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;

    public String getUrl() {
        String tempIP = "127.0.0.1";
        try {
            tempIP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements()) {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(tempIP)) {
                        return "http://" + ip.getHostAddress() + ":" + this.serverPort;
                    }
                }
            }
            return "http://" + tempIP + ":" + this.serverPort;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

}
