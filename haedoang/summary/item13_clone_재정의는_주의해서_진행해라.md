### item13 clone 재정의는 주의해서 진행해라
 - clone은 객체를 복제하는 개념이다. clone의 메서드의 Object 명세는 아래와 같다
   - x.clone != x
   - x.clone.getClass() == x.getClass()
   - x.clone.equals(x)
   - x.clone.getClass() == x.getClass()
 - 상속 관계에서의 clone은 사용하지 말아야 한다
   - 상위 클래스에서 override한 clone 메서드가 new 생성자를 반환하는 경우(clone()은 기능적으로 new 생성자와 동일하기 때문에)
 - Cloneable 아키텍처는 '가변 객체를 참조하는 필드는 final로 선언하라'는 일반 용법과 충돌한다
   - Cloneable에서 가변 객체를 final로 선언할 경우 복제본과 동일한 객체를 바라볼 수 있기 때문
 - Cloneable 은 동기화를 해주어야 한다(Object clone 메서드가 동기화를 고려하지 않았기 때문)
   ```java 
   public Hashtable<K,V>
       extends Dictionary<K,V>
       implements Map<K,V>, Cloneable, java.io.Serializable  {
       ...
       public synchronized Object clone() {
           Hashtable<?,?> t = cloneHashtable();
           t.table = new Entry<?,?>[table.length];
           for (int i = table.length ; i-- > 0 ; ) {
               t.table[i] = (table[i] != null)
                   ? (Entry<?,?>) table[i].clone() : null;
           }
           t.keySet = null;
           t.entrySet = null;
           t.values = null;
           t.modCount = 0;
           return t;
       }
   }
   ```
 - 복사 생성자와 복사 팩터리를 사용하는 것이 낫다. 
   - 동기화 처리는 필요하다