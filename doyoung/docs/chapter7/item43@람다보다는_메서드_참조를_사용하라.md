### 람다보다는 메서드 참조를 사용하라

람다 보다 더 **간결하게** 만드는 방법이 메서드 참조이다.

#### 람다

```
map.merge(key, 1 (count, incr) -> count + incr);
```

#### 메서드 참조

```
map.merge(key, 1, Integer::sum);
```

### 메서드참조가 간결하지만 람다를 사용하는이유

1. 매개변수의 이름 자체가 프로그래머에게 좋은 가이드가 될 수 있다면 메서드 참조보다는 람다를 사용할 수 있다.
2. 이름이 긴 클래스 안에 메서드를 참조할 경우 간결하지 않을 수 있다.

```
service.execute(GoshThisClassNameIsHumongous::action);
```

### 결론

메서드 참조 쪽 짧고 명확하다면 메서드 참조를 쓰고, 그렇지 않을 떄만 람다를 사용하라 !

### 메서드 참조 유형

- 정적 : 정적 메서드를 참조하는 경우 (Integer::parseInt) // str -> Integer.parseInt(str);
- 한정적 - bound (인스턴스) : 인스턴스 메서드를 참조하는 경우 (Instant.now()::isAfter) // Instant then = Instant.now(); t -> then.isAfter(t);
- 비한정적 - unbound (인스턴스) : 전달용 매개변수의 인스턴드 타입을 참조하는 경우 (String::toLowerCase) // str -> str.toLowerCase()
- 클래스 생성자 : TreeMap<K,V>::new // () -> new TreeMap<K,V>();
- 배열 생성자 : int[]::new // len -> new int[len]


