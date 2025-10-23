class Employee {
    protected String name;
    protected int age;
    protected double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Salary: $" + salary);
    }
}

class Developer extends Employee {
    protected String programmingLanguage;

    public Developer(String name, int age, double salary, String programmingLanguage) {
        super(name, age, salary);
        this.programmingLanguage = programmingLanguage;
    }

    public void showDeveloperInfo() {
        displayInfo();
        System.out.println("Programming Language: " + programmingLanguage);
    }
}

class SeniorDeveloper extends Developer {
    private int yearsOfExperience;

    public SeniorDeveloper(String name, int age, double salary, String programmingLanguage, int yearsOfExperience) {
        super(name, age, salary, programmingLanguage);
        this.yearsOfExperience = yearsOfExperience;
    }

    public void showSeniorDevInfo() {
        showDeveloperInfo();
        System.out.println("Years of Experience: " + yearsOfExperience);
    }
}

class Manager extends Employee {
    private String department;

    public Manager(String name, int age, double salary, String department) {
        super(name, age, salary);
        this.department = department;
    }

    public void showManagerInfo() {
        displayInfo();
        System.out.println("Department: " + department);
    }
}

interface TeamLead {
    void leadTeam();
}

interface ProjectCoordinator {
    void coordinateProject();
}

class TechLead extends Manager implements TeamLead, ProjectCoordinator {
    private String projectName;

    public TechLead(String name, int age, double salary, String department, String projectName) {
        super(name, age, salary, department);
        this.projectName = projectName;
    }

    @Override
    public void leadTeam() {
        System.out.println(name + " is leading the team for project: " + projectName);
    }

    @Override
    public void coordinateProject() {
        System.out.println(name + " is coordinating the project: " + projectName);
    }

    public void showTechLeadInfo() {
        showManagerInfo();
        System.out.println("Project: " + projectName);
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
        Developer dev = new Developer("Alice", 28, 70000, "Java");
        dev.showDeveloperInfo();

        SeniorDeveloper seniorDev = new SeniorDeveloper("Bob", 35, 90000, "Python", 10);
        seniorDev.showSeniorDevInfo();

        Manager mgr = new Manager("Carol", 40, 100000, "IT");
        mgr.showManagerInfo();

        TechLead lead = new TechLead("David", 38, 110000, "Development", "Project Apollo");
        lead.showTechLeadInfo();
        lead.leadTeam();
        lead.coordinateProject();
    }
}
