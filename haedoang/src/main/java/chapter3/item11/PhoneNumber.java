package chapter3.item11;

import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/02/22
 * description :
 */
public class PhoneNumber implements Cloneable {
    private final short areaCode;
    private final short prefix;
    private final short lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(areaCode, prefix, lineNum);
        int result = Short.hashCode(areaCode);
        result += 31 * result + Short.hashCode(prefix);
        result += 31 * result + Short.hashCode(lineNum);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }

    @Override
    protected chapter3.item10.PhoneNumber clone() throws CloneNotSupportedException {
        try {
            return (chapter3.item10.PhoneNumber) super.clone();
        } catch(CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
