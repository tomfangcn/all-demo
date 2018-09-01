package jproxy.staticproxy;

public class BussinessProxy implements BussinessInterface {

	private BussinessInterface bussinessImpl;

	public BussinessProxy(BussinessInterface bussinessImpl) {
		this.bussinessImpl = bussinessImpl;
	}

	@Override
	public void execute() {
		System.out.println("ǰ����...");
		bussinessImpl.execute();
		System.out.println("������...");

	}

}
