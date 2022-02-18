## 다 쓴 객체 참조를 해제하라

#### 가비지컬렉터가 있다고 메모리 관리에 더 이상 신경 쓰지 않아도 된다고 오해 하지 말자.

> 오히려 의도치 않게 객체를 살려두는 메모리 누수를 찾기 아주 까다롭다.

### 객체 참조 해제 되지 않는 예시

메모리 누수는 어디서 일어날까?
<br/>
스택이 커졌다가 줄어들었을 떄, **스택에서 꺼내진 객체들을 가비지 컬렉터가 회수하지 않는다.**

```
public Object pop() {
    if (size == 0)
        throw new EmptyStackException();
    return elements[--size];
}
```

프로그램에서 그 객체를을 사용하지 않더라도 말이다.
<br/>
이 스택이 그 객체들의 다쓴 참조 (obsolete reference, 만기 참조) 를 여전히 가지고 있기 때문이다.

```
public Object popAndUnreference() {
    if (size == 0)
        throw new EmptyStackException();
    Object result = elements[--size];
    elements[size] = null; // 다 쓴 참조 해제
    return result;
}
```

### 객체 참조 해제 방법

1. 객체를 다 썻을 떄 참조를 해제 하는 가장 쉬운 방법은 null 처리 하는 것이다.(**비추천**)
    - **장점:** null 처리한 참조를 실수로 사용하려 하면 즉시 NPE 가 발생하여 프로그램 오류를 조기 발견할 수 있다.
    - **단점:** null 처리하는 것에 대해 혈안이 되어 프로그램을 필요 이상으로 지저분하게 만든다.
2. 그 참조를 담은 변수를 유효 범위(scope) 밖으로 밀어내는 것이다. (Item57 참조) (**추천**)

### null 처리를 해주어야 하는 예시

일반적으로 자기 메모리를 직접 관리하는 클래스라면 프로그래머는 항시 메모리 누수에 주의해야 한다.

1. 스택
2. 캐시
    - 객체 참조를 캐시에 넣고 나서, 이 사실을 까맣게 잊으면 메모리 누수가 될 수 있다.

    1. WeakHashMap 을 사용해 캐시를 만들자.
        - 캐시에서 다 쓴 객체는 null 을 통해서 자동으로 제거될 것 이다.
    2. ScheduledThreadPoolExecutor 이나 LinkedHashMap 의 removeEldestEntry 같은 메서드를 사용해
3. 리스너 혹은 콜백
    - 콜백을 등록하고 명확히 해지하지 않는다면, 뭔가 조치해주지 않는 한 콜백이 쌓여갈 것이다.
    - **약한 참조**로 저장하면 가비지 컬렉터가 즉시 수거해간다. (ex. `WeakHashMap`)

## 어려웠던 부분

### Reference 는 4가지 종류

- 뒤로 갈수록 GC에 의해 제거될 우선순위가 높다.
    - `Strong Reference` : 절대 GC가 되지 않음
    - `Soft Reference` : JVM의 메모리가 없을 경우 GC 합니다.
        - 캐시로 사용 X -> GC threshold 까지 계속 차 있는 상태가 유지되면서 훨씬 더 잦은 GC 발생
    - `Weak Reference` : 해당 객체가 GC 되도록 유도 됨.
    - `Phantom Reference` : 올바르게 삭제 하고 삭제 이후 작업을 조작하기 위함.
        - 잘못된 구현의 예로는 GC에 의해 제거된 객체가 **부활(resurrect)** 하는 것인데,
        - 이는 finalize() 메서드에 Strong Reference를 갖도록 코딩되어 있을 경우 발생합니다. 하지만 Phantom Reference는 메모리에서 해지된 후
        - enqueue되기 때문에 finalize()에서 객체가 부활되는 문제가 없습니다.

#### finalize()의 문제점

- 첫째, 언제 실행이 될지 모릅니다.
- 둘째, GC에 따라 실행이 되지 않을 수 있습니다.
- 셋째, 예외가 발생되면 무시됩니다.
- 넷째, 성능 저가하 발생할 수 있습니다.

- WeakHashMap : 약한 참조를 가진 HashMap


- ScheduledThreadPoolExecutor (백그라운드 스레드)
    - 만약 어떤 작업을 일정 시간 지연 후에 수행하거나, 일정 시간 간격으로 주기적으로 실행해야
