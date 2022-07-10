package me.doyoung.studyeffectivejava.chapter11.item84;

// 코드 84-1 끔찍한 CountDownLatch 구현 - 바쁜 대기 버전! (447쪽)
public class SlowCountDownLatch {
    private int count;

    public SlowCountDownLatch(int count) {
        if (count < 0)
            throw new IllegalArgumentException(count + " < 0");
        this.count = count;
    }

    public void await() {
        while (true) {
            synchronized(this) {
                if (count == 0)
                    return;
            }
        }
    }
    public synchronized void countDown() {
        if (count != 0)
            count--;
    }
}
