package me.doyoung.studyeffectivejava.chapter1.item7.reference;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class Phantom2 {
    public class LargeObjectFinalizer extends PhantomReference<Object> {

        public LargeObjectFinalizer(
                Object referent, ReferenceQueue<? super Object> q) {
            super(referent, q);
        }

        public void finalizeResources() {
            // free resources
            System.out.println("clearing ...");
        }
    }

    @Test
    public void main() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<LargeObjectFinalizer> references = new ArrayList<>();
        List<Object> largeObjects = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            Object largeObject = new Object();
            largeObjects.add(largeObject);
            references.add(new LargeObjectFinalizer(largeObject, referenceQueue));
        }

        largeObjects = null;
        System.gc();

        /* phantomReference가 ReferenceQueue에 들어갔는지 확인함 */
        Reference<?> referenceFromQueue;
        for (PhantomReference<Object> reference : references) {
            System.out.println(reference.isEnqueued());
        }

        while ((referenceFromQueue = referenceQueue.poll()) != null) {
            /* phantomReference 제거 전 수행할 업무를 처리함 */
            ((LargeObjectFinalizer) referenceFromQueue).finalizeResources();

            /* phantomReference 객체를 수동으로 clear() */
            referenceFromQueue.clear();
        }
    }
}
