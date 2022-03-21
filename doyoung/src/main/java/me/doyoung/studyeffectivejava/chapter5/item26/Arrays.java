package me.doyoung.studyeffectivejava.chapter5.item26;

public class Arrays {
    transient Object[] elementData; // non-private to simplify nested class access

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int size = 10;
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) java.util.Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

}
