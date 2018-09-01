package stream;

import java.util.Properties;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import stream.model.User;
import stream.model.avro.UserAvro;
import stream.model.avro.schema.AvroDeserializationSchema;
import stream.transformation.watermark.PunctuatedWatermark;
import stream.transformation.watermark.function.ToUserMap;

public class JobDemo {

	public static void main(String[] args) throws Exception {

		String topic = "test-topic";
		Properties props = new Properties();
		props.put("bootstrap.servers", "");
		props.put("security.protocol", "");
		props.put("sasl.kerberos.service.name", "");
		props.put("group.id", "");

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// 配置并行度
		env.setParallelism(1);
		// 禁用操作链
		env.disableOperatorChaining();
		// 配置重启
		env.setRestartStrategy(RestartStrategies.noRestart());
		// 配置时间特性--事件时间
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		// 设置周期性水位线的发射间隔
		// env.getConfig().setAutoWatermarkInterval(100);
		// 配置检查点
		// env.enableCheckpointing(500);
		// env.setStateBackend(new FsStateBackend("hdfs:///flink/checkpoints"));
		// CheckpointConfig checkpointConfig = env.getCheckpointConfig();
		// checkpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
		// checkpointConfig.setMinPauseBetweenCheckpoints(500);
		// checkpointConfig.setCheckpointTimeout(60000);
		// checkpointConfig.setMaxConcurrentCheckpoints(1);
		// checkpointConfig
		// .enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

		FlinkKafkaConsumer010<UserAvro> consumer = new FlinkKafkaConsumer010<UserAvro>(topic,
				new AvroDeserializationSchema<UserAvro>(), props);
		consumer.setStartFromLatest();

		DataStream<User> sourceStream = env.addSource(consumer).map(new ToUserMap());

		sourceStream.assignTimestampsAndWatermarks(new PunctuatedWatermark()).keyBy(0)
				.window(TumblingEventTimeWindows.of(Time.seconds(5)))
				// .trigger(trigger)
				// .evictor()
				// delay = watermark - window.end;
				.allowedLateness(Time.seconds(5));

		env.execute("JobDemo execute");

	}

}
