package study.heejin.chapter9.item60;

public class IntChange {

    // 정수 타입을 사용한 해법
    public static void main(String[] args) {
        int itemsBought = 0;
        int funds = 100;

        for (int price = 10; funds >= price; price += 10) {
            funds -= price;
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(센트): " + funds);
    }
}
