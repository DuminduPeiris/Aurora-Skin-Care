import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic clinic = new Clinic();
        AppointmentService service = new AppointmentService();

        // Main loop for user interaction
        while (true) {
            System.out.println("-".repeat(35));
            System.out.println("Welcome to Aurora Skin Care Clinic");
            System.out.println("-".repeat(35));
            System.out.println("Main Menu");
            System.out.println("1. Make an Appointment");
            System.out.println("2. View Appointments by Date");
            System.out.println("3. Search Appointment");
            System.out.println("4. Update an Appointment");
            System.out.println("5. Generate an Invoice");
            System.out.println("6. Exit");
            System.out.print("Choose the option buy selecting the NO : ");

            int option = question();

            // Consume newline

            switch (option) {
                case 1: // Make Appointment
                    service.createAppointment();
                    break;
                case 2: // View Appointments by Day
                    service.viewAppointments();
                    break;
                case 3: // Search Appointment
                    service.searchAppointments();
                    break;
                case 4: // Update Appointments
                    service.updateAppointment();
                    break;
                case 5: // Update Appointments
                    service.genarateInvoice();
                    break;
                case 6: // Exit
                   service.exit();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }

        }
    }

    public static int question(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                 return scanner.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please enter a valid Number");
                scanner.nextLine();
            }
        }

    }
}



