## finalizer 와 cleaner 사용을 피하라

#### finalize()의 문제점

1. 언제 실행이 될지 모릅니다.
2. GC에 따라 실행이 되지 않을 수 있습니다.
3. 예외가 발생되면 무시됩니다.
4. 성능 저가하 발생할 수 있습니다.
5. finalizer 공격에 노출되어 심각한 보완 문제를 일으킬 수 있다.
   - 생성자 예외, finalize 메서드 final

#### AutoCloseable

: 아이템 9 참고

#### finalize, cleaner 적합한 예시

- 네이티브 피어 : 일반 자바 객체가 네이티브 메서드를 통해 기능 수행을 위임하는 네이티브 객체를 말한다.
- 자원의 소유자가 close 메서드를 호출하지 않는 것에 대한 안전망 (즉시 실행되지는 않더라도 늦게라도 해주는게 낫다.)





