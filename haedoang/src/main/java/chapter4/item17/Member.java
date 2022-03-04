package chapter4.item17;

/**
 * author : haedoang
 * date : 2022/03/03
 * description :
 */
public final class Member {
    private final Long id;
    private final String name;

    private Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Member valueOf(Long id, String name) {
        return new Member(id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
