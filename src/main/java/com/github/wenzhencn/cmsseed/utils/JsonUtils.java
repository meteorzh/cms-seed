package com.github.wenzhencn.cmsseed.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 * @author zhen.wen
 *
 */
public final class JsonUtils {
	
	private final static ObjectMapper MAPPER = new ObjectMapper();
	
	public final static ObjectMapper IGNORE_NULL_MAPPER = new ObjectMapper();
	
	static {
		IGNORE_NULL_MAPPER.setSerializationInclusion(Include.NON_NULL);
	}
	
	private JsonUtils() {}
	
	public static String toJson(Object obj) {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toJsonIgnoreNull(Object obj) {
		try {
			return IGNORE_NULL_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T fromJson(String json, Class<T> clz) {
		try {
			return MAPPER.readValue(json, clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> T fromJson(String json, TypeReference<T> type) {
		try {
			return MAPPER.readValue(json, type);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
