package study.heejin.chapter9.item59;

import java.util.Random;

public class RandomBug {

    private static Random rnd = new Random();

    public static int randomBug(int n) {
        return Math.abs(rnd.nextInt()) % n;
    }

    public static int random(int n) {
        return rnd.nextInt(n);
    }

    public static void main(String[] args) {
        // 얼마 지나지 않아 같은 수열이 반복된다.
        for (int i = 1; i < 10; i++) {
            int value = 2 * i;
            System.out.println("n이 크리 크지 않은 2의 제곱수 : n = " + value + ", rnd = " + randomBug(value));
        }

        // 몇몇 숫자가 평균적으로 더 자주 반환된다.
        for (int i = 1; i < 10; i++) {
            int value = 499999 * i;
            if (value % 2 != 0) {
                System.out.println("n이 2의 제곱수가 아닌 큰 수 : n = " + value + ", rnd = " + randomBug(value));
            }
        }

        // 무작위 수 1백만 개 생성 후, 중간 값보다 작은 수의 개수를 출력
        // random 메서드가 이상적으로 동작한다면 약 50만 개가 출력돼야 하지만, 실제로 돌려보면 666,666에 가까운 값을 얻는다.
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;

        for (int i = 0; i < 1000000; i++) {
            if (randomBug(n) < n/2) {
                low++;
            }
        }
        System.out.println("randomBug low ===> " + low);

        // Random.nextInt(int)를 사용하여 해결
        low = 0;
        for (int i = 0; i < 1000000; i++) {
            if (random(n) < n/2) {
                low++;
            }
        }
        System.out.println("random low ===> " + low);
    }

}
