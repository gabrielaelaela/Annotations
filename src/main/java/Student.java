import java.util.Arrays;

@Name("Student")
public class Student {
    @Name("testGrade")
    private int[] grades;
    @Name("studentName")
    public String name;
    @Name("studentName1")
    public String surname;

    public String sth;

    public Student(String name) {
        this.name = name;
        grades = new int[10];
        Arrays.fill(grades, 0);
    }

    public void addGrade(int grade) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == 0) {
                grades[i] = grade;
                break;
            }
        }
    }
}
