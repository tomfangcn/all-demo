package quickstart.flink.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	// private ShardedJedis shardedJedis;// 切片额客户端连接
	// private ShardedJedisPool shardedJedisPool;// 切片连接池

	public RedisClient() {
		initialPool();
		jedis = jedisPool.getResource();
		// initialShardedPool();
		// shardedJedis = shardedJedisPool.getResource();

	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, "192.168.206.129", 6379);
	}

	public void show() {
		// KeyOperate();
		StringOperate();
		jedis.close();
	}

	private void KeyOperate() {
		System.out.println("======================key==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());
		// 设置 key001的过期时间
		System.out.println("设置 key001的过期时间为5秒:" + jedis.expire("key001", 5));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
		System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
		// 移除某个key的生存时间
		System.out.println("移除key001的生存时间：" + jedis.persist("key001"));
		System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
		// 查看key所储存的值的类型
		System.out.println("查看key所储存的值的类型：" + jedis.type("key001"));
	}

	private void StringOperate() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(jedis.lpop("test"));
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RedisClient().show();
	}
}
