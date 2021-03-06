## item54 null이 아닌 빈 컬렉션이나 배열을 반환하라

### 반환 타입이 컬렉션이 경우 null을 반환하면 클라이언트에서 예외를 감수해야 한다
- 빈 컬렉션이 아닌 null이 반환되는 경우 클라이언트는 `null 처리`를 해주어야 한다
- `Collections.emptyList()` 를 활용하여 빈 컬렉션을 반환하는 것이 낫다

### 배열의 경우 빈 배열을 반환하라
- 배열도 마찬가지로 null 을 반환하는 것보다 빈 배열을 반환하는 것이 낫다
- 빈 배열의 객체 생성이 부담된다면 길이가 0인 배열을 정적으로 선언하여 사용하도록 한다


### 정리
- null이 아닌, 빈 배열이나 컬렉션을 반환하라
- null을 반환하는 경우 사용자가 API를 사용하기 어렵다(NULL 처리가 필요하기 때문에)
  - 성능이 더 좋아지는 것도 아니다 
