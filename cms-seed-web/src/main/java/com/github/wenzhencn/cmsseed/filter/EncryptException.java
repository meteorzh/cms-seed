package com.github.wenzhencn.cmsseed.filter;

/**
 *
 * @author wenzhen
 * @Description
 * @Date: Created in 2019/10/22 17:16
 */
public class EncryptException extends Exception {

    public EncryptException(String message) {
        super(message);
    }

    public EncryptException(String message, Throwable cause) {
        super(message, cause);
    }

}
