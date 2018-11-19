package primary.annotation.test;

import primary.annotation.anno.Test;
import primary.annotation.anno.UseCase;

public class AnnoTest {

    public void execute(){
        System.out.println("executing ...");
    }

    @Test
    public void testExecute(){
        execute();
    }

    @UseCase(id=2)
    public void method1(){

    }
    @UseCase(id=1)
    public void method2(){

    }
}
