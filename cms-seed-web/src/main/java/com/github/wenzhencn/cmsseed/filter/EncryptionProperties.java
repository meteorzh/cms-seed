package com.github.wenzhencn.cmsseed.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author wenzhen
 * @Description
 * @Date: 2019-10-22 16:04:03
 */
@Data
@ConfigurationProperties(prefix = "app.req-crypt")
public class EncryptionProperties {

    /**
     * 密钥
     */
    private String key;

    /**
     * 加密 盐值
     */
    private String salt;

    /**
     * 签名 盐值
     */
    private String signSalt;

}
