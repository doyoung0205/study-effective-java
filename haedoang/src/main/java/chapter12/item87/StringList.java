package chapter12.item87;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * author : haedoang
 * date : 2022/07/16
 * description :
 */
public class StringList implements Serializable {
    private transient int size = 0; // 기본 직렬화 대상이 아님을 정의
    private transient Entry head = null;

    private static class Entry {
        String data;
        Entry next;
        Entry previous;
    }


    public final void add(String s) {
        //TODO 지정한 문자열을 이 리스트에 추가한다
    }

    /**
     * 이 {@code StringList} 인스턴스를 직렬화한다
     *
     * @serialData 이 리스트의 크기를 기록한 후 ({@code int}), 이어서 모든 원소를 (각각은 {@code String}) 순서대로 기록한다.
     * @param s
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size);

        for (Entry e = head; e != null; e = e.next) {
            s.writeObject(e.data);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int numElements = s.readInt();

        for (int i = 0; i < numElements; i++) {
            add((String) s.readObject());
        }
    }
}
