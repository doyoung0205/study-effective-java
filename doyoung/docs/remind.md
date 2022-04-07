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

인스턴스 생성을 통제하는 클래스 중 하나이다.

<code>new</code> 라는 단순한 이름 보다는 목적에 맞게 정적으로 이름을 가질 수 있다.

항상 생성하지 않고 캐싱을 할수 있으며

형변환을 위해 사용하기도 하며

때로는 자식 타입을 반환 할 수 있다는 장점이 있다.

하지만 생성자를 <code>private</code> 으로 할 경우 상속이 불가능 하다는 단점이 있다.

from : 형변환 of : 여러개 매개변수받을 때 instance : 해당 클래스의 인스턴스 생성할 때 getType : 다른 객체를 생성 할 때

</div>
</details>

<details>
<summary>
빌더패턴은 무엇인가?
</summary>
<div markdown="1">

다양한 생성자가 필요함에 따라서 클라이언트가 생성자를 호출 할 때 매개변수가 헷갈릴 가능성이 높다.

따라서 빌더 패턴을 필수적인 요소들을 빌더 생성자를 통해 생성한 뒤 일종의 프로퍼티를 setter 를 통해서 객체를 완성할 수 있다.

하지만 필수적인 요소들이 많다면 처음과 같은 문제점이 발생할 수 있을 것이고 이는 스텝 빌더를 통해 해결할 수 있다.
 

</div>
</details>

<details>
<summary>
객체 참조에 따른 메모리 누수에 대해서 주의해야할 점은 무엇인가?
</summary>
<div markdown="1">

객체 안에 배열과 같은 타입이 사용 되거나 캐싱이 있을 경우

직접적으로 null 을 대입하지 않으면 계속해서 메모리가 누적되거나 
캐싱또한 `linkedHashmap 의 removeEldestEntry` 같은 메소드를 통해서 주기적으로 캐싱된 데이터가 너무 누적되지 않도록 방지 해줘야 한다.


</div>
</details>

<details>
<summary>
<code>finalizer</code> 의 문제점과 <code>finalizer attack</code> 이란 무엇인가?
</summary>
<div markdown="1">

기본적으로 finalizer 의 문제점은 다음과 같습니다.

- 첫째, 언제 실행이 될지 모릅니다.
- 둘째, GC에 따라 실행이 되지 않을 수 있습니다.
- 셋째, 예외가 발생되면 무시됩니다.

상속받은 객체에서 finalizer 가 실행 될 때 예외가 발생하면 무시 되기 때문에

부모 클래스의 불변식을 깬 코드를 작성할 수 있다.

```
@Override
protected void finalize() throws Throwable {
    this.transfer(100000, "dory");
}
```


</div>
</details>


<details>
<summary>
자원을 직접 명시하지 않고 의존 객체 주입을 받아서 사용하면 어떤 장점이 있는가?
</summary>
<div markdown="1">

기존에 자원이 있는 상태에서 다른 자원이 필요할 때 명시된 구체적인 자원 하나로 모든 상황을 대처하기는 어렵다.

따라서 의존 객체를 주입 받아 주입된 객체를 활용하는 것에만 관심을 가진 클래스로 만든다면

의존 객체 주입이 유연성과 테스트 용이성을 개선해준다.

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
왜 불변객체를 사용하는가? 
</summary>
<div markdown="1">
스레드 안전 때문이다.

멀티 스레드 환경에서 어떤 스레드가 데이터에 접근해도 해당 객체는 값이 변경되지 않는 객체임으로 변경된 데이터에 대한 우려가 없다.

상속을 할 경우 재졍의가 가능하기 때문에 주의할 점은 상속을 막아야 한다.
</div>
</details>

<details>
<summary>
클래스의 인스턴스 변수가 <code>private</code> 으로 선언하면 어떤 것 이 좋은가?
</summary>
<div markdown="1">

패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공함으로써 내부 표현 방식을 언제든 바꿀 수 있는 유연성을 얻을 수 있다.

</div>
</details>

<details>
<summary>
상속과 컴포지션 중 어떤 것을 사용 할 것인가?
</summary>
<div markdown="1">

