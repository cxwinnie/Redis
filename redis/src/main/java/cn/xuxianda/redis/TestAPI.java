package cn.xuxianda.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestAPI {
	@SuppressWarnings("all")
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.43.9", 6379);
		jedis.set("k1", "v1");
		jedis.set("k2", "v2");
		jedis.set("k3", "v3");
		System.out.println(jedis.get("k1"));
		System.out.println(jedis.get("k2"));
		System.out.println(jedis.get("k3"));
		System.out.println("---------------------keys*-----------------------");
		Set<String> keys = jedis.keys("*");
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println(jedis.exists("k1"));
		System.out.println(jedis.ttl("k1"));
		System.out.println("---------------------mset*-----------------------");
		jedis.mset("mkey1","mvalue1","mkey2","mvalue2");
		List<String> values = jedis.mget("mkey1","mkey2");
		for(String value : values){
			System.out.println(value);
		}
		System.out.println("---------------------list*-----------------------");
		jedis.lpush("lk", "lv1","lv2","lv3");
		List<String> list = jedis.lrange("lk", 0, -1);
		for(String key : list){
			System.out.println(key);
		}
		System.out.println("---------------------mset*-----------------------");
		jedis.sadd("orders", "jd001");
		jedis.sadd("orders", "jd002");
		jedis.sadd("orders", "jd003");
		Set<String> set = jedis.smembers("orders");
		for(Iterator it = set.iterator();it.hasNext();){
			System.out.println(it.next());
		}
		System.out.println("---------------------hash*-----------------------");
		jedis.hset("hash1", "userName", "lisi");
		System.out.println(jedis.hget("hash1", "userName"));
		Map map = new HashMap<String, String>();
		map.put("telephone", "13809074909");
		map.put("address", "江苏常州");
		map.put("email", "1234@qq.com");
		jedis.hmset("hash2", map);
		List<String> result = jedis.hmget("hash2", "telephone","email");
		for(String element : result){
			System.out.println(element);
		}
		System.out.println("---------------------zset*-----------------------");
		jedis.zadd("zset01", 60d,"v1");
		jedis.zadd("zset01", 70d,"v2");
		jedis.zadd("zset01", 80d,"v3");
		jedis.zadd("zset01", 90d,"v4");
		Set<String> s1 = jedis.zrange("zset01", 0, -1);
		for(Iterator it = s1.iterator();it.hasNext();){
			System.out.println(it.next());
		}
		System.out.println(jedis.zscore("zset01", "v1"));
	}
}
