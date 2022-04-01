package me.doyoung.studyeffectivejava.chapter5.item31;

import java.util.HashSet;
import java.util.Set;

// 코드 30-2의 제네릭 union 메서드에 와일드카드 타입을 적용해 유연성을 높였다. (185-186쪽)
public class Union {
    public static <E> Set<E> union(Set<? extends E> s1,
                                   Set<? extends E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    // 향상된 유연성을 확인해주는 맛보기 프로그램 (185쪽)
    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        integers.add(1); 
        integers.add(3); 
        integers.add(5); 

        Set<Double> doubles =  new HashSet<>();
        doubles.add(2.0); 
        doubles.add(4.0); 
        doubles.add(6.0); 

        Set<Number> numbers = union(integers, doubles);

//      // 코드 31-6 자바 7까지는 명시적 타입 인수를 사용해야 한다. (186쪽)
//      Set<Number> numbers = Union.<Number>union(integers, doubles);

        System.out.println(numbers);
    }
}
