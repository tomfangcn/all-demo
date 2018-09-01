package quickstart.flink.redis;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

public class FlinkToRedisExample implements RedisMapper<Tuple2<String, String>> {
	public RedisCommandDescription getCommandDescription() {
		return new RedisCommandDescription(RedisCommand.HSET, "HASH_NAME");
	}

	public String getKeyFromData(Tuple2<String, String> data) {
		return data.f0;
	}

	public String getValueFromData(Tuple2<String, String> data) {
		return data.f1;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String hostName = "192.168.206.129";
		Integer port = 9999;

		// set up the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// get input data

		// 单机
		// FlinkJedisPoolConfig conf1 = new
		// FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();

		// 集群
		// FlinkJedisPoolConfig conf2 = new FlinkJedisPoolConfig.Builder()
		// .setNodes(new HashSet<InetSocketAddress>(Arrays.asList(new
		// InetSocketAddress(5601)))).build();

		// Sentinels
		// FlinkJedisSentinelConfig conf = new
		// FlinkJedisSentinelConfig.Builder()
		// .setMasterName("master").setSentinels(...).build();

		// text.addSink(new RedisSink<Tuple2<String, String>>(conf1, new
		// RedisExampleMapper());
		// text.addSink(new RedisSink());
		env.execute("Write into Redis");

	}

}
