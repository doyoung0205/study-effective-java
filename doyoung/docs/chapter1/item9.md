## try-finally 보다는 try-with-resources 를 사용하라

자바 라이브러리에서는 close 메서드를 호출해 직접 닫아줘야 하는 지원들이 있다.

GC가 알아서 메모리를 해제해주는데 close() 를 작성해주는 이유는 무엇일까?

1. OS로부터 자원을 할당 받아 사용하는 네이티브 메소드 경우, C에서 자원을 할당 받게 된다.
   <br>따라서 GC가 자원을 할당 받았는지 알 수 가 없다.
2. 메서드를 통해서 자원을 그만쓴다라는 것을 알리고 GC가 메모리를 해제 할 수 있도록 하는 것
3. 명시적으로 수거가 되도록 하는 것.

`InputStream`, `OutputStream`, `java.sql.Connection` 등이 좋은 예시다.

Connection 을 close 하지 않았을 경우, 

계속 다른 Connection 을 사용하고 Threads_connected 가 최대 사용량을 넘어가 오류가 발생하게 된다.

- [Connection close 를 하지 않았을 경우](https://codingdog.tistory.com/entry/jdbc%EC%97%90%EC%84%9C-connection%EC%9D%84-close-%ED%95%B4-%EC%A3%BC%EC%A7%80-%EC%95%8A%EC%95%98%EC%9D%84-%EB%95%8C-%EC%96%B4%EB%96%A4-%EC%9D%BC%EC%9D%B4-%EC%9D%BC%EC%96%B4%EB%82%98%EB%8A%94%EC%A7%80-%EC%8B%A4%EC%8A%B5%ED%95%B4-%EB%B4%85%EC%8B%9C%EB%8B%A4)
#### 외부 자원(DB, 파일)을 쓰는 객체들은 꼭 닫아주자 ! (AutoCloseable)


Suppress : 억압하다. 진압하다.

억제된 예외(배열)를 반환:
```

 private void printStackTrace(PrintStreamOrWriter s) {
     // Guard against malicious overrides of Throwable.equals by
     // using a Set with identity equality semantics.
     Set<Throwable> dejaVu = Collections.newSetFromMap(new IdentityHashMap<>());
     dejaVu.add(this);

     synchronized (s.lock()) {
         // Print our stack trace
         s.println(this);
         StackTraceElement[] trace = getOurStackTrace();
         for (StackTraceElement traceElement : trace)
             s.println("\tat " + traceElement);

         // Print suppressed exceptions, if any
         for (Throwable se : getSuppressed())
             se.printEnclosedStackTrace(s, trace, SUPPRESSED_CAPTION, "\t", dejaVu);

         // Print cause, if any
         Throwable ourCause = getCause();
         if (ourCause != null)
             ourCause.printEnclosedStackTrace(s, trace, CAUSE_CAPTION, "", dejaVu);
     }
 }

```


### cf.  @SuppressWarnings 는 무엇일까?

위의 예외랑은 완전 다름. 주로 경고를 무시할 때 사용 !

- [참고](https://ktko.tistory.com/entry/Java%EC%9D%98-SuppressWarnings-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)
