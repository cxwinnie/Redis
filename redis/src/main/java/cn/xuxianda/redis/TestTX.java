package cn.xuxianda.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
	private static Jedis jedis = new Jedis("192.168.43.9",6379);
	
	public boolean transMethod() throws InterruptedException {
	     int balance;// 可用余额
	     int debt;// 欠额
	     int amtToSubtract = 10;// 实刷额度

	     jedis.watch("balance");
	     jedis.set("balance","5");//此句不该出现，讲课方便。模拟其他程序已经修改了该条目
	     //Thread.sleep(7000);
	     balance = Integer.parseInt(jedis.get("balance"));
	     if (balance < amtToSubtract) {
	       jedis.unwatch();
	       System.out.println("modify");
	       return false;
	     } else {
	       System.out.println("***********transaction");
	       Transaction transaction = jedis.multi();
	       transaction.decrBy("balance", amtToSubtract);
	       transaction.incrBy("debt", amtToSubtract);
	       transaction.exec();
	       balance = Integer.parseInt(jedis.get("balance"));
	       debt = Integer.parseInt(jedis.get("debt"));

	       System.out.println("*******" + balance);
	       System.out.println("*******" + debt);
	       return true;
	     }
	  }
	
	public static void main(String[] args) throws Exception {
		TestTX test = new TestTX();
	     boolean retValue = test.transMethod();
	     System.out.println("main retValue-------: " + retValue);
	}
	
	@Test
	private static void test1() {
		Transaction tx = jedis.multi();
		
		tx.set("k4", "v4");
		tx.set("k5", "v5");
		
		//tx.exec();
		tx.discard();
	}
}
