package chapter12.item87;

import java.io.Serializable;
import java.util.Objects;

/**
 * author : haedoang
 * date : 2022/07/16
 * description :
 */
public class Name implements Serializable {

    /**
     * 성. not null
     * @serial
     */
    private final String lastName;
    /**
     * 이름. not null
     * @serial
     */
    private final String firstName;

    /**
     * 중간이름. nullable
     * @serial
     */
    private final String middleName;

    private Name(String lastName, String firstName, String middleName) {
        this.lastName = Objects.requireNonNull(lastName);
        this.firstName = Objects.requireNonNull(firstName);
        this.middleName = middleName;
    }

    public static Name valueOf(String lastName, String firstName, String middleName) {
        return new Name(lastName, firstName, middleName);
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(getLastName(), name.getLastName()) && Objects.equals(getFirstName(), name.getFirstName()) && Objects.equals(getMiddleName(), name.getMiddleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastName(), getFirstName(), getMiddleName());
    }
}
