public class HelpDesk {
    private final String stationName;
    public final QueueManager qm;

    public HelpDesk(String stationName) {
        this.stationName = stationName;
        this.qm = QueueManager.getInstance();
    }

    public void callNext() {
        int num = qm.callNextNum();

        System.out.println(stationName + " called queue number: " + num);
    }

    public void resetQueue(int num) {
        try {
            qm.reset(num);

            System.out.println(stationName + " reset the queue number to " + num);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public String getStationName() {
        return stationName;
    }
}