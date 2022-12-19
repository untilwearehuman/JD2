package homework.two_december_10.task72;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class Task72Run {

    public static final int YEAR_OF_FOUNDATION = 2010;
    public static final String FOUNDATION_YEAR = "Year of foundation of Academy is ";
    public static final String ACADEMY_WAS_FOUNDED = "Academy was founded ";
    public static final String YEARS_AGO = " years ago";
    public static final String THIS_YEAR = "This year is ";
    public static final String METHODS_WITH_ANNOTATIONS = "METHODS WITH ANNOTATIONS\n";
    public static final String METHOD_WITH_ANNOTATION = "Name of the method with annotation is : ";
    public static final String ANNOTATION_IS = "Annotation is : ";
    public static final String ANNOTATION_TYPE = "Annotation type is : ";

    @AcademyInfo(year = YEAR_OF_FOUNDATION)
    public static void yearOfFoundation() {
        int year = YEAR_OF_FOUNDATION;
        System.out.println(FOUNDATION_YEAR + year);
    }

    @AcademyInfo(year = YEAR_OF_FOUNDATION)
    public static void yearsOfExistence() {
        int years = LocalDateTime.now().getYear() - YEAR_OF_FOUNDATION;
        System.out.println(ACADEMY_WAS_FOUNDED + years + YEARS_AGO);
    }

    public static void thisYear() {
        int year = LocalDateTime.now().getYear();
        System.out.println(THIS_YEAR + year);
    }

    public static void main(String[] args) throws ClassNotFoundException,
            InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        System.out.println(METHODS_WITH_ANNOTATIONS);

        Task72Run run = (Task72Run) Class.forName(Task72Run.class.getName()).getDeclaredConstructor().newInstance();
        for (Method method : run.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(AcademyInfo.class)) {
                method.setAccessible(true);
                Annotation annotation = method.getAnnotation(AcademyInfo.class);
                method.invoke(method);
                System.out.println(METHOD_WITH_ANNOTATION + method.getName());
                System.out.println(ANNOTATION_IS + annotation);
                System.out.println(ANNOTATION_TYPE + annotation.annotationType());
            }
        }
    }
}