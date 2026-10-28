import java.util.*;

public class CentralizedSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        HelpDesk station1 = new HelpDesk("Help Desk 1");
        HelpDesk station2 = new HelpDesk("Help Desk 2");
        HelpDesk station3 = new HelpDesk("Help Desk 3");

        HelpDesk[] stations = {station1, station2, station3};

        QueueManager qm = QueueManager.getInstance();
        OnlineMonitor om = new OnlineMonitor();

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== WELCOME TO PAG-IBIG QUEUING SYSTEM ===");
            System.out.println("1. Call next number");
            System.out.println("2. Reset queue number");
            System.out.println("3. View current queue number");
            System.out.println("4. Start online monitoring");
            System.out.println("5. Stop online monitoring");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (!input.hasNextInt()) {
                System.out.println("\nPlease input a valid number!");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                case 2:
                    System.out.println("\nSelect a help desk station:");
                    System.out.println("1. Help Desk 1");
                    System.out.println("2. Help Desk 2");
                    System.out.println("3. Help Desk 3");
                    System.out.print("Station: ");

                    if (!input.hasNextInt()) {
                        System.out.println("\nInvalid station!");
                        input.nextLine();
                        break;
                    }

                    int stationSelection = input.nextInt();

                    if (stationSelection < 1 || stationSelection > 3) {
                        System.out.println("\nInvalid station selection!");
                        break;
                    }

                    HelpDesk station = stations[stationSelection - 1];

                    if (choice == 1) {
                        System.out.println("\nOption selected: Call next number");
                        station.callNext();
                    } else {
                        System.out.println("\nOption selected: Reset queue number");
                        System.out.print("Enter new queue number: ");

                        if (!input.hasNextInt()) {
                            System.out.println("\nInvalid queue number!");
                            input.nextLine();
                            break;
                        }

                        int newNum = input.nextInt();
                        station.resetQueue(newNum);
                    }
                    break;
                case 3:
                    System.out.println("\nOption selected: View current queue number");
                    System.out.println("Currently serving: " + qm.getCurrNum());
                    break;
                case 4:
                    om.startMonitoring();
                    break;
                case 5:
                    om.stop();
                    break;
                case 6:
                    System.out.println("\nOption selected: Exit");
                    om.stop();
                    isRunning = false;
                    System.out.println("System closed. Have a great day.");
                    break;
                default:
                    System.out.println("\nInvalid option!");
            }
        }

        input.close();
    }
}