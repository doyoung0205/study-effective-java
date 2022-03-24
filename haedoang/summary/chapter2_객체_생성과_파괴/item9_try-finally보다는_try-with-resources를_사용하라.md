### 아이템9 try-finally 보다는 try-with-resources를 사용하라
 - 자원 회수가 필요한 오브젝트는 예외처리가 매우 중요하다.
 - 하지만 자원 회수 대상이 많아질수록 예외에 대한 처리가 쉽지 않다.
 - 훌륭한 개발자들도 자원 회수 오브젝트에 대해 처리하는 것에서 실수가 발생하곤 한다

#### try-with-resources
 - 위와 같은 단점을 개선하기 위해 블록 내 자원이 사용이 종료될 때 자동으로 회수해준다.
 - 위와 같은 경우 예외 스택을 찾기 어려웠으나(예외를 삼킴) try-with-resources는 중첩없이 다중 예외처리가 가능하다


#### 닫아야하는 객체란 무엇이 있는가 
- Autoclosable을 구현한 객체
- [Stream API](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
Streams have a BaseStream.close() method and implement AutoCloseable, but nearly all stream instances do not actually need to be closed after use.
Generally, only streams whose source is an `IO channel` (such as those returned by Files.lines(Path, Charset)) will require closing.
Most streams are backed by collections, arrays, or generating functions, which require no special resource management. 
(If a stream does require closing, it can be declared as a resource in a try-with-resources statement.)

