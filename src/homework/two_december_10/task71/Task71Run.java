package homework.two_december_10.task71;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task71Run {

    public static final String PRINT_HELLO_WORLD = "printHelloWorld";

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        HelloWorld helloWorld = (HelloWorld) Class.forName(HelloWorld.class.getName())
                .getDeclaredConstructor().newInstance();

        Method method = helloWorld.getClass().getDeclaredMethod(PRINT_HELLO_WORLD);
        method.setAccessible(true);
        method.invoke(helloWorld);

    }
}
