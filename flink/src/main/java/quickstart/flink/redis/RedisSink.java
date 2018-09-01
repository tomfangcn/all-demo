package quickstart.flink.redis;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisSink extends RichSinkFunction<String> {

	private static final long serialVersionUID = 1L;

	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池

	@Override
	public void open(Configuration parameters) throws Exception {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, "192.168.206.129", 6379);
		jedis = jedisPool.getResource();
		super.open(parameters);
	}

	public void invoke(String value) throws Exception {
		// TODO Auto-generated method stub
		jedis.lpush("test", value.toString());

	}

	@Override
	public void close() throws Exception {
		if (jedis != null) {
			jedis.close();
		}
		super.close();
	}
}
