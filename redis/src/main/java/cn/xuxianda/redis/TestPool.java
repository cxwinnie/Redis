package cn.xuxianda.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPool {
	public static void main(String[] args) {
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.set("key1", "value1");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JedisPoolUtil.release(jedisPool, jedis);
		}
	}
}
