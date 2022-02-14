package chapter2.item8;

/**
 * author : haedoang
 * date : 2022/02/14
 * description :
 */
class CleanerTest {

    public static void main(String[] args) {
        new Room(100);
        System.out.println("cleaner는 객체 close를 보장하지 않는다.");

        System.out.println("================================");
        try (Room room = new Room(200)) {
            System.out.println("try-with-resource: 객체 close가 보장된다.");
        }
    }

}