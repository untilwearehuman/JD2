package homework.two_december_10.task70;

import java.util.Arrays;

public class Task70Run {

    public static final String CLASS_NAME = "Class name : ";
    public static final String CLASS_FIELDS = "Class fields : ";
    public static final String SUPER_CLASS = "Super class : ";
    public static final String CLASS_METHODS = "Class methods : ";
    public static final String CLASS_CONSTRUCTORS = "Class constructors : ";
    public static final String MAN_NAME = "Jack";
    public static final int MAN_AGE = 42;
    public static final int MAN_HEIGHT = 176;

    public static void classInformationReflectionMethod (Object o) {

        Class classInfo = o.getClass();
        System.out.println(CLASS_NAME + classInfo);
        System.out.println(CLASS_FIELDS + Arrays.toString(classInfo.getDeclaredFields()));
        System.out.println(SUPER_CLASS + classInfo.getSuperclass());
        System.out.println(CLASS_METHODS + Arrays.toString(classInfo.getDeclaredMethods()));
        System.out.println(CLASS_CONSTRUCTORS + Arrays.toString(classInfo.getConstructors()));

    }

    public static void main(String[] args) {

        classInformationReflectionMethod(new Man(MAN_NAME, MAN_AGE, MAN_HEIGHT));

    }
}
