package com.github.wenzhencn.cmsseed.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 版本号工具
 * @author zhen.wen
 *
 */
public class VersionNumUtils {
	
	private static final Pattern PATTERN = Pattern.compile("^((0|([1-9]\\d*))\\.){2}(0|([1-9]\\d*))$");
	
	/**
	 * 判断版本号是否相同
	 * @param ver1
	 * @param ver2
	 * @return
	 */
	public static boolean equals(String ver1, String ver2) {
		return StringUtils.equals(ver1, ver2);
	}
	
	/**
	 * 比较版本号<br />
	 * ver1 > ver2 1<br />
	 * ver1 == ver2 0<br />
	 * ver1 < ver2 -1<br />
	 * @param ver1
	 * @param ver2
	 * @return
	 */
	public static int compare(String ver1, String ver2) {
		Assert.isTrue(valid(ver1) && valid(ver2), "version num must be valid");
		if(equals(ver1, ver2)) return 0;
		VersionNum vn1 = new VersionNum(ver1);
		VersionNum vn2 = new VersionNum(ver2);
		
		return vn1.after(vn2) ? 1 : -1;
	}
	
	/**
	 * 判断版本号格式
	 * @param ver
	 * @return
	 */
	public static boolean valid(String ver) {
		Assert.notNull(ver, "ver should not be null");
		Matcher matcher = PATTERN.matcher(ver);
		return matcher.find();
	}
	
	/**
	 * 
	 * @author zhen.wen
	 *
	 */
	@Getter
	@Setter
	@NoArgsConstructor
	protected static class VersionNum {
		
		/**
		 * 主版本号
		 */
		private Integer main;
		
		/**
		 * 次版本号
		 */
		private Integer minor;
		
		/**
		 * 修订版本号
		 */
		private Integer revise;
		
		public VersionNum(String ver) {
			String[] ns = ver.split("\\.");
			this.main = Integer.valueOf(ns[0]);
			this.minor = Integer.valueOf(ns[1]);
			this.revise = Integer.valueOf(ns[2]);
		}
		
		/**
		 * 当前版本是否在给定版本之后<br />
		 * this > vn ?
		 * @param vn
		 * @return
		 */
		public boolean after(VersionNum vn) {
			if(this.main > vn.getMain()) {
				return true;
			} else if(this.main < vn.getMain()) {
				return false;
			}
			
			if(this.minor > vn.getMinor()) {
				return true;
			} else if(this.minor < vn.getMinor()) {
				return false;
			}
			
			if(this.revise > vn.getRevise()) {
				return true;
			}
			
			return false;
		}
		
	}

}
