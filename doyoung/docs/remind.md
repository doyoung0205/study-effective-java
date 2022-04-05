# 상기시키자.

> 코드는 책을 쓰는것과 비슷하지만 수정할 수 있다.

```
입으로 내뱉어 버린 말이나 종이에 인쇄되어 출간된 책이나 잡지와는 달리, 프로그램은 계속 수정할 수 있다.
그래서 코드는 단순히 효과적으로 작동하고 다른 사람이 쉽게 이해할 수 있기만 하면 되는 게 아니라,
**구조가 수정하기 쉬워야 한다.**
```

# ETC

<details>
<summary>
왜 불변객체를 사용하는가? 
</summary>
<div markdown="1">
스레드 안전 때문이다.

멀티 스레드 환경에서 어떤 스레드가 데이터에 접근해도 해당 객체는 값이 변경되지 않는 객체임으로 변경된 데이터에 대한 우려가 없다.
</div>
</details>


<details>
<summary>
객체 지향 원칙의 solid 는 무엇이고 왜 이런 원칙을 적용해야하고 이렇게 되면 왜 객체지향스러운가?
</summary>
<div markdown="1">

[//]: # (TODO)
</div>
</details>

# 객체 생성과 파괴

<details>
<summary>
<code>try-with-resource</code> 가 무엇인가? 
</summary>
<div markdown="1">

(File, DB) 등의 외부 자원을 사용하는 경우 <code>close</code> 를 통해 자원을 닫아줘야 한다.

물론 GC 가 있어서 괜찮다고 생각할 수 있지만, 

1. OS로부터 자원을 할당 받아 사용하는 네이티브 메소드 경우, C에서 자원을 할당 받게 된다.
   <br>따라서 GC가 자원을 할당 받았는지 알 수 가 없다.
2. 메서드를 통해서 자원을 그만쓴다라는 것을 알리고 GC가 메모리를 해제 할 수 있도록 하는 것
3. 명시적으로 수거가 되도록 하는 것.

예를 들어, Connection 을 close 하지 않았을 경우

계속 다른 Connection 을 사용하고 Threads_connected 가 최대 사용량을 넘어가 오류가 발생하게 된다.

이와 같은 이유로 외부 자원을 닫아줘야 하기 때문에 try ~ finally 구문을 사용해 close 하기도 하고 
close 메서드를 호출하지 않는 실수를 할 수 도 있다.

그리고 이 방법은 너무 지저분 하다.

따라서 자바 7 버전 부터 AutoCloseable 인터페이스를 제공하여 이를 구현한 클래스들은 다음과 같은 try ~ with ~ resource 구문을 사용할 수 있다. 

```

try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
    return br.readLine();
}

```

이 방법을 쓰면 훨씬 짧고 close 메서드를 사용하지 않아도 자동적으로 자원을 회수 할 수 있다.

</div>
</details>

<details>
<summary>
정적 팩터리 메서드가 무엇인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
빌더패턴은 무엇인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
객체 참조에 따른 메모리 누수에 대해서 주의해야할 점은 무엇인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
<code>finalizer attack</code> 이란 무엇인가?
</summary>
<div markdown="1">

</div>
</details>



<details>
<summary>
자원을 직접 명시하지 않고 의존 객체 주입을 받아서 사용하면 어떤 장점이 있는가?
</summary>
<div markdown="1">

</div>
</details>

# 모든 객체의 공통 메서드

<details>
<summary>
equals 를 정의할 때 주의해야할 사항이 무엇이 있는가?
</summary>
<div markdown="1">

</div>
</details>


<details>
<summary>
equals 를 정의 할 때 왜 hashcode 도 같이 정의해야 하는가?
</summary>
<div markdown="1">

</div>
</details>


<details>
<summary>
clone 을 재정의 할 때 주의해야할 점은 어떤 것 이 있는가?
</summary>
<div markdown="1">

</div>
</details>

# 클레스와 인터페이스

<details>
<summary>
클래스의 인스턴스 변수가 <code>private</code> 으로 선언하면 어떤 것 이 좋은가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
추상클래스 와 인터페이스 중 어떤 것을 우선시 되는가?
</summary>
<div markdown="1">

</div>
</details>


<details>
<summary>
상속과 컴포지션 중 어떤 것을 사용 할 것인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
맴버 클래스의 static 은 어떤 상황에서 붙여야 하는가?
</summary>
<div markdown="1">

</div>
</details>

# 제네릭

<details>
<summary>
질문
</summary>
<div markdown="1">

</div>
</details>


<details>
<summary>
제네릭의 로 타입을 사용하지 않아야 하는 이유가 무잇인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
배열과 리스트의 차이점이고 둘 중 우선되어야 하는 것은 어떤 것인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
제네릭의 와일드 카드는 무엇이고 어떤 상황에서 사용하는가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
제네릭과 가변인수를 사용할 때 주의해야힐 사항은 무엇인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
타입 안전 이종 컨테이너는 무엇인가?
</summary>
<div markdown="1">

</div>
</details>

<details>
<summary>
슈퍼 타입 토큰은 무엇인가? 
</summary>
<div markdown="1">

</div>
</details>


---


`remind template`

```

<details>
<summary>
질문
</summary>
<div markdown="1">
답변
</div>
</details>

```
