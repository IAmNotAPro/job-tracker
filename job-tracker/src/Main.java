import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static List<JobApplication> applications;

    public static void main(String[] args) {
        List<JobApplication> applications = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean running = true;
        int choice = 0;

        // Loop through menu until user picks quit
        //    1) Add an application
        //    2) List applications
        //    3) Quit
        while(running) {
            System.out.println(); // Line buffer
            System.out.println("1) Add Job Application");
            System.out.println("2) List Job Application");
            System.out.println("3) Quit");
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
                    applications.add(addJobApplication(input, applications));
                    System.out.println("Job Application Added");
                    break;
                case 2:
                    // List job applications
                    listJobApplications(applications);
                    if(applications.isEmpty()){
                        System.out.println("No Job Application has been added");
                        break;
                    }
                    System.out.println("Job Application Listed");
                    break;
                case 3:
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

    public static JobApplication addJobApplication(Scanner scanner, List<JobApplication> applications) {

        System.out.print("Company Name: ");
        String companyName = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        System.out.print("Date Applied (YYYY-MM-DD): ");
        LocalDate dateApplied = LocalDate.parse(scanner.nextLine());

        System.out.print("Job Status: ");
        String statusInput = scanner.nextLine();
        Status status = Status.valueOf(statusInput.toUpperCase()); // Because Status is an enum, must convert the string input to enum

        System.out.print("Job Notes: ");
        String notes = scanner.nextLine();

        return new JobApplication(companyName, role, dateApplied, status, notes);
    }

    public static void listJobApplications(List<JobApplication> applications){
        for(JobApplication application: applications){
            System.out.println();

            System.out.println("Company Name: " + application.getCompany());
            System.out.println("Role: " + application.getRole());
            System.out.println("Date Applied: " + application.getDateApplied());
            System.out.println("Job Status: " + application.getStatus());
            System.out.println("Job Notes: " + application.getNotes());
        }
    }


}
