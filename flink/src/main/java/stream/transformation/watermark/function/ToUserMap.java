package stream.transformation.watermark.function;

import org.apache.flink.api.common.functions.MapFunction;

import stream.model.User;
import stream.model.avro.UserAvro;

public class ToUserMap implements MapFunction<UserAvro, User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User map(UserAvro value) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(value.getName().toString());
		user.setTimeStamp(value.getTimeStamp());
		return user;
	}

}
