package study.heejin.chapter12.item87;

import java.io.Serializable;

/**
 * 기본 직렬화 형태에 적합하지 않은 클래스
 */
public final class StringList implements Serializable {
    private static final long serialVersionUID = 9025593466529759437L;

    private int size = 0;
    private Entry head = null;

    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }
}
