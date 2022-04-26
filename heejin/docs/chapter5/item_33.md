# 제네릭

[아이템 33. 타입 안전 이종 컨테이너를 고려하라](#타입-안전-이종-컨테이너를-고려하라)
[- 정리](#정리)  

<br>

## 타입 안전 이종 컨테이너를 고려하라
- 제네릭은 Set<E>, Map<K, V> 등의 컬렉션과 ThreadLocal<T>, AtomicReference<T> 등의 _단일원소 컨테이너_에도 흔히 쓰인다.
- 이런 모든 쓰임에서 매개변수화되는 대상은 (원소가 아닌) 컨테이너 자신이다.

> 타입 이종 컨테이너 패턴  
> 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공하는 패턴

> 타입 토큰  
> 컴파일타임 타입 정보와 런타임 타입 정보를 알아내기 위해 메서드들이 주고받는 class 리터럴  
> 예컨데, String.class의 타입은 Calss<String>이고, Integer.class의 타입은 Class<Integer>인 식이다.

### 타입 이종 컨테이너 패턴
```java
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
```
- Favorites 인스턴스는 타입 안전하다.
  - String을 요청했는데 Integer를 반환하는 일은 절대 없다.
  - 또한 모든 키의 타입이 제각각이라, 일반적인 맵과 달리 여러 가지 타입의 원소를 담을 수 있다.
- `Map<Class<?>, Object>`를 보면, 비한정적 와일드카드 타입이 중첩되어 있기 때문에 모든 키가 서로 다른 매개변수화 타입일 수 있다는 뜻이다.
- favorites 맵의 값 타입은 단순히 `Object`이기 때문에 키와 값 사이의 타입 관계를 보증하지 않는다.
- 그래서 `getFavorite` 메서드에서 Class의 `cast` 메서드를 사용해 객체가 가리키는 타입으로 동적 형변환한다.


#### 타입 이종 컨테이너의 제약 사항
1. Class 객체를 (제네릭이 아닌) 로 타입으로 넘기면 Favorites 인스턴스의 타입 안전성이 쉽게 깨진다.
   ```java
   f.putFavorite((Class) Integer.class, "Integer의 인스턴스가 아닙니다.");
   int favoriteInteger = f.getFavorite(Integer.class);
   ```
   - 위 코드는 putFavorite을 호출할 때는 문제가 없지만, getFavorite을 호출할 때 ClassCastException을 던진다.
   - HashSet과 HashMap 등의 일반 컬렉션 구현체에도 똑같은 문제가 있다.
   - 하지만, 이 정도의 문제를 감수하겠다면 런타임 타입 안전성을 얻을 수 있다.
   - `java.util.Collections`에는 `CheckedSet`, `CheckedList`, `CheckedMap` 같은 메서드가 있는데, 바로 이 방식을 적용한 컬렉션 래퍼들이다.
   ```java
   public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }
   ```

2. 실체화 불가 타입에는 사용할 수 없다.
- favorites 맵에 `String`이나 `String[]`은 저장할 수 있어도, `List<String>`은 저장할 수 없다.
- `List<String>.class` 라고 쓰면 문법 오류가 난다.
- 그렇다고 `List.class` 라는 같은 객체를 공유해서 `List<Integer>`, `List<String>` 을 반환하도록 허용한다면 Favorite 객체 내부는 엉망이 된다.
- 이 제약에 대해 _슈퍼 타입 토큰_으로 해결하려는 시도도 있다.
  - [슈퍼 타입 토큰 사용 예시](../../src/main/java/study/heejin/chapter5/item33/SuperTypeToken.java)
  - [Favorite - TypeReference](../../src/main/java/study/heejin/chapter5/item33/TypeReference.java)
  - [Favorite - 슈퍼 타입 토큰 적용](../../src/main/java/study/heejin/chapter5/item33/TypeSafeFavorite.java)

### 정리
- 컬렉션 API로 대표되는 일반적인 제네릭 형태에서는 한 컨테이너가 다룰 수 있는 타입 매개변수의 수가 고정되어 있다.
- 하지만 컨테이너 자체가 아닌 키를 타입 매개변수로 바꾸면 타입 안전 이종 컨테이너를 만들 수 있다.
- 타입 안전 이종 컨테이너는 Class를 키로 쓰며, 이런 식으로 쓰이는 Class 객체를 타입 토큰이라 한다.


<br>

---
#### Reference

- [토비의 봄 TV 2회 - 슈퍼 타입 토큰](https://www.youtube.com/watch?v=01sdXvZSjcI)

