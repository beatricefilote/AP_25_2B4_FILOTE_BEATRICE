//fac clasa parinte Person pt ca studentul si proful la baza sunt persoane
class Person {
    protected String name;
    protected String dateOfBirth;

    //constructor
    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
    //getteri
    public String getName() {
        return name;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
//e o clasa copil a clasei person si mosteneste metodele din person (override), dar are in plus registrationnumber
class Student extends Person {
    private int registrationNumber;

    public Student(String name, String dateOfBirth, int registrationNumber) {
        super(name, dateOfBirth); //folosesc super pt ca dateofbirth este deja in person
        this.registrationNumber = registrationNumber;
    }

    //getter
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    //fac verificari doar pe baza registernumberului
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return registrationNumber == student.registrationNumber;
    }
}


class Teacher extends Person {
    private String[] proposedProjects;
    private int projectCount;

    public Teacher(String name, String dateOfBirth, int maxProjects) {
        super(name, dateOfBirth);
        this.proposedProjects = new String[maxProjects];
        this.projectCount = 0;
    }
    //adaug proiecte
    public void proposeProject(String projectTitle) {
        if (projectCount < proposedProjects.length) {
            proposedProjects[projectCount++] = projectTitle;
        }
    }
//getter

    public String[] getProposedProjects() {
        return proposedProjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return name.equals(teacher.name);
    } //verific doar dupa nume
}


class Project {
    private String title;
    private Teacher proposer;
    //constructor
    public Project(String title, Teacher proposer) {
        this.title = title;
        this.proposer = proposer;
    }
    //getteri
    public String getTitle() {
        return title;
    }

    public Teacher getProposer() {
        return proposer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return title.equals(project.title);
    } //ac nume si ac prof
}

class Problem {
    private Student[] students;
    private Teacher[] teachers;
    private Project[] projects;
    private int studentCount, teacherCount, projectCount;

    public Problem(int maxStudents, int maxTeachers, int maxProjects) {
        students = new Student[maxStudents];
        teachers = new Teacher[maxTeachers];
        projects = new Project[maxProjects];
        studentCount = teacherCount = projectCount = 0;
    }

    public void addStudent(Student student) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) return;
        }
        students[studentCount++] = student;
    }

    public void addTeacher(Teacher teacher) {
        for (int i = 0; i < teacherCount; i++) {
            if (teachers[i].equals(teacher)) return;
        }
        teachers[teacherCount++] = teacher;
    }

    public void addProject(Project project) {
        for (int i = 0; i < projectCount; i++) {
            if (projects[i].equals(project)) return;
        }
        projects[projectCount++] = project;
    }

    public Person[] getAllPersons() {
        Person[] persons = new Person[studentCount + teacherCount];
        System.arraycopy(students, 0, persons, 0, studentCount);
        System.arraycopy(teachers, 0, persons, studentCount, teacherCount);
        return persons;
    }

    public void allocateProjects() {
        for (int i = 0; i < studentCount && i < projectCount; i++) {
            System.out.println("Student " + students[i].getName() + " gets project " + projects[i].getTitle());
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem(10, 5, 10);

        Teacher t1 = new Teacher("Alex", "2001-04-05", 2);
        problem.addTeacher(t1);
        Project p1 = new Project("Programare Avansata", t1);
        problem.addProject(p1);

        Student s1 = new Student("Bea", "2004-05-06", 638);
        problem.addStudent(s1);

        problem.allocateProjects();
    }

}