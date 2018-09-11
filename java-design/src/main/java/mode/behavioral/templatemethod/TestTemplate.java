package mode.behavioral.templatemethod;

public class TestTemplate {


	public static void main(String[] args) {
		Game game = new FootBall();
		game.play();
		game = new BaseBall();
		game.play();
	}
	
}
