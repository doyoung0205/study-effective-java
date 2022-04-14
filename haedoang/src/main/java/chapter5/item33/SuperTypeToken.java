package chapter5.item33;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * author : haedoang
 * date : 2022/04/09
 * description :
 */
public class SuperTypeToken {
    static class Sup<T> {
        T value;
    }

    static class Sub extends Sup<String> {
    }

    static class Sub2 extends Sup<List<Integer>> {
    }

    static class TypeReference<T> {
        Type type;

        public TypeReference() {
            final Type stype = getClass().getGenericSuperclass();
            if (stype instanceof ParameterizedType) {
                this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;
            TypeReference<?> that = (TypeReference<?>) o;
            return Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }
    }

    static class TypesafeMap {
        Map<TypeReference<?>, Object> map = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value) {
            map.put(tr, value);
        }

        <T> T get(TypeReference<T> tr) {
            if (tr.type instanceof Class<?>) {
                return ((Class<T>) tr.type).cast(map.get(tr));
            } else {
                return ((Class<T>) ((ParameterizedType) tr.type).getRawType()).cast(map.get(tr));
            }

        }
    }
//
//    public static void main(String[] args) throws NoSuchFieldException {
//        //LOCAL CLASS
//        class Sub3 extends Sup<List<Boolean>> {
//        }
//
//        //익명 클래스
//        Sup b4 = new Sup<List<Void>>() {
//        };
//
//        // 런타임 시 정의된 타입이 사라진다
//        Sup<String> s = new Sup<>();
//        System.out.println(s.getClass().getDeclaredField("value").getType()); //Object
//
//        // 제네릭 타입을 받는 슈퍼클래스를 상속하면서 타입을 정의할 경우
//        // 리플렉션을 통해 런타임 시 전달되어 바이트 코드에 남게 된다
//        Sub b = new Sub();
//        final Type t = b.getClass().getGenericSuperclass(); //Sup<String>
//        ParameterizedType ptype = (ParameterizedType) t;
//        System.out.println(ptype.getActualTypeArguments()[0]); //실제 타입 인자를 받아올 수 있다
//
//        //COLLECTION
//        Sub2 b2 = new Sub2();
//        final Type t2 = b2.getClass().getGenericSuperclass();
//        ParameterizedType ptype2 = (ParameterizedType) t2;
//        System.out.println(ptype2.getActualTypeArguments()[0]);
//
//        //LOCAL CLASS
//        Sub3 b3 = new Sub3();
//        final Type t3 = b3.getClass().getGenericSuperclass();
//        ParameterizedType ptype3 = (ParameterizedType) t3;
//        System.out.println(ptype3.getActualTypeArguments()[0]);
//
//
//        //ANONYMOUS
//        final Type t4 = b4.getClass().getGenericSuperclass();
//        ParameterizedType ptype4 = (ParameterizedType) t4;
//        System.out.println(ptype4.getActualTypeArguments()[0]);
//    }

    public static void main(String[] args) {

        //////////////////////////////////////////////////////////////

//        TypeReference tr = new TypeReference<String>(); // NG

        TypeReference tr = new TypeReference<String>() {
        };

        TypesafeMap m = new TypesafeMap();
        m.put(new TypeReference<>() {
        }, 1);
        m.put(new TypeReference<>() {
        }, "String");
        m.put(new TypeReference<List<Integer>>() {
        }, Arrays.asList(1, 2, 3));

        m.put(new TypeReference<List<String>>() {
        }, Arrays.asList("A", "B", "C"));

        System.out.println(m.get(new TypeReference<Integer>() {
        }));
        System.out.println(m.get(new TypeReference<String>() {
        }));
        System.out.println(m.get(new TypeReference<List<Integer>>() {
        }));
        System.out.println(m.get(new TypeReference<List<String>>() {
        }));
    }
}
