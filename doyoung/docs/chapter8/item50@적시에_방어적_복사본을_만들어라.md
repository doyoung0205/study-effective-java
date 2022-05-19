### 적시에 방어적 복사본을 만들라.

오버런, 배열 오버런, 와일드 포인터 같은 메모리 충돌 오류에서 안전하다.

하지만 클라이언트가 불변식을 깨뜨리려 혈안이 되어 있다고 가정하고 방어적으로 프로그래밍해야 한다.

실제로도 악의적인 의도를 가진 사람들이 시스템의 보안을 뚫으려는 시도가 늘어 난다.

어떤 객체든 그 객체의 허락 없이는 외부에서 내부를 수정하는 일은 불가능하다.

```

Date start = new Date();
Date end = new Date();
Period p = new Period(start, end);
end.setYear(89); // p의 내부를 수정했다.

``` 

다행히 자바8 이후로는 불변인 Instant 를 사용하면된다. (LocalDateTime, ZonedDateTime) 을 사용하면 된다.


### 생성 시점 공격

Period 는 내부를 보호하기 위해서는 생성자에서 받은 가변 매개변수 각각을 방어적 복사를 해야한다.

```
public Period(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());
    
    if (this.start.compareTo(this.end) > 0) {
        throw new IllegalArgumentException("시작시간이 종료시간보다 늦습니다.");
    }
}
```

매개변수의 유효성을 검사하기 전에 방어적 복사본을 만들고, 이 복사본으로 유효성을 검사한 점에 주목하자.

왜냐하면 멀티스레딩 환경이라면 원본 객체의 유효성을 검사한 후 복사본을 만드는 그 찰나의 취약한 순간에 다른 스레드가 원본 객체를 수정할 위험이 있기 때문이다.

방어적 복사를 매개변수 유효성 검사 전에 수행하면 이런 위험에서 해방될 수 있다.

컴퓨터 보안 커뮤니티에서는 이를 검사시점/사용시점 공격 혹은 `TOCTOU` 공격 이라고 한다.


`방어적 복사본은 배열이 아니라면 clone 말고 생성자를 통해서 만든다. `



### 사용 시점 공격

아직도 접근자 메서드가 내부의 가변 정보를 **직접** 드러내기 때문에 공격이 가능하다.

따라서 가변 필드의 방어적 복사본을 반환하면 된다.

```
public Date start() {
    return new Date(start.getTIme());   
}

public Date end() {
    return new Date(end.getTime());
}

```

여기서는 생성자와 달리 clone 을 사용해도 된다. 왜냐하면 Period 가 가지고 있는 Date 객체는 
java.util.Date 임이 확실하기 때문이다.  (feat. item13)


---

되도록이면 불변객체를 사용하자.

