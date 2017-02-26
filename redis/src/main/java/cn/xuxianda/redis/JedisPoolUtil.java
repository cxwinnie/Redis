package cn.xuxianda.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = new JedisPool();
	
	private JedisPoolUtil(){}
	
	public static JedisPool getJedisPoolInstance(){
		if(null == jedisPool){
			synchronized (JedisPoolUtil.class) {
				if(null == jedisPool){
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxTotal(1000);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWaitMillis(100*1000);
					poolConfig.setTestOnBorrow(true);
					jedisPool = new JedisPool(poolConfig, "192.168.43.9", 6379);
				}
			}
		}
		return jedisPool;
	}
	
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
