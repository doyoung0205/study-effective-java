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


