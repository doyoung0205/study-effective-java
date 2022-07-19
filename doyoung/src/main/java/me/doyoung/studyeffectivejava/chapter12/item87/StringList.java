package me.doyoung.studyeffectivejava.chapter12.item87;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 코드 87-3 합리적인 커스텀 직렬화 형태를 갖춘 StringList (462-463쪽)
public final class StringList implements Serializable {
    private transient int size   = 0;
    private transient Entry head = null;

    // 이제는 직렬화되지 않는다.
    private static class Entry {
        String data;
        Entry  next;
        Entry  previous;
    }

    // 지정한 문자열을 이 리스트에 추가한다.
    public final void add(String s) {  }

    /**
     * 이 {@code StringList} 인스턴스를 직렬화한다.
     *
     * @serialData 이 리스트의 크기(포함된 문자열의 개수)를 기록한 후
     * ({@code int}), 이어서 모든 원소를(각각은 {@code String})
     * 순서대로 기록한다.
     */
    private void writeObject(ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);

        // 모든 원소를 올바른 순서로 기록한다.
        for (Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }

    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int numElements = s.readInt();

        // 모든 원소를 읽어 이 리스트에 삽입한다.
        for (int i = 0; i < numElements; i++)
            add((String) s.readObject());
    }

    // 나머지 코드는 생략
}
