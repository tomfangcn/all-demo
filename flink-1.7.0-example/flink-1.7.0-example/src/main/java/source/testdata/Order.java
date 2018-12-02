package source.testdata;

public class Order {
    private int orderId;

    private int buyVol;

    private double buyPrice;

    private String desc;

    private int uid;

    private String userName;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBuyVol() {
        return buyVol;
    }

    public void setBuyVol(int buyVol) {
        this.buyVol = buyVol;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", buyVol=" + buyVol +
                ", buyPrice=" + buyPrice +
                ", desc='" + desc + '\'' +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
