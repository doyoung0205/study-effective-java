package study.heejin.chapter2.item8;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable {

    private static final Cleaner cleaner = Cleaner.create();

    // 청소가 필요한 자원. 절대 Room을 참조하면 안된다. (순환참조 X)
    private static class State implements Runnable {
        int numberOfJunkPiles; // 방(Room) 안의 쓰레기 수

        State(int numberOfJunkPiles) {
            this.numberOfJunkPiles = numberOfJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("- Cleaner가 방 청소");
            numberOfJunkPiles = 0;
        }
    }

    // 방의 상태. cleanable과 공유한다.
    private final State state;
    private final Cleaner.Cleanable cleanable;

    public Room(int numberOfJunkPiles) {
        this.state = new State(numberOfJunkPiles);
        this.cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }

    public int getNumberOfJunkPiles() {
        return state.numberOfJunkPiles;
    }
}
