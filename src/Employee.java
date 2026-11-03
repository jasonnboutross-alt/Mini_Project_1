import java.time.LocalDate;

public class Employee {

    private int id;
    private String name;
    private double salary;
    private LocalDate birthDate;
    private String gender;
    private String department;
    private boolean fullTime;

    public Employee(int id, String name, double salary, LocalDate birthDate,
                    String gender, String department, boolean fullTime) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.birthDate = birthDate;
        this.gender = gender;
        this.department = department;
        this.fullTime = fullTime;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }
    public boolean isFullTime() { return fullTime; }
}