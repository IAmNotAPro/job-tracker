import javax.swing.plaf.synth.SynthTableUI;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    private final static Scanner input = new Scanner(System.in);
    private static List<JobApplication> applications;

    public static void main(String[] args) {
        applications = new ArrayList<>();
        boolean running = true;

        // Loop through menu until user picks quit
        //    1) Add an application
        //    2) List applications
        //    3) Change status
        //    4) Quit
        while(running) {
            switch(menu(input)){
                case 1:
                    // Add job application
                    applications.add(addJobApplication(input));
                    System.out.println("Job Application Added");
                    break;
                case 2:
                    // List job applications
                    listJobApplications(applications);
                    if(applications.isEmpty()){
                        System.out.println("No Job Application has been added");
                        break;
                    }
                    System.out.println("Job Application(s) Listed");
                    break;
                case 3:
                    // Change Job Status
                    System.out.print("Enter name of Company you're wanting to change status for: ");
                    String company = input.nextLine();
                    changeApplicationStatus(company);
                    break;
                case 4:
                    // Quit
                    System.out.println("Bye!");
                    running = false;
                    break;
                default:
                    System.out.print("Invalid choice. Try again.");
                    break;

            }
        }
    }

    public static JobApplication addJobApplication(Scanner scanner) {

        System.out.print("Company Name: ");
        String companyName = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        LocalDate dateApplied = LocalDate.now(); // Filler content
        LocalDate date = handleDate(input, dateApplied);

        System.out.print("Job Status: ");
        Status currentStatus = Status.APPLIED;
        Status status = handleStatus(input, currentStatus);

        System.out.print("Job Notes: ");
        String notes = scanner.nextLine();

        return new JobApplication(companyName, role, date, status, notes);
    }

    public static void listJobApplications(List<JobApplication> applications){
        applications.sort(Comparator.comparing(JobApplication::getDateApplied).reversed()); // Sorts by date applied, newest first
        for(JobApplication application: applications){
            System.out.println();

            System.out.println("Company Name: " + application.getCompany());
            System.out.println("Role: " + application.getRole());
            System.out.println("Date Applied: " + application.getDateApplied());
            System.out.println("Job Status: " + application.getStatus());
            System.out.println("Job Notes: " + application.getNotes());
        }
    }

    public static void changeApplicationStatus(String companyName){
        for(JobApplication application: applications) {
            if (application.getCompany().equalsIgnoreCase(companyName)) {
                System.out.print("New status: ");
                String statInput = input.nextLine();
                Status newStatus = Status.valueOf(statInput.toUpperCase());
                application.setStatus(newStatus);
                System.out.print("Job status changed!");
                return;
            }
        }
        System.out.println("Job not found");
    }

    public static Integer menu(Scanner input){ // Error handling for the menu
        System.out.println(); // Line buffer
        System.out.println("1) Add Job Application");
        System.out.println("2) List Job Applications");
        System.out.println("3) Change Application Status");
        System.out.println("4) Quit");
        System.out.print("Enter your choice: ");
        boolean needInput = true;
        int choice = 0;

        while (needInput) {
            try {
                choice = input.nextInt();
                input.nextLine();
                needInput = false;
            } catch (InputMismatchException e) {
                System.out.print("Invalid. Enter your choice: ");
                input.nextLine();
            }
        }
        return choice;
    }

    public static LocalDate handleDate(Scanner scanner, LocalDate date){ // Error handling for date input
        boolean validDate = false;
        while(!validDate) {
            try {
                System.out.print("Date Applied (YYYY-MM-DD): ");
                date = LocalDate.parse(scanner.nextLine());
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.print("Please enter a valid date (YYYY-MM-DD): ");
            }
        }
        return date;
    }

    public static Status handleStatus(Scanner scanner, Status status){
        boolean validStatus = false;
        while(!validStatus) {
            try {
                String statusInput = scanner.nextLine();
                status = Status.valueOf(statusInput.toUpperCase()); // Because Status is an enum, must convert the string input to enum
                validStatus = true;
            } catch (IllegalArgumentException e) {
                System.out.print("Please enter a valid status: ");
            }
        }
        return status;
    }


}
