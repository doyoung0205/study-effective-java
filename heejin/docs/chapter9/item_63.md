# 일반적인 프로그래밍 원칙

[아이템 63. 문자열 연결은 느리니 주의하라](#문자열-연결은-느리니-주의하라)  
[- 정리](#정리)  

<br>

## 문자열 연결은 느리니 주의하라
- 문자열 연결 연산자(+)를 사용하면 편하지만, 성능 저하를 감내해야 한다.
- 문자열 연결 연산자로 문자열 n개를 잇는 수단은 n^2에 비례한다.
  - 문자열은 불변이라서 두 문자열을 연결할 경우 양쪽의 내용을 모두 복사해야 하므로 성능 저하는 피할 수 없는 결과다.
  - 문자열 연결을 잘못 사용한 예 - 느리다!

    ```java
    public String statement() {
        String result = "";
        for (int i = 0; i < numItems(); i++) {
            result += lineForItem(i);   // String concatenation
        }
        return result;
    }
    ```
    
  - StringBuilder를 사용하면 문자열 연결 성능이 크게 개선된다.

    ```java
    public String statement() {
       StringBuilder b = new StringBuilder(numItems() * LINE_WIDTH);
       for (int i = 0; i < numItems(); i++) {
           b.append(lineForItem(i));
       }
       return b.toString();
    }
    ```
  - `new StringBuilder(numItems() * LINE_WIDTH)`
  - `StringBuilder`를 전체 결과를 담기에 충분한 크기로 초기화 하면 더 빠르다.


- 자바 6 이후 문자열 연결 성능을 다방면으로 개선했지만, 이 두 메서드의 성능 차이는 여전히 크다.

<br>

### 정리
- 문자열을 연결할 때는 문자열 연결 연산자(`+`)를 피하자.
- 대신 `StringBuilder`의 `append` 메서드를 사용하라

<br>


#### Reference
- [String은 항상 StringBuilder로 변환될까?](https://siyoon210.tistory.com/160)

<br>