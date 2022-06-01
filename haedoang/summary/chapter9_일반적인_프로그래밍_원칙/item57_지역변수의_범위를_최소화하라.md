## item57 지역변수의 범위를 최소화하라

### 지역변수 유효 범위 최소화의 이점
- 코드 가독성이 높아진다
- 유지보수성이 높아진다
- 오류 가능성이 낮아진다

### 지역 변수의 범위를 최소화하는 방법
- 가장 처음 쓰일 때 선언한다
- 거의 모든 지역 변수는 선언과 동시에 초기화하여야 한다
  - `try-catch` 문은 예외
- 메서드를 작게 유지하고 한 가지 기능에 집중하라 

### 복사 붙여넣기 오류를 일으킬 수 있는 for 문 
- `for` 반복문의 사용은 복사 붙여넣기 오류를 유발한다
    ```java
    for (Element e : c) {
        Element e = i.next();
    }
    
    Iterator<Element> i = c.iterator();
    while (i.next()) {
        ...
    }
    
    Iterator<Element> i2 = c2.iterator();
    while (i.next()) { // 복사 오류를 유발할 수 있음
        ...
    }
    ```
- `enhanced-for`문은 이런 오류를 컴파일 타임에 찾아낼 수 있다
    ```java
    for (Iterator<Element> i = c.iterator(); i.hasNext();) {
        ...
    }
    
    for (Iterator<Element> i2 = c2.iterator(); i2.hasNext();) {
        ...
    }
    ```
- for 문은 `while`문에 비해 가독성도 좋다