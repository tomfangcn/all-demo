package primary.annotation.anno.handler;

import primary.annotation.anno.UseCase;
import primary.annotation.test.AnnoTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {
    public static void track(List<Integer> useCase, Class<?> clazz){

        for (Method m : clazz.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null){
                System.out.println("uc id :"+uc.id()+" implement");
                useCase.remove(uc.id());
            }
        }


        for (Integer i : useCase){
            System.out.println("uc id :"+i+" unimplement");
        }

    }

    public static void main(String[] args) {
        List<Integer> testData = new ArrayList<>();
        Collections.addAll(testData,1,2,3,4,5,6);
        UseCaseTracker.track(testData, AnnoTest.class);
    }
}
