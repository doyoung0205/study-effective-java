package study.heejin.chapter5.item33;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class TypeReference<T> {

    public Type type;

    public TypeReference() {
        Type sType = getClass().getGenericSuperclass();

        if (sType instanceof ParameterizedType) {
            this.type = ((ParameterizedType) sType).getActualTypeArguments()[0];

        } else {
            throw new RuntimeException("타입 정보가 올바르지 않습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) {
            return false;
        }
        TypeReference<?> that = (TypeReference<?>) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    public static void main(String[] args) {
        // 예외 발생. ParameterizedType이 아니다.
        // TypeReference t = new TypeReference<String>();

        TypeReference t = new TypeReference<String>() {};
        System.out.println("type = " + t.type);
    }
}
