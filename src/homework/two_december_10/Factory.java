package homework.two_december_10;

import java.util.Random;

public class Factory implements Runnable {

    public static final String INTERRUPTED_MESSAGE = "Thread %s was interrupted%n";
    public static final String GET_TIME_PASSED = " getTimePassed : ";
    public static final int BOUND = 4;
    public static final int SLEEP_MILLIS = 100;
    private RobotPartsWasteDump partsWasteDump;

    public Factory(RobotPartsWasteDump partsWasteDump) {
        this.partsWasteDump = partsWasteDump;
    }

    @Override
    public void run() {
        while (partsWasteDump.getTimePassed() < RobotPartsWasteDump.TIME_OF_100_NIGHTS) {
            try {
                for (int i = 0; i < (new Random().nextInt(BOUND) + 1); i++) {
                    partsWasteDump.throwPartToDump();
                }
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + GET_TIME_PASSED +
                partsWasteDump.getTimePassed() / RobotPartsWasteDump.ONE_NIGHT_LENGTH + " nights");

    }
}