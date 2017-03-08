package com.cody.common.core;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.cody.common.utils.PropertiesUtils;
import com.google.common.collect.Maps;

/**
 * @ClassName: Constants
 * @Description: 常量类
 * @author：wanhuan
 * @date：2016/11/21
 */

public class Constants {
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newConcurrentMap(); 
	
	//public static PropertiesUtils loader = PropertiesUtils.instance();

	/*public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getValue(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}*/

	public final static Integer RESOURCE_MENU = 0; // 菜单
	public final static Integer RESOURCE_BUTTON = 1; // 按钮

	public final static String SESSION_USER = "sessionUser";

	/**
	 * 上传文件主目录
	 */
	//public static final String UPLOAD_HOME_PATH = getConfig("image.path.home");
	/**
	 * 商品图片目录
	 */
	//public static final String UPLOAD_PROD_PATH = getConfig("image.path.prod");
	/**
	 * 品牌图片目录
	 */
	//public static final String UPLOAD_BRAND_PATH = getConfig("image.path.brand");
	//public static final String UPLOAD_BANNER_PATH = getConfig("image.path.banner");
	//public static final String UPLOAD_SUPPLYER_PATH = getConfig("image.path.supplyer");

	/**
	 * redis常量
	 */
	//public static final String REDIS_KEY_PREFIX = getConfig("redis.keyPrefix");
	public static final String REDIS_DICT = "dict:";
	public static final String REDIS_SEQUENCE_USER = "sequence:userid";
	public static final String REDIS_USER = "user:";

	/**
	 * URL
	 */
	//public static final String URL_IMAGE = getConfig("url.image");
	//public static final String URL_REMOTE_SHOP = getConfig("url.remote.shop");
	//public static final String URL_REMOTE_ADMIN = getConfig("url.remote.admin");

	/**
	 * 供应商常量
	 */
	public static final Integer SUPPLYER_ORG_PID = 1;// 供应商注册组织架构默认pid
	public static final Integer SUPPLYER_ADMIN_USER_TYPE = 1;

	/**
	 * RPC专用用户
	 */
	// public static final Long RPC_USER_ADMIN =
	// Long.valueOf(getConfig("rpc.userid.admin"));
	// public static final Long RPC_USER_SHOP =
	// Long.valueOf(getConfig("rpc.userid.shop"));

	public static final String DJT_GP = "DJT_GP";
}
