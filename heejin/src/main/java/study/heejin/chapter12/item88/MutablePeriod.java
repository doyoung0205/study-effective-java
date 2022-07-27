package study.heejin.chapter12.item88;

import java.io.*;
import java.util.Date;

public class MutablePeriod implements Serializable {
    public final Period period;

//    public final Date start;
//    public final Date end;
    public Date start;
    public Date end;

    public MutablePeriod() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);

            // 유효한 Period 인스턴스를 직렬화한다.
            out.writeObject(new Period(new Date(), new Date()));

            // 악의적으로 내부 Date 필드로의 참조를 추가한다.
            byte[] ref = {0x71, 0, 0x7e, 0, 5}; // 참조 #5
            bos.write(ref); // 시작(start) 필드
            ref[4] = 4; // 참조 #4
            bos.write(ref); // 종료(end) 필드

            // Period 역직렬화 후 Date 참조를 훔친다.
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));

            period = (Period) in.readObject();
            start = (Date) in.readObject();
            end = (Date) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        MutablePeriod mp = new MutablePeriod();
        final Period p = mp.period;
        final Date pEnd = mp.end;

        pEnd.setYear(78);
        System.out.println(p);

        pEnd.setYear(69);
        System.out.println(p);
    }
}
