package study.heejin.chapter2.item8;

public class Adult {

    public void cleanRoom(int numberOfJunkPiles) {

        try (Room myRoom = new Room(numberOfJunkPiles)) {
            System.out.println("Adult 방 청소 완료!");
        }
    }
}
