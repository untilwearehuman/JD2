package homework.two_december_10;

import java.util.List;
import java.util.Random;

public class Minion implements Runnable {

    public static final int BOUND = 4;
    public static final String GET_TIME_PASSED = " getTimePassed : ";
    public static final String ASSEMBLED = " assembled ";
    public static final String ROBOTS = " robots";
    public static final String INTERRUPTED_MESSAGE = "Thread %s was interrupted%n";
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
        partsWasteDump.setInterrupt(false);
        while (partsWasteDump.getTimePassed() < partsWasteDump.getTimeOfHundredNights()) {
            try {

                if (partsWasteDump.getPartList().size() >= 4) {
                    for (int i = 0; i < (new Random().nextInt(BOUND) + 1); i++) {
                        partsWasteDump.takePartFromDump();
                        professorPartList.add(partsWasteDump.getCollectedPart());

//                        System.out.println(Thread.currentThread().getName() + " took " + partsWasteDump.getCollectedPart());

                    }
                } else {
                    for (int i = 0; i < (new Random().nextInt(partsWasteDump.getPartList().size() + 1)); i++) {
                        partsWasteDump.takePartFromDump();
                        professorPartList.add(partsWasteDump.getCollectedPart());
//                        System.out.println(Thread.currentThread().getName() + " took " + partsWasteDump.getCollectedPart());
                    }
                }

                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (partsWasteDump.getTimePassed() == partsWasteDump.getTimeOfHundredNights()) {
            partsWasteDump.setInterrupt(true);
            try {
                Thread.currentThread().interrupt();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.printf(INTERRUPTED_MESSAGE, Thread.currentThread().getName());
            }
        }

//        System.out.println("MINION : " + Thread.currentThread().getName() + " collected parts " + professorPartList);
        System.out.println(Thread.currentThread().getName() + GET_TIME_PASSED + partsWasteDump.getTimePassed());

        CalculateMaxNumberRobotsToAssemble robotsToAssemble = new CalculateMaxNumberRobotsToAssemble();
        robotsAssembled = robotsToAssemble.getRobotsQuantity(professorPartList);

        System.out.println(Thread.currentThread().getName() + ASSEMBLED + robotsAssembled + ROBOTS);

    }
}