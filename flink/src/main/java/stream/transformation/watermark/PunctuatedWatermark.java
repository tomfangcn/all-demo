package stream.transformation.watermark;

import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import stream.model.User;

public class PunctuatedWatermark implements AssignerWithPunctuatedWatermarks<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long maxOutOfOrderness = 0;
	private long currentMaxTimeStamp;

	public long extractTimestamp(User user, long previousElementTimestamp) {
		// TODO Auto-generated method stub

		long timeStamp = user.getTimeStamp();
		currentMaxTimeStamp = Math.max(timeStamp, currentMaxTimeStamp);
		return timeStamp;
	}

	public Watermark checkAndGetNextWatermark(User user, long extractedTimestamp) {
		// TODO Auto-generated method stub
		return new Watermark(currentMaxTimeStamp - maxOutOfOrderness);
	}

}
