package mode.behavioral.templatemethod;

public abstract class Game {

	abstract void initialize();
	
	abstract void starPlay();
	
	abstract void endPlay();
	
	public final void play(){
		
		//初始化游戏
		initialize();
		//开始游戏
		starPlay();
		//结束游戏
		endPlay();
		
	}
	
}
