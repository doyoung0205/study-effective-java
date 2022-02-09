## 불필요한 객체 생성을 피하라


똑같은 기능의 객체를 매번 생성하기보다는 객체 하나를 재사용하는 편이 나을 때가 많다.


- String 문자열 리터럴을 사용하는 것이 좋다.

```
[AS-IS]
String name = new String("doyoung"); // 매번 새로운 인스턴스 생성

[TO-BE]
// 가상 머신 안에서 이와 똑같은 문자열 리터럴을 사용하는 모든 코드가 같은 객체 보장
String nameWithJLS = "doyoung"; 


```

- 생성자 보다 팩터리 메서드를 사용하는 것이 좋다.
  - 생성자는 호출 할때 마다 새로운 객체를 만들지만, 팩터리 메서드는 전혀 그렇지 않다.

```Boolean.valueOf(true);```  


- 생성 비용이 비싼 객체는 정적초기화 과정에서 직접 생성해 캐싱해두고 재사용하는 방법이 좋다.
```"doyoung".matches(...)``` 같은 String.matches 는 내부에서
Pattern 인스턴스를 생성하고 한번 사용하고 GC 대상이 된다.

Pattern 은 유한상태머신을 만들기 때문에 생성 비용이 비싸다.
<br>
따라서 정적초기화 과정에서 직접 생성해 캐싱해두고 재사용하는 방법이 좋다.


- 오토박싱을 사용할 때 상호변환을 주의해야 한다.


```
private static long sum() {
    Long sum = 0L; // -> Long 타입으로 선언한 이유로 속도가 10배 느려짐.
    // [TO-BE] long sum = 0L;
    for (long i = 0; i <= Integer.MAX_VALUE; i++)
        sum += i;
    return sum;
}
```


- 다른 타입을 반환 할 떄도 항상 인스턴스를 만들지 말고 미리 생성된 (캐싱된) 항상 같은 객체를 반환 할 수 있다.

```
HashMap keySet 메서드 예시
keySet 이 null 이 아닐경우,기존에 가지고있는 keySet 을 반환
public Set<K> keySet() {
    Set<K> ks = keySet; 
    if (ks == null) {
        ks = new KeySet();
        keySet = ks;
    }
    return ks;
}


```
