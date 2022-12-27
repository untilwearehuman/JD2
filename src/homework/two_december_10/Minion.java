package homework.two_december_10;

import java.util.List;
import java.util.Random;

public class Minion implements Runnable {

    public static final int BOUND = 4;
    public static final String GET_TIME_PASSED = " getTimePassed : ";
    public static final String ASSEMBLED = " assembled ";
    public static final String ROBOTS = " robots";
    public static final String INTERRUPTED_MESSAGE = "Thread %s was interrupted%n";
    public static final int PARTS_BOUND = 4;
    public static final int SLEEP_MILLIS = 100;
    public static final String TOOK = "%s took %s%n";
    public static final String NIGHTS_N = "%s%s%d nights%n";
    private RobotPartsWasteDump partsWasteDump;
    private List<String> professorPartList;
    private int robotsAssembled;

    public Minion(RobotPartsWasteDump partsWasteDump, List<String> professorPartList) {
        this.partsWasteDump = partsWasteDump;
        this.professorPartList = professorPartList;
    }

    public int getRobotsAssembled() {
        return robotsAssembled;
    }

    @Override
    public void run() {
        while (partsWasteDump.getTimePassed() < RobotPartsWasteDump.TIME_OF_100_NIGHTS) {
            try {

                if (partsWasteDump.getPartList().size() >= PARTS_BOUND) {
                    for (int i = 0; i < (new Random().nextInt(BOUND) + 1); i++) {
                        partsWasteDump.takePartFromDump();
                        professorPartList.add(partsWasteDump.getCollectedPart());

                        System.out.printf(TOOK, Thread.currentThread().getName(), partsWasteDump.getCollectedPart());
//                        System.out.printf(NIGHTS_N, Thread.currentThread().getName(), GET_TIME_PASSED,
//                                partsWasteDump.getTimePassed() / RobotPartsWasteDump.ONE_NIGHT_LENGTH);
                    }
                } else {
                    for (int i = 0; i < (new Random().nextInt(partsWasteDump.getPartList().size() + 1)); i++) {
                        partsWasteDump.takePartFromDump();
                        professorPartList.add(partsWasteDump.getCollectedPart());
                        System.out.printf(TOOK, Thread.currentThread().getName(), partsWasteDump.getCollectedPart());
//                        System.out.printf(NIGHTS_N, Thread.currentThread().getName(), GET_TIME_PASSED,
//                                partsWasteDump.getTimePassed() / RobotPartsWasteDump.ONE_NIGHT_LENGTH);
                    }
                }

                Thread.sleep(SLEEP_MILLIS);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf(NIGHTS_N, Thread.currentThread().getName(), GET_TIME_PASSED,
                partsWasteDump.getTimePassed() / RobotPartsWasteDump.ONE_NIGHT_LENGTH);

        CalculateMaxNumberRobotsToAssemble robotsToAssemble = new CalculateMaxNumberRobotsToAssemble();
        robotsAssembled = robotsToAssemble.getRobotsQuantity(professorPartList);

        System.out.println(Thread.currentThread().getName() + ASSEMBLED + robotsAssembled + ROBOTS);

    }
}