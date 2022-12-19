package homework.two_december_10.task70;

public class Man {

    private static final String MANS_IDENTITY = "Human";
    public static final String GREETINGS = "Hi, my name is ";

    private String name;
    private int age;
    private int height;

    public Man(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void sayingName() {
        System.out.println(GREETINGS + getName());
    }


}
