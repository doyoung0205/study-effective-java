# 객체 생성과 파괴

[아이템 9. try-finally 보다는 try-with-resource를 사용하라](#tryfinally-보다는-trywithresource를-사용하라)  
[- try-finally](#tryfinally)  
[- try-with-resource](#trywithresource)  

<br>

## try-finally 보다는 try-with-resource를 사용하라
- 
- 자바 라이브러리에는 close 메서드를 호출해 직접 닫아줘야 하는 자원이 많다. 
    - `InputStream`, `OutputStream`, `jva.sql.Connection` 등이 좋은 예다.
    - 자원 닫기는 클라이언트가 놓치기 쉬워서 예측할 수 없는 성능 문제로 이어지기도 한다.


- Collections, Arrays, Generators 종류의 스트림을 처리할 때 명시적으로 **닫지 않아야 한다.**
- 이러한 스트림과 관련된 리소스는 메모리이며 가비지 컬렉션 (GC)이 이를 자동으로 처리한다.
- IO 리소스는 스트림을 닫는 것을 잊으면 스트림이 열린 상태로 유지되고 결국 리소스 누수가 발생 한다.
- 이미 닫힌 스트림을 닫으면 IllegalStateException 이 발생한다.
 


### try-finally

- 전통적으로 자원이 제대로 닫힘을 보장하는 수단으로 `try-finally`가 쓰였다.
  ```java
  public static String firstLineOfFile(String path) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader(path));
      try {
          return br.readLine();
      } finally {
          br.close();
      }
  }
  ```

- 하지만 자원이 둘 이상이면 try-finally 방식은 너무 지저분하다.
- 또한, try 블로과 finally 블록에서 모두 예외가 발생할 수 있는데, 이런 상황에서 두 번째 예외가 첫 번째 예외를 집어삼켜 버린다.  
  그러면 스택 추적 내역에 첫 번재 예외에 관한 정보는 남지 않게 되어, 실제 시스템에서 디버깅을 어렵게 한다.


### try-with-resource

- 자바 7에서 나온 `try-with-resource`를 사용하면 위의 문제를 해결할 수 있다.
- 이 구조를 사용하려면 해당 자원이 `AutoCloseable` 인터페이스를 구현해야 한다.


- try-finally 로 작성된 코드를 try-with-resource 로 다시 작성한 것이다.
  ```java
  public static String firstLineOfFile(String path) throws IOException {
      try (BufferedReader br = new BufferedReader(new FileReader(path))) {
          return br.readLine();
      }
  }
  ```
  
- try-with-resource 이 짧고 읽기 수월할 뿐 아니라 문제를 진단하기도 훨씬 좋다.
- `readLine`과 (코드에서는 보이지 않는) `close` 양쪽에서 예외가 발생하면, `close`에서 발생한 얘외는 숨겨지고 `readLine`에서 발생한 예외가 기록된다.
- 이렇게 숨겨진 예외들도 그냥 버려지지 않고, 스택 추적 내역에 '숨겨졌다(suppressed)'는 꼬리표를 달고 출력된다.
- 또한, 자바 7에서 `Throwable`에 추가된 `getSuppressed` 메서드를 이용하면 프로그램 코드에서 가져올 수도 있다.


- 보통의 try-finally 에서처럼 try-with-resource 에서도 catch 절을 쓸 수 있다. 
  ```java
  public static String firstLineOfFile2(String path, String defaultValue) {
      try (BufferedReader br = new BufferedReader(new FileReader(path))) {
          return br.readLine();
      } catch (IOException e) {
          return defaultValue;
      }
  }
  ```
 
<br>

---
#### Reference

- [Should We Close a Java Stream?](https://www.baeldung.com/java-stream-close)
- [JAVA - I.O(입출력) > 기반 스트림](https://memory-develo.tistory.com/61)
- [스트림(stream)](http://www.tcpschool.com/java/java_io_stream)



