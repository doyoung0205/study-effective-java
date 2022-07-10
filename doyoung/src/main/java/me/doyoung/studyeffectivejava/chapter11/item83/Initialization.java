package me.doyoung.studyeffectivejava.chapter11.item83;

// 다양한 초기화 방식 (443-445쪽)
public class Initialization {

    // 코드 83-1 인스턴스 필드를 초기화하는 일반적인 방법 (443쪽)
    private final FieldType field1 = computeFieldValue();

    // 코드 83-2 인스턴스 필드의 지연 초기화 - synchronized 접근자 방식 (443쪽)
    private FieldType field2;
    private synchronized FieldType getField2() {
        if (field2 == null)
            field2 = computeFieldValue();
        return field2;
    }

    // 코드 83-3 정적 필드용 지연 초기화 홀더 클래스 관용구 (443쪽)
    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }

    private static FieldType getField() { return FieldHolder.field; }


    // 코드 83-4 인스턴스 필드 지연 초기화용 이중검사 관용구 (444쪽)
    private volatile FieldType field4;

    private FieldType getField4() {
        FieldType result = field4;
        if (result != null)    // 첫 번째 검사 (락 사용 안 함)
            return result;

        synchronized(this) {
            if (field4 == null) // 두 번째 검사 (락 사용)
                field4 = computeFieldValue();
            return field4;
        }
    }


    // 코드 83-5 단일검사 관용구 - 초기화가 중복해서 일어날 수 있다! (445쪽)
    private volatile FieldType field5;

    private FieldType getField5() {
        FieldType result = field5;
        if (result == null)
            field5 = result = computeFieldValue();
        return result;
    }

    private static FieldType computeFieldValue() {
        return new FieldType();
    }
}
