package study.heejin.chapter4.item17;

public class Member {
    private final String name;
    private final Address address;

    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
