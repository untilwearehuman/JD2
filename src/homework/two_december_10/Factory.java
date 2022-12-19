package homework.two_december_10;

import java.util.Random;

public class Factory implements Runnable {

    public static final String INTERRUPTED_MESSAGE = "Thread %s was interrupted%n";
    public static final String GET_TIME_PASSED = " getTimePassed : ";
    private RobotPartsWasteDump partsWasteDump;

    public Factory(RobotPartsWasteDump partsWasteDump) {
        this.partsWasteDump = partsWasteDump;
    }

    @Override
    public void run() {
        partsWasteDump.setInterrupt(false);
        while (partsWasteDump.getTimePassed() < partsWasteDump.getTimeOfHundredNights()) {
            try {
                for (int i = 0; i < (new Random().nextInt(4) + 1); i++) {
                    partsWasteDump.throwPartToDump();
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

        //System.out.println("FACTORY : " + Thread.currentThread().getName() + " Parts at the Waste Dump " + partsWasteDump.getPartList());
        System.out.println(Thread.currentThread().getName() + GET_TIME_PASSED + partsWasteDump.getTimePassed());

    }
}
