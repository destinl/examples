package com.ls.javacgdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/5 21:21
 */
@Component
@ConfigurationProperties(prefix = "ip.whitelist")
public class IpWhitelistProperties {

    private List<String> addresses;

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}