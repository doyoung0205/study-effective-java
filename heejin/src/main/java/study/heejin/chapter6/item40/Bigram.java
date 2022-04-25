package study.heejin.chapter6.item40;

import java.util.HashSet;
import java.util.Set;

public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    // equals를 재정의한 것이 아니라 다중정의한 것이다!
    public boolean equals(Bigram b) {
        return b.first == first && b.second == second;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Bigram)) {
//            return false;
//        }
//        Bigram bigram = (Bigram) o;
//        return first == bigram.first && second == bigram.second;
//    }

    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
        }
        System.out.println(s.size());
    }
}
