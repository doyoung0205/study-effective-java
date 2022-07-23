import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;

/**
 * author : haedoang
 * date : 2022/07/23
 * description :
 */
public class MutablePeriod {
    public final Period period;

    public final Date start;

    public final Date end;

    public MutablePeriod() {
        try {
            ByteArrayOutputStream bos =
                    new ByteArrayOutputStream();
            ObjectOutputStream out =
                    new ObjectOutputStream(bos);
            out.writeObject(new Period(new Date(), new Date()));

            byte[] ref = {0x71, 0, 0x7e, 0, 5};
            bos.write(ref);
            ref[4] = 4;
            bos.write(ref);

            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray())
            );

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


        System.out.println("불변 객체를 깨뜨릴 수 있다");

        pEnd.setYear(78);
        System.out.println(p);

        pEnd.setYear(69);
        System.out.println(p);
    }
}
