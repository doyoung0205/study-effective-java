### item14 Comparable을 구현할지 고민하라
- Comparable 인터페이스는 순서가 있는 객체를 구현할 때 사용하는 믹스인 인터페이스이다
- Compareble 은 `compareTo()` 메서드를 가지고 있는데 규약은 아래와 같다(Comparable 객체를 구현했다고 가정한다, sgn: 부호함수)
  - sgn(x.compareTo(y)) == -sgn(y.compareTo(x))
  - x.compareTo(y) > 0 && y.compareTo(z) 이면 x.compareTo(z)
  - x.compareTo(y) == 0 이면 sgn(x.compareTo(z)) == sgn(x.compareTo(z))
  - x.compareTo(y) == 0 == x.equals(y), 이 권고를 지키지 않는 클래스는 명시해야 한다.
- Comparable은 확장한 클래스의 값 컴포넌트가 추가될 경우 구현할 수 없다. 
  ```java
  //parent
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
  }
  
  //child
  public class KoreanStudent extends Student implements Comparable<KoreanStudent> {
    private String schoolName;

    public KoreanStudent(String name, double avgScore, String schoolName) {
        super(name, avgScore);
        this.schoolName = schoolName;
    }
  }
  ```