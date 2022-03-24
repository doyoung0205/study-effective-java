## item29 이왕이면 제네릭 타입으로 만들라

### 제네릭을 사용하지 않은 Stack 예제
- Object[] 배열을 사용
- 객체를 반환할 때 사용에 맞게 형변환이 필요하다
    ```java
    public class Stack {
        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
    
            Object result = elements[--size];
            elements[size] = null; //다쓴 객체 참조해제
    
            return result;
        }
    }
    ```
  
### 제네릭을 사용하는 Stack 예제 
- 객체 생성 시에 타입을 부여하기 때문에 타입 안정성을 보장. 컴파일 시점에 오류를 파악할 수 있다
- 런타임시 객체의 형변환을 컴파일러가 자동으로 해주기 때문에 형변환에 대해 신경쓸 필요가 없다
    ```java
    Stack<String> stack = new chapter5.item29.Stack<>();
    
    stack.push("A");
    stack.push("B");
    stack.push("C");
    
    // then
    String data1 = stack.pop();
    String data2 = stack.pop();
    String data3 = stack.pop(); 
    ```
  
### 배열 타입을 제네릭으로 사용한다면 ?
- 이전 장에서 학습했다시피 배열은 제네릭타입, 매개변수화 타입, 타입 매개변수를 생성할 수 없다 
  - `new List<E>[]`, `new List<String>[]`, `new E[]` => X (타입 안전하지 못하다)

#### 1) Object[]을 생성하여 형변환하여 사용하는 방법
- Object[] 배열로 초기화 후 제네릭 배열로 형변환
- 가독성이 좋고 배열 생성 시에만 형 변환해주면 된다
- 배열의 런타임 타입이 컴파일 타입과 달라 `힙 오염`을 발생한다 
  ```java
      (E[]) new Object[] // 타입 안전하지 못하다
  ```
#### 2) 필드 타입을 Object[]로 사용하는 방법
- Object[] 필드를 사용하며 객체 반환 시 제네릭 타입으로 형변환
- 배열을 반환할 떄마다 형변환을 해주어야 한다
  ```java
    @SuppressWarnings("unchecked") E result = (E) elements[--size]; // 객체 사용 시 타입 변환
  ```

### 정리
- 클라이언트에서 직접 형 변환하는 타입보다 제네릭 타입이 더 안전하고 편하다
- 새로운 타입을 설계할 경우 형변환 없이도(제네릭 사용) 사용할 수 있도록 하라