package com.github.wenzhencn.cmsseed.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 
 * @author zhen.wen
 *
 */
public final class WebUtils {
	
	private static final String HTTP_METHOD_POST = "POST";
	
    private static final String HTTP_METHOD_GET = "GET";
    
	private WebUtils() {}
	
	
	/**
	 * 将ServletRequest转型为HttpServletRequest
	 * 
	 * @param request
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }
	
	/**
	 * 通过ServletResponse对象向客户端返回Json
	 * 
	 * @param response ServletResponse
	 * @param obj 向客户端发送的对象
	 */
	public static void responseJson(ServletResponse response, Object obj) {
		Validate.notNull(response, "Response must not be null");
		String json = JsonUtils.toJson(obj);
		Validate.notBlank(json, "No message to send");
		try(ServletOutputStream out = response.getOutputStream()) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			out.write(org.apache.tomcat.util.codec.binary.StringUtils.getBytesUtf8(json));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取request中的body数据
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String readBody(ServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		String str, allStr = "";
		while((str = reader.readLine()) != null) {
			allStr += str;
		}
		return allStr;
	}
	
	/**
	 * 发送GET请求
	 * @param urlStr
	 * @param headers
	 * @param query
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public static String doGet(String urlStr, Map<String, String> headers, Map<String, String> query) throws IOException {
        String paramStr = prepareQueryString(query);
        if (StringUtils.isNotEmpty(paramStr)) {
        	urlStr += "?" + paramStr;
        }
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(HTTP_METHOD_GET);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                conn.setRequestProperty(key, headers.get(key));
            }
        }

        conn.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String result = "";
        while ((line = br.readLine()) != null) {
            result += "" + line;
        }
        br.close();
        return result;
    }

	/**
	 * 发送post请求(Content-Type为JSON)
	 * @param urlStr
	 * @param headers
	 * @param query
	 * @param jsonBody
	 * @return
	 * @throws IOException
	 */
	public static String doPost(String urlStr, Map<String, String> headers, Map<String, String> query, String jsonBody) throws IOException {
		String paramStr = prepareQueryString(query);
        if (StringUtils.isNotEmpty(paramStr)) {
        	urlStr += "?" + paramStr;
        }
        
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(HTTP_METHOD_POST);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                conn.setRequestProperty(key, headers.get(key));
            }
        }
        OutputStream os = conn.getOutputStream();
        os.write(org.apache.tomcat.util.codec.binary.StringUtils.getBytesUtf8(jsonBody));
        os.close();

        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            result += "" + line;
        }
        br.close();

        return result;
    }
	
	/**
	 * 请求是否是POST请求
	 * @param request
	 * @return
	 */
	public static boolean isPost(HttpServletRequest request) {
		return HTTP_METHOD_POST.equals(request.getMethod());
	}
	
	/**
	 * 组装URL的查询参数
	 * @param map
	 * @return
	 */
	private static String prepareQueryString(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (sb.length() > 0) {
            	sb.append("&");
            }
            sb.append(key).append("=").append(value);
        }
        return sb.toString();
    }
	
}
