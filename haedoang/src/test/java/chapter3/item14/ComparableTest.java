package chapter3.item14;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/02/25
 * description :
 */
public class ComparableTest {
    @Test
    @DisplayName("Comparable은 동치성 비교에 더해 순서까지 비교할 수 있다.")
    public void comparableTest() {
        // given
        final ArrayList<Student> students = Lists.newArrayList(
                new Student("아리아나그란데", 94.8),
                new Student("레이디가가", 84.2),
                new Student("두아리파", 96.2)
        );
        // when
        Collections.sort(students);

        // then
        assertThat(students).extracting(Student::getName)
                .containsExactly("레이디가가", "아리아나그란데", "두아리파");
    }

    @Test
    @DisplayName("객체참조 필드 여러 개인 경우 우선순위를 정해주어야 한다")
    public void comparableTestWhenMultiFields() {
        // given
        final ArrayList<Student> students = Lists.newArrayList(
                new Student("아리아나그란데", 94.8),
                new Student("레이디가가", 84.2),
                new Student("위켄드", 96.2),
                new Student("두아리파", 96.2)
        );
        // when
        Collections.sort(students);

        // then
        assertThat(students).extracting(Student::getName)
                .containsExactly("레이디가가", "아리아나그란데", "두아리파", "위켄드")
                .as("sort1. avgScore, sort2. name");
    }

    @Test
    @DisplayName("Comparator를 사용한 정렬")
    public void comparatorTest() {
        final ArrayList<Student> students = Lists.newArrayList(
                new Student("아리아나그란데", 94.8),
                new Student("레이디가가", 84.2),
                new Student("위켄드", 96.2),
                new Student("두아리파", 96.2)
        );

        // when
        students.sort(Comparator.comparingDouble(Student::getAvgScore)
                .thenComparing(Student::getName));

        // then
        assertThat(students).extracting(Student::getName)
                .containsExactly("레이디가가", "아리아나그란데", "두아리파", "위켄드")
                .as("sort1. avgScore, sort2. name");
    }

    @Test
    @DisplayName("hashSet 기능 테스트")
    public void hashSetTest() {
        // given
        final HashSet<BigDecimal> hashSet = new HashSet<>();

        // when
        hashSet.add(new BigDecimal("1.0"));
        hashSet.add(new BigDecimal("1.00"));

        // then
        assertThat(hashSet).hasSize(2);
        assertThat(new BigDecimal("1.0").equals(new BigDecimal("1.00")))
                .isFalse();
    }

    @Test
    @DisplayName("treeSet은 정렬을 지원하기 때문에 compareTo() 동작 테스트")
    public void treeSetTest() {
        // given
        final TreeSet<BigDecimal> treeSet = new TreeSet<>();

        // when
        treeSet.add(new BigDecimal("1.0"));
        treeSet.add(new BigDecimal("1.00"));

        // then
        assertThat(treeSet).hasSize(1)
                .as("treeSet은 정렬을 자동으로 하는데 이때 compareTo가 사용된다. 그래서 원소는 1개가 저장이 된다.");
        assertThat(new BigDecimal("1.0").compareTo(new BigDecimal("1.00")))
                .isEqualTo(0);
    }
}
