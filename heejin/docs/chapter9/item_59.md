# 일반적인 프로그래밍 원칙

[아이템 59. 라이브러리를 익히고 사용하라](#라이브러리를-익히고-사용하라)  
[- 라이브러리의 이점](#라이브러리의-이점)  
[- 라이브러리를 사용하지 않는 이유](#라이브러리를-사용하지-않는-이유)  
[- 정리](#정리)  

<br>

## 라이브러리를 익히고 사용하라
- 라이브러리를 잘 모르고 직접 구현한 경우 잘못 동작할 가능성이 있다.
  ```java
  private static Random rnd = new Random();
  
  public static int random(int n) {
      return Math.abs(rnd.nextInt()) % n;
  }
  ```
  - n이 그리 크지 않은 2의 제곱수라면 얼마 지나지 않아 같은 수열이 반복된다.
  - n이 2의 제곱수가 아니라면 몇몇 숫자가 평균적으로 더 자주 반환된다. n값이 크면 이 현상은 더 두드러진다.

  <br>
  
  ```java
  public static void main(String[] args) {
      int n = 2 * (Integer.MAX_VALUE / 3);
      int low = 0;

      for (int i = 0; i < 1000000; i++) {
          if (random(n) < n/2) {
              low++;
          }
      }
  }
  ```
  - random 메서드가 이상적으로 동작한다면 약 50만 개가 출력돼야 하지만, 실제로 돌려보면 666,666에 가까운 값을 얻는다.
  - 무작위로 생성된 수 중에서 2/3 가량이 중간값보다 낮은 쪽으로 쏠린 것이다.
  - random 메서드의 결함으로, 지정한 범위 바깥의 수가 종종 튀어나올 수 있다. `rnd.nexInt()`가 반환한 값을 `Math.abs`를 이용해 음수가 아닌 정수로 매핑하기 때문이다.
  - 이 문제는 `Random.nextInt(int)`를 사용하면 해결된다. - [예제 코드](../../src/main/java/study/heejin/chapter9/item59/RandomBug.java)

<br>

### 라이브러리의 이점
- 라이브러리를 사용하면 그 코드를 작성한 전문가의 지식과 앞서 사용한 다른 프로그래머들의 경험을 활용할 수 있다.
- 핵심적인 일과 크게 관련 없는 문제를 해결하느라 시간을 허비하지 않아도 된다.
- 따로 노력하지 않아도 성능이 지속해서 개선된다.
  - 업계 표준 벤치마크를 사용해 성능을 확인하기 때문에 표준 라이브러리 제작자들은 더 나은 방법을 꾸준히 모색한다.
- 다음 릴리즈에서 기능이 추가되면 사용할 수 있는 기능이 점점 많아진다. 
- 라이브러리를 사용해서 작성한 코드는 많은 사람에게 낯익은 코드가 된다. 

<br>

### 라이브러리를 사용하지 않는 이유
- 라이브러리에 그런 기능이 있는지 모르기 때문일 것이다.
  - 지정한 URL의 내용을 가져오는 명령줄 애플리케이션
  - 자바 9에서 InputStream에 추가된 transferTo 메서드를 사용하면 쉽게 구현할 수 있다.
  ```java
  public static void main(String[] args) throws IOException {
      try (InputStream in = new URL(args[0]).openStream()) {
          in.transferTo(System.out);
      }
  }
  ```

- **메이저 릴리즈마다 주목할 만한 수많은 기능이 라이브러리에 추가된다.**
- 라이브러리가 너무 방대하여 모든 API 문서를 공부하기는 벅차겠지만 자바 프로그래머라면 적어도 `java.lang`, `java.util`, `java.io`와 그 하위 패키지들에는 익숙해져야 한다.
  - `java.util.Collections`, `java.util.Stream`, `java.util.concurrent`의 기능을 알아두면 큰 도움이 된다.
- 때때로 라이브러리가 필요한 기능을 충분히 제공하지 못할 수 있다.
- 우선은 라이브러리를 사용하려 시도해보자. 원하는 기능이 아니라면 대안을 사용하자.
- 그 다음 선택지는 고품질의 서드파티 라이브러리일 것이다. 구글의 구아바 라이브러리가 대표적이다.

<br>

### 정리
- 바퀴를 다시 발명하지 말자.
- 있는지 잘 모르겠다면 찾아보라.
- 일반적으로 라이브러리의 코드는 직접 작성한 것보다 품질이 좋고, 점차 개선될 가능성이 크다.

<br>

