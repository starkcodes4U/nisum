package DAY_2;

import java.lang.reflect.Method;

class Sample {
    public void greet() {
        System.out.println("Hello!");
    }

    public int add(int x, int y) {
        return x + y;
    }
}

public class MetadataExample {
    public static void main(String[] args) {
        Sample sampleObj = new Sample();
        Class<?> objClass = sampleObj.getClass();

        System.out.println("Full Class Name: " + objClass.getName());
        System.out.println("Simple Class Name: " + objClass.getSimpleName());
        System.out.println("Is it an Interface? " + objClass.isInterface());

        System.out.println("Declared Methods:");
        Method[] methods = objClass.getMethods();
        for (Method m : methods) {
            System.out.println("- " + m.getName());
        }
    }
}
