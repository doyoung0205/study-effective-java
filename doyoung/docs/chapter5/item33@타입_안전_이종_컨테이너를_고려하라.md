## 타입 안전 이종 컨테이너를 고려하라

제네릭은 Set<E>, Map<K,V> 등의 컬렉션과 ThreadLocal<T>, AtomicReference<T> 등의 단일원소 컨테이너에도 흔히 쓰인다.

이런 모든 쓰임에서 매개변수화 되는 대상은 컨테이너 자신이다.

**따라서 하나의 컨테이너에서 매개변수화할 수 있는 타입의 수가 제한된다.**

### 타입 안전 이종 컨테이너

그렇다면 타입을 제한하지 않는 컨테이너를 사용할 필요가 있을 수 있다.

`DB 의 Row 는 여러개의 Column 을 가질 수 있는데, 모두 열을 타입 안전하게 이용할 수 있다면 멋질 것이다.`

키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺼 때 매개변수화한 키를 함께 제공하면 된다.

참고: `Favorites.java`

---

- 슈퍼타입토큰

타입 매개변수가 제네릭 인 경우 List<String> 이나 List<Integer> 나 List 로 같다.

이때 사용할 수 있는 방법이 슈퍼타입토큰 이다.


Spring 에서는 ParameterizedTypeReference 라는 클래스로 미리 구현해놓았다.


`f.putFavorite(new TypeRef<List<String>>, pets)`

하지만 슈퍼타입 토큰은 비한정적이다. 
