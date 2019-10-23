package com.github.wenzhencn.cmsseed.filter;

import com.github.wenzhencn.cmsseed.utils.AesUtils;
import com.github.wenzhencn.cmsseed.utils.MD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 加密请求封装类<br/>
 *
 * @author wenzhen
 * @Description
 * @Date: 2019-10-22 17:23:21
 */
@Slf4j
public class EncryptionServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody;

    private Charset charSet;

    private String DEFAULT_SIGN_HEADER = "sign";

    public EncryptionServletRequestWrapper(HttpServletRequest request, EncryptionProperties properties) {
        super(request);

        try {
            String sign = request.getHeader(DEFAULT_SIGN_HEADER);
            if (StringUtils.isBlank(sign)) {
                throw new EncryptException("no sign in header");
            }
            String requestBodyStr = getRequestPostStr(request);
            if (StringUtils.isNotBlank(requestBodyStr)) {
                // 1. 解密
                String decryption = AesUtils.decrypt(requestBodyStr, properties.getKey(), properties.getSalt());
                // 2. 验签
                String rsign = MD5.encodeByMd5AndSalt(decryption, properties.getSignSalt());
                if(!sign.equals(rsign)) {
                    throw new EncryptException("wrong sign in header");
                }
                requestBody = decryption.getBytes(charSet);
            } else {
                requestBody = new byte[0];
            }
        } catch (EncryptException e) {
            log.warn(e.getMessage());
            requestBody = new byte[0];
        } catch (Exception e) {
            log.error("error when wrappe request", e);
            requestBody = new byte[0];
        }
    }

    public String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        String charSetStr = request.getCharacterEncoding();
        if (StringUtils.isBlank(charSetStr)) {
            charSetStr = "UTF-8";
        }
        charSet = Charset.forName(charSetStr);

        return StreamUtils.copyToString(request.getInputStream(), charSet);
    }

    /**
     * 重写 getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() {
        if (requestBody == null) {
            requestBody = new byte[0];
        }

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    /**
     * 重写 getReader()
     */
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}