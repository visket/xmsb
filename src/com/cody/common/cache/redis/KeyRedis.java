package com.cody.common.cache.redis;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.cody.common.core.Constants;
import com.cody.common.utils.DateUtils;
import com.cody.common.utils.JedisUtils;

/**
 * 
 * @ClassName: KeyRedis
 * @Description: redis发号器
 * @author：wanhuan
 * @date：2016/11/18
 */
@Component
public class KeyRedis {
	
	public Long nextID(String key){
		Date date = new Date();
		while(true){
			boolean ifExist = JedisUtils.setNx(key + "_flag", DateUtils.getDateTime(), 0);
			if(ifExist){//判断是否获得锁
				break;
			}
			//循环等待超过2s，则不等待了
			Date currentDate = new Date();
			if(currentDate.getTime() - date.getTime()/1000 > 2){
				break;
			}
		}
		//自增
		Long sequeu = JedisUtils.incr(key);
		//删除锁标识
		JedisUtils.del(key + "_flag");
		return sequeu;
	}
	/**
	 * 
	 * @Title: nextUserID 
	 * @Description: 用户分布式唯一id
	 * @return Integer    返回类型 
	 * @throws
	 */
	public Long nextUserID(){
		return nextID(Constants.REDIS_SEQUENCE_USER);
	}
	
}