컴포지션을 먼저 고려 할 것 같다.

구현하는 입장인 하위클래스에서는 실수로 상위 클래스의 메소드를 호출하여 의도하지 않은 방향으로 코드가 작성될 수 있다.
즉, 메소드 재정의(`오버라이딩`)가 된다면 `캡슐화`를 깨뜨릴 수 있다.

`캡슐화` 는 다른 컴포넌트(클래스) 와 소통할 때 내부 동작 방식 은 전혀 신경쓰지 않을 수 있어야 한다.

자식 클래스는 부보 클래스의 내부 동작 방식을 신경쓰지 않을 수 없다.


상속을 사용하는 경우는 is-a 관계 인 경우이거나 self 문제가 발생할 경우

상속을 할 경우 문서화(implSpec) 를 꼭하여 무엇을 한는지 문서화를 한다.  

</div>
</details>

<details>
<summary>
추상클래스 와 인터페이스 중 어떤 것을 우선시 되는가?
</summary>
<div markdown="1">


상위클래스의 코드 추가가 있을 경우 

클래스는 대해서는 단일 상속을 지원함으로 해당 클래스가 꼭 변경되어야 한다. 
하지만 인터페이스는 다중 구현이 됨으로 새로운 인터페이스를 만들어 해당되는 부분에만 추가할 수 있다.


</div>
</details>

# 제네릭

<details>
<summary>
제네릭의 로 타입을 사용하지 않아야 하는 이유가 무잇인가?
</summary>
<div markdown="1">
이유는 `타입 안정성`, `타입 표현력`을 잃기 때문이다.

로 타입의 객체는 어떤 객체든 들어갈 수 있다.

따라서 컬렉션에서 원소를 꺼내 사용할 때 어떤 타입이 올지 예측 하기 어렵다. 어렵고 캐스팅할 때 오류가 발생할 가능성이 높다.

</div>
</details>

<details>
<summary>
제네릭의 와일드 카드는 무엇이고 어떤 상황에서 사용하는가?
</summary>
<div markdown="1">

와일드 카드는 어떤 타입일 진 모르지만 통일화된 타입이 라는 것을 알 수 있기에 

해당 객체에 여러개의 타입이 있을 가능성이 없어 타입 안정성을 확보 할 수 있다.

</div>
</details>

<details>
<summary>
배열과 리스트의 차이점이고 둘 중 우선되어야 하는 것은 어떤 것인가?
</summary>
<div markdown="1">


- 배열: 공변 (함께 변한다.)
   - 실체화 (런파임시 오류)
- 제네릭: 불공변
  - 소거 방식 (컴파일시 오류)

컴파일시 오류를 발생한다면 런타임보다 빠르게 확인할 수 있다. 따라서 제네릭 타입의 리스트를 사용하는 것이 좋다.


</div>
</details>



<details>
<summary>
제네릭과 가변인수를 사용할 때 주의해야할 사항은 무엇인가?
</summary>
<div markdown="1">

가변인수는 내부적으로 배열을 만들기 때문에 배열의 단점인 힙 오염이 발생할 수 있기 때문에 

해당 가변인수로 만들어진 배열을 조작하지 않도록 주의해야 한다.

</div>
</details>

<details>
<summary>
타입 안전 이종 컨테이너는 무엇인가?
</summary>
<div markdown="1">

키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺼 때 매개변수화한 키를 함께 제공하면 된다.

다양한 타입 매개변수를 KEY 로 사용하는 것이 목적 ! 

</div>
</details>

<details>
<summary>
슈퍼 타입 토큰은 무엇인가? 
</summary>
<div markdown="1">

List 의 제네릭이 붙어도 런타임에는 제네릭 정보가 소거 되어서 파라미터 타입의 List<String>.class 같은 방식으로 보내지 못하지만

제네릭 타입의 객체를 상속해서 상위 객체의 제네릭 타입을 알 수있도록 하여 `ParameterizedTypeReference` 같은 클래스를 슈퍼 타입 토큰 이라고 한다.

예시로 resttemplate 에서 제네릭 타입의 리스트를 받기 위해서 사용된다.

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
