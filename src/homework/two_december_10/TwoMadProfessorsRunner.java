package homework.two_december_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoMadProfessorsRunner {

    public static final String[] ROBOT_PARTS = {"Head", "Body", "Left hand", "Right hand", "Left leg", "Right leg",
            "CPU", "RAM", "HDD"};
    public static final int INITIAL_FACTORY_WASTE_DUMP_CAPACITY = 20;
    public static final String INITIAL_PART_LIST = "INITIAL PART LIST";
    public static final String WASTE_DUMP = "WASTE DUMP";
    public static final String PROFESSOR_1 = "Professor 1";
    public static final String PROFESSOR_2 = "Professor 2";
    public static final String THE_WINNER_IS = "The winner is ";
    public static final String NUMBER_OF_ROBOTS_ASSEMBLED_IS = ". Number of robots assembled is ";
    public static final String DRAW_ROBOTS_ASSEMBLED = "DRAW! All the professors assembled same number of robots : ";

    public static void main(String[] args) {

        List<String> initialPartsList = new ArrayList<>(INITIAL_FACTORY_WASTE_DUMP_CAPACITY);
        for (int i = 0; i < INITIAL_FACTORY_WASTE_DUMP_CAPACITY; i++) {
            initialPartsList.add(ROBOT_PARTS[new Random().nextInt(ROBOT_PARTS.length)]);
        }

        System.out.println(INITIAL_PART_LIST);
        System.out.println(initialPartsList);

        List<String> professor1PartList = new ArrayList<>();
        List<String> professor2PartList = new ArrayList<>();


        RobotPartsWasteDump partsWasteDump = new RobotPartsWasteDump(initialPartsList);

        Factory factory = new Factory(partsWasteDump);

        Minion minion1 = new Minion(partsWasteDump, professor1PartList);
        Minion minion2 = new Minion(partsWasteDump, professor2PartList);

        Thread thread1 = new Thread(factory);

        thread1.setName(WASTE_DUMP);

        thread1.start();

        Thread thread2 = new Thread(minion1);
        Thread thread3 = new Thread(minion2);

        thread2.setName(PROFESSOR_1);
        thread3.setName(PROFESSOR_2);

        thread2.start();
        thread3.start();

        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (minion1.getRobotsAssembled() > minion2.getRobotsAssembled()) {
            System.out.println(THE_WINNER_IS + thread2.getName() +
                    NUMBER_OF_ROBOTS_ASSEMBLED_IS + minion1.getRobotsAssembled());
        } else if (minion1.getRobotsAssembled() == minion2.getRobotsAssembled()) {
            System.out.println(DRAW_ROBOTS_ASSEMBLED + minion1.getRobotsAssembled());
        } else {
            System.out.println(THE_WINNER_IS + thread3.getName() +
                    NUMBER_OF_ROBOTS_ASSEMBLED_IS + minion2.getRobotsAssembled());
        }

    }
}