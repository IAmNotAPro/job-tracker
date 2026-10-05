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
        int choice = 0;

        // Loop through menu until user picks quit
        //    1) Add an application
        //    2) List applications
        //    3) Change status
        //    4) Quit
        while(running) {
            System.out.println(); // Line buffer
            System.out.println("1) Add Job Application");
            System.out.println("2) List Job Applications");
            System.out.println("3) Change Application Status");
            System.out.println("4) Quit");
            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice");
                input.nextLine();
            }

            switch(choice){
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
                    System.out.print("Enter name of Job you're wanting to change status for: ");
                    String job = input.nextLine();
                    changeApplicationStatus(job);
                    System.out.print("Job status changed!");
                    break;
                case 4:
                    // Quit
                    System.out.println("Bye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
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
        System.out.print("Date Applied (YYYY-MM-DD): ");
        try {
            dateApplied = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid date");
            dateApplied = LocalDate.parse(scanner.nextLine());
        }

        System.out.print("Job Status: ");
        String statusInput = scanner.nextLine();
        Status status = Status.valueOf(statusInput.toUpperCase());  // Because Status is an enum, must convert the string input to enum

        System.out.print("Job Notes: ");
        String notes = scanner.nextLine();

        return new JobApplication(companyName, role, dateApplied, status, notes);
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

    public static void changeApplicationStatus(String jobName){
        for(JobApplication application: applications) {
            if (application.getRole().equals(jobName)) {
                System.out.print("New status: ");
                String statInput = input.nextLine();
                Status newStatus = Status.valueOf(statInput.toUpperCase());
                application.setStatus(newStatus);
            }
        }
    }


}
