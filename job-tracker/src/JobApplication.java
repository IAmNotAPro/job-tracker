import java.time.LocalDate;

public class JobApplication {
    private String company;
    private String role;
    private LocalDate dateApplied;
    private Status status;
    private String notes;

    public JobApplication(String company, String role, LocalDate dateApplied, Status status, String notes) {
        this.company = company;
        this.role = role;
        this.dateApplied = dateApplied;
        this.status = Status.APPLIED;
        this.notes = notes;
    }

    // Getter methods
    public String getCompany() {
        return this.company;
    }

    public String getRole() {
        return this.role;
    }

    public LocalDate getDateApplied(){
        return this.dateApplied;
    }

    public Status string(){
        return this.status;
    }

    public String getNotes() { return this.notes; }

    public Status getStatus(){
        return this.status;
    }

    // Setter methods
    public void setStatus(Status status){ this.status = status; }

    public void setRole(String role){ this.role = role; }

    public void setDateApplied(LocalDate dateApplied){ this.dateApplied = dateApplied; }

    @Override
    public String toString(){
        // Return one readable line
        return "";
    }
}


