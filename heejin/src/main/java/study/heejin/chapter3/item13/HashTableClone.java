package study.heejin.chapter3.item13;

import java.util.Arrays;

public class HashTableClone implements Cloneable {
    private Entry[] buckets = new Entry[5];

    public void put(int index, String value) {
        if (buckets.length < index) {
            throw new IllegalArgumentException();
        }
        buckets[index] = new Entry(index, value, null);

        if (index == 0 && buckets[index+1] != null) {
            buckets[index].next = buckets[index+1];
        }
        if (index >= 1) {
            buckets[index-1].next = buckets[index];
        }
    }

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Entry deepCopy() {
            // 방법 1) 재귀 호출 때문에 리스트의 원소 수만큼 스택 프레임을 소비하여, 리스트가 길면 스택 오버플로를 일으킬 위험이 있다.
            // return new Entry(key, value, next == null ? null : next.deepCopy());

            // 방법 2) 재귀 호출 대신 반복자를 써서 순회하는 방향으로 수정
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next) {
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            }
            return result;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public HashTableClone clone() {
        try {
            HashTableClone result = (HashTableClone) super.clone();
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    result.buckets[i] = buckets[i].deepCopy();
                }
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Object get(int index) {
        return buckets[index].value;
    }

    public Object getNext(int index) {
        return buckets[index].next.value;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "buckets=" + Arrays.toString(buckets) +
                '}';
    }
}
