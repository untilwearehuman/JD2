package homework.two_december_10;

import java.util.List;
import java.util.Random;

public class RobotPartsWasteDump {

    public static final String[] ROBOT_PARTS = {"Head", "Body", "Left hand", "Right hand", "Left leg", "Right leg",
            "CPU", "RAM", "HDD"};
    public static final int ONE_NIGHT_LENGHT = 100;
    private volatile long timePassed = 0;
    private final long timeOfHundredNights = 10_000L;

    private volatile String collectedPart;
    private final List<String> partList;
    private volatile boolean interrupt;

    public RobotPartsWasteDump(List<String> partList) {
        this.partList = partList;
    }

    public String getCollectedPart() {
        return collectedPart;
    }

    public List<String> getPartList() {
        return partList;
    }

    public long getTimePassed() {
        return timePassed;
    }

    public long getTimeOfHundredNights() {
        return timeOfHundredNights;
    }

    public boolean isInterrupt() {
        return interrupt;
    }

    public void setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
    }

    public synchronized void throwPartToDump() throws InterruptedException {
        if (timePassed < timeOfHundredNights) {
            if (!isInterrupt()) {
                partList.add(ROBOT_PARTS[new Random().nextInt(ROBOT_PARTS.length)]);

                timePassed = timePassed + ONE_NIGHT_LENGHT;
//            System.out.println("QUEUE : TIME PASSED " + timePassed);
            }
        }
        notifyAll();
    }

    public synchronized void takePartFromDump() {
        if (timePassed < timeOfHundredNights) {
            while (getPartList().size() < 1) {

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (!isInterrupt()) {
                collectedPart = partList.get(0);
                partList.remove(0);
            }
        }
    }
}