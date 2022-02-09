package me.doyoung.studyeffectivejava.chapter1.item1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Lotto {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private final int lottoNumber;


    private Lotto(int lottoNumber) {
        if (!validate(lottoNumber)) {
            throw new IllegalArgumentException("잘못된 로또 번호 입니다.");
        }

        System.out.println("Lotto 객체 생성 lottoNumber = " + lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public static Lotto getInstance(int lottoNumber) {
        if (LottoCache.containsKey(lottoNumber)) {
            return LottoCache.get(lottoNumber);
        }

        return LottoCache.putAndGet(lottoNumber);
    }

    private static boolean validate(int lottoNumber) {
        return lottoNumber >= MIN && lottoNumber <= MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto target = (Lotto) o;
        return Objects.equals(lottoNumber, target.lottoNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lottoNumber);
    }

    private static class LottoCache {
        private static final Map<Integer, Lotto> c = new HashMap<>();

        public static Lotto get(int lottoNumber) {
            return c.get(lottoNumber);
        }

        public static Lotto putAndGet(int lottoNumber) {
            final Lotto lotto = new Lotto(lottoNumber);
            c.put(lottoNumber, lotto);
            return lotto;
        }

        public static boolean containsKey(int lottoNumber) {
            return c.containsKey(lottoNumber);
        }
    }
}
