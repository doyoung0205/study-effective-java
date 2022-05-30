# 메서드

[아이템 54. null이 아닌, 빈 컬렉션이나 배열을 반환하라](#null이-아닌-빈-컬렉션이나-배열을-반환하라)  
[- 정리](#정리)  

<br>

## null이 아닌, 빈 컬렉션이나 배열을 반환하라

- 컬렉션이나 배열 같은 컨테이너가 비었을 때 `null`을 반환하는 메서드를 사용할 때는 방어로직을 넣어줘야 한다.
  ```java
  public List<String> getCheeses() {
      return cheeseInStock.isEmpty() ? null : new ArrayList<>(cheeseInStock);
  }
  ```
  ```java
  List<String> cheeses = cheeseShop.getCheeses();
  if (cheeses != null && cheeses.contains("Stilton")) {
      ...
  }
  ```
  

- 빈 컨테이너를 할당하는데 비용이 들기 때문에 `null`을 반환하는 편이 낫다는 주장도 있다.
- 하지만 이는 두 가지 면에서 틀린 주장이다.  
  1 ) `null` 대신 빈 컨테이너를 할당하는 것이 성능 저하의 주범이라고 보기 어렵다. (이 정도의 성능 차이는 신경 쓸 수준이 못된다.)  
  2 ) 빈 컬렉션과 배열은 굳이 새로 할당하지 않고도 반환할 수 있다.
  - 빈 컬렉션을 반환하는 올바른 방법
    ```java
    public List<String> getCheeses() {
        return new ArrayList<>(cheeseInStock);
    }
    ```
- **빈 컬렉션 할당이 성능을 눈에 띄게 떨어뜨린다면, 불변 컬렉션을 반환하도록 최적화한다.**
  - 빈 컬렉션을 매번 새로 할당하지 않도록 최적화한 방법
    ```java
    public List<String> getCheeses() {
        return cheeseInStock.isEmpty() ? Collections.emptyList() 
                                       : new ArrayList<>(cheeseInStock);
    }
    ```
  - `Collections.emptySet`, `Collections.emptyMap` 과 같이 불변 객체는 자유롭게 공유해도 안전하다.
  - 단, 최적화 이후 수정 전과 비교하여 성능이 개선되었는지 확인해 봐야 한다.

- 배열을 쓸 때, null을 반환하지 말고 길이가 0인 배열을 반환하해야 한다.
  - 길이가 0일 수도 있는 배열을 반환하는 올바른 방법
    ```java
    public String[] getCheeses() {
        return cheeseInStock.toArray(new String[0]);
    }
    ```
  - 빈 배열을 매번 새로 할당하지 않도록 최적화한 방법
    ```java
    private static final String[] EMPTY_CHEESE_ARRAY = new String[0]; 
    public String[] getArrayOptimization() {
        return cheeseInStock.toArray(EMPTY_CHEESE_ARRAY);
    }
    ```
  - 반면, 아래의 코드와 같이 배열을 미리 할당하면 성능이 나빠진다.
    ```java
    public String[] getBadArray() {
        return cheeseInStock.toArray(new String[cheeseInStock.size()]);
    }
    ```


### 정리 
- `null`이 아닌, 빈 배열이나 컬렉션을 반환하라.
- `null`을 반환하는 API는 사용하기 어렵고 오류 처리 코드도 늘어난다.
- `null`을 사용한다고 성능이 좋은 것도 아니다

<br>
