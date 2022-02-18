package study.heejin.chapter2.item7.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class ReferenceExample {

    private List<SoftReference<BigData>> softRefs = new LinkedList<>();
    private List<WeakReference<BigData>> weakRefs = new LinkedList<>();
    private List<BigData> strongRefs = new LinkedList<>();

    public void strongReferenceTest() {
        try {
            for (int i = 0; true; i++) {
                strongRefs.add(new BigData());
            }
        } catch (OutOfMemoryError ofm) { // out of memory 발생
            System.out.println("out of memory!");
        }
    }

    public void softReferenceTest() {
        try {
            for (int i = 0; true; i++) {
                softRefs.add(new SoftReference<>(new BigData()));
            }
        } catch (OutOfMemoryError ofm) { // out of memory 발생 하지 않음
            System.out.println("out of memory!");
        }
    }


    public void weakReferenceTest() {
        try {
            for (int i = 0; true; i++) {
                weakRefs.add(new WeakReference<>(new BigData()));
            }
        } catch (OutOfMemoryError ofm) { //out of memory 발생 하지 않음
            System.out.println("out of memory!");
        }
    }
}
