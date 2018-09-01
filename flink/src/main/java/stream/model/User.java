package stream.model;

public class User {

	private String name;

	private long timeStamp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", timeStamp=" + timeStamp + "]";
	}

}
