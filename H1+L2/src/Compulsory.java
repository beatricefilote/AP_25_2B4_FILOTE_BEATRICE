import java.time.LocalDate;

class Student {
    //var:
    private String name;
    private LocalDate birthdate;
    private Long regNumber;

    //constructorii:
    public Student(String name) {
        this.name = name;
    }
    public Student(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public Student(Long regNumber) {
        this.regNumber = regNumber;
    }

    //getteri si setteri:
    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Long getRegNumber() {
        return regNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }
}
class Project {

    //var:
    private String name;
    private ProjectType type;

    //constructorii:

    public Project(String name) {
        this.name = name;
    }

    public Project(ProjectType type) {
        this.type = type;
    }

    //getteri si setteri:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }
}
enum ProjectType {
    THEORETICAL,
    PRACTICAL
}