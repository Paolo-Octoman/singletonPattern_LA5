public class QueueManager{
    private static final QueueManager instance = new QueueManager();
    private int currNum = 0;

    private QueueManager() {
        // Prevents other classes from creating another manager.
    }

    public static QueueManager getInstance() {
        return instance;
    }

    public synchronized int getCurrNum() {
        return currNum;
    }

    public synchronized int callNextNum() {
        currNum++;
        return currNum;
    }

    public synchronized void reset(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("\nQueue number cannot be negative!");
        }
        currNum = num;
    }
}