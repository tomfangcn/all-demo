package mode.structuraltype.proxy;

public class RealImage implements Image {

	private String fileName;
	
	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}
	
	@Override
	public void display() {
		System.out.println("RealImage display "+fileName);
	}

	public void loadFromDisk(String fileName){
		System.out.println("RealImage loading Image");
	}
}
