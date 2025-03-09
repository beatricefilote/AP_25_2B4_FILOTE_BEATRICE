public class Main {
    public static void main(String[] args) {

        String[] graphArgs = {"10", "4"};
        GraphGenerator.main(graphArgs);

        Student student = new Student("Bea");
        System.out.println(student.getName());
        student.setName("Beaaaaa");
        System.out.println(student.getName());


        Project project = new Project("Proiect");
        System.out.println(project.getName());
        project.setName("Proieeeeeect");
        System.out.println(project.getName());

    }
}