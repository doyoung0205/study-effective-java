# 동시성

[아이템 83. 지연 초기화는 신중히 사용하라](#지연-초기화는-신중히-사용하라)  

[- 다양한 초기화 방식](#다양한-초기화-방식)    
[- 정리](#정리)    

<br>

## 지연 초기화는 신중히 사용하라
- 지연 초기화는 필드의 초기화 시점을 그 값이 처음 필요할 때까지 늦추는 기법이다.

- 장점
  - 값이 전혀 쓰이지 않으면 초기화도 일어나지 않는다.
  - 주로 최적화 용도로 쓰이지만, 클래스와 인스턴스 초기화 때 발생하는 위험한 순환 문제를 해결하는 효과도 있다.

- 단점
  - 클래스 혹은 인스턴스 생성 시의 초기화 비용은 줄지만, 지연 초기화하는 필드에 접근하는 비용이 커진다.
  - 멀티스레드 환경에서는 지연 초기화를 하기가 까다롭다.
  
- **대부분의 상황에서 일반적인 초기화가 지연 초기화보다 낫다.**


### 다양한 초기화 방식
1. 인스턴스 필드를 초기화하는 일반적인 방법

    ```java
    private final FieldType field = computeFieldValue();
    ```
    
2. **지연 초기화가 초기화 순환성을 깨뜨릴 것 같으면 `synchronized`를 단 접근자를 사용하자.**

    ```java
    // 인스턴스 필드의 지연 초기화 - synchronized 접근자 방식
    private FieldType field;
      
    private synchronized FieldType getField() {
        if (field == null) {
            field = computeFieldValue();
        }
        return field;
    }
    ```
  
3. **성능 때문에 정적 필드를 지연 초기화해야 한다면 지연 초기화 홀더 클래스 관용구를 사용하자.**

    ```java
    // 정적 필드용 지연 초기화 홀더 클래스 관용구
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }
      
    private static FieldType getField() {
        return FieldHolder.field;
    }
    ```
  
4. **성능 때문에 인스턴스 필드를 지연 초기화해야 한다면 이중검사 관용구를 사용하라.**

    ```java
    // 인스턴스 필드 지연 초기화용 이중검사 관용구
    private volatile FieldType field;
      
    private FieldType getField() {
        FieldType result = field;
        if (result != null) {  // 첫 번째 검사 (락 사용 안함)
            return result;
        }
      
        synchronized(this) {
            if (field == null) {  // 두 번째 검사 (락 사용)
                field = computeFieldValue();
            }
            return field;
        }
    }
    ```
  
   - `result` 변수는 필드가 이미 초기화된 상황에서는 그 필드를 딱 한번만 읽도록 보장하는 역할을 한다. 

5. 반복해서 초기화해도 상관없는 인스턴스 필드를 지연 초기화해야 할 때는 단일검사 관용구를 사용할 수 있다.
  
    ```java
    // 단일 검사검사 관용구 - 초기화가 중복해서 일어날 수 있다.
    private volatile FieldType fieldType;
      
    private FieldType getField() {
        FieldType result = field;
        if (result == null) {
            field = result = computeFieldValue();
        }
        return result;
    }
    ```
   - 이 관용구는 어떤 환경에서는 필드 접근 속도를 높여주지만, 초기화가 스레드당 최대 한 번 더 이뤄질 수 있다. 


- 이번 장의 지연 초기화 기법은 기본 타입 필드와 객체 참조 필드 모두에 적용할 수 있다.    
- 이중검사와 단일검사 관용구를 수치 기본 타입 필드에 적용한다면 필드의 값을 null 대신 0과 비교하면 된다.


### 정리
- 대부분의 필드는 지연시키지 말고 곧바로 초기화해야 한다.
- 성능 때문에 지연 초기화를 사용해야 한다면 인스턴스 필드에는 이중검사 관용구를, 정적 필드에는 지연 초기화 홀더 클래스 관용구를 사용하자.


<br>
