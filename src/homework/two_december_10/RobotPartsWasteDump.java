package homework.two_december_10;

import java.util.List;
import java.util.Random;

public class RobotPartsWasteDump {

    public static final String[] ROBOT_PARTS = {"Head", "Body", "Left hand", "Right hand", "Left leg", "Right leg",
            "CPU", "RAM", "HDD"};
    public static final int ONE_NIGHT_LENGTH = 100;
    public static final int TIME_OF_100_NIGHTS = 10000;
    public static final String NIGHTS = " nights";
    public static final String TIME_PASSED = "TIME PASSED ";
    private volatile int timePassed = 0;
    private volatile String collectedPart;
    private final List<String> partList;

    public RobotPartsWasteDump(List<String> partList) {
        this.partList = partList;
    }

    public String getCollectedPart() {
        return collectedPart;
    }

    public List<String> getPartList() {
        return partList;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public synchronized void throwPartToDump() {
        if (timePassed < TIME_OF_100_NIGHTS) {
            partList.add(ROBOT_PARTS[new Random().nextInt(ROBOT_PARTS.length)]);

            timePassed = timePassed + ONE_NIGHT_LENGTH;
            System.out.println(TIME_PASSED + timePassed / ONE_NIGHT_LENGTH + NIGHTS);
        }
        notifyAll();
    }

    public synchronized void takePartFromDump() {
        if (timePassed < TIME_OF_100_NIGHTS) {
            while (getPartList().size() < 1) {

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            collectedPart = partList.get(0);
            partList.remove(0);
        }
    }
}