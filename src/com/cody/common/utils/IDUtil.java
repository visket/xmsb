package com.cody.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IDUtil {

	private static final long ONE_STEP = 10;
	private static final Lock LOCK = new ReentrantLock();
	private static long lastTime = System.currentTimeMillis();
	private static short lastCount = 0;
	private static int count = 0;

	/**
	 * 按当前时间生成16位ID
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String generalSrid() {
		LOCK.lock();
		try {
			if (lastCount == ONE_STEP) {
				boolean done = false;
				while (!done) {
					long now = System.currentTimeMillis();
					if (now == lastTime) {
						try {
							Thread.currentThread();
							Thread.sleep(1);
						} catch (java.lang.InterruptedException e) {
						}
						continue;
					} else {
						lastTime = now;
						lastCount = 0;
						done = true;
					}
				}
			}
			count = lastCount++;
		} finally {
			LOCK.unlock();
			return lastTime + "" + String.format("%03d", count);
		}
	}

	/**
	 * 获得12位ID
	 * 
	 * @return
	 */
	public static String generalSrid12() {
		String id = generalSrid();
		return id.substring(4, id.length());
	}

	/**
	 * 生成图片ID,图片按时间分包存放 该ID为短ID
	 * 
	 * @return
	 */
	public synchronized static String generalImgid() {
		String uuid = UUID();
		return uuid.substring(0, uuid.indexOf("-", 9));
	}

	/**
	 * 生成文件ID,按时间分包存放 该ID为短ID
	 * 
	 * @return
	 */
	public synchronized static String generalFileid() {
		return UUID();
	}

	/**
	 * 随机生成UUID
	 * 
	 * @return
	 */
	public synchronized static String UUID() {
		return java.util.UUID.randomUUID().toString();
	}

	public static String getFormatDate(String formatString) {
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(now);
	}

	/**
	 * 生成会员卡ID
	 * 
	 * @return
	 */
	public static String memberCardID() {
		return getFormatDate("yy" + "0000");
	}

	/**
	 * 随机指定范围内N个不重复的数 最简单最基本的方法
	 * 
	 * @param min
	 *            指定范围最小值
	 * @param max
	 *            指定范围最大值
	 * @param n
	 *            随机数个数
	 */
	public static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(UUID());
	}
}
