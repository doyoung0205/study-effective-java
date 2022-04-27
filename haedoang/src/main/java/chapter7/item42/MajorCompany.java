package chapter7.item42;

import java.math.BigInteger;

/**
 * author : haedoang
 * date : 2022/04/26
 * description :
 */
public class MajorCompany {
    private BigInteger salary;
    private String name;

    private MajorCompany(BigInteger salary, String name) {
        this.salary = safeInstance(salary);
        this.name = name;
    }

    public static MajorCompany valueOf(BigInteger salary, String name) {
        return new MajorCompany(salary, name);
    }

    private BigInteger safeInstance(BigInteger salary) {
        return salary.getClass() == BigInteger.class ? salary : new BigInteger(salary.toByteArray());
    }

    public BigInteger getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MajorCompany{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                '}';
    }
}
