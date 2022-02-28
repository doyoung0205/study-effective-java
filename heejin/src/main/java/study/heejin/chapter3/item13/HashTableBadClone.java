package study.heejin.chapter3.item13;

import java.util.Arrays;

public class HashTableBadClone implements Cloneable {
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
    public HashTableBadClone clone() {
        try {
            HashTableBadClone result = (HashTableBadClone) super.clone();
            result.buckets = buckets.clone();
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
