public class OnlineMonitor implements Runnable {
    private final QueueManager qm;
    private volatile boolean isRunning = false;
    private Thread monitorThread;

    public OnlineMonitor() {
        qm = QueueManager.getInstance();
    }

    public synchronized void startMonitoring() {
        if (isRunning) {
            System.out.println("\nMonitor is already running.");
            return;
        }

        isRunning = true;
        monitorThread = new Thread(this);
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    public synchronized void stop() {
        isRunning = false;

        if (monitorThread != null) {
            monitorThread.interrupt();
        }
        System.out.println("\nOnline monitoring stopped.");
    }

    @Override 
    public void run() {
        int lastNum = -1;

        while (isRunning) {
            int curr = qm.getCurrNum();

            if (curr != lastNum) {
                System.out.println("\n[ONLINE MONITOR] Current queue number: " + curr);

                lastNum = curr;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                if(!isRunning) {
                    break;
                }
            }
        }
    }
}