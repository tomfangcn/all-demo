package stream.transformation.watermark;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import stream.model.User;

public class PeriodicWatermark implements AssignerWithPeriodicWatermarks<User> {

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
		return 0;
	}

	public Watermark getCurrentWatermark() {
		// TODO Auto-generated method stub
		return new Watermark(currentMaxTimeStamp - maxOutOfOrderness);
	}

}
