package chapter3.item14;

/**
 * author : haedoang
 * date : 2022/02/25
 * description :
 */
public class Student implements Comparable<Student> {
    private String name;
    private double avgScore;

    public Student(String name, double avgScore) {
        this.name = name;
        this.avgScore = avgScore;
    }

    @Override
    public int compareTo(Student o) {
        int result = Double.compare(avgScore, o.avgScore);
        if (result == 0) {
            result = String.CASE_INSENSITIVE_ORDER.compare(name, o.name);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", avgScore=" + avgScore +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getAvgScore() {
        return avgScore;
    }
}
