package cn.xuxianda.redis;

import redis.clients.jedis.Jedis;

public class TestMS {
	public static void main(String[] args) {
		Jedis jedis_M = new Jedis("192.168.43.9",6379);
		Jedis jedis_S = new Jedis("192.168.43.9",6380);
		
		jedis_S.slaveof("192.168.43.9", 6379);
		
		jedis_M.set("k1", "v11");
		System.out.println(jedis_S.get("k1"));
		
	}
}
