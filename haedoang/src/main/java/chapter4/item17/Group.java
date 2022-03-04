package chapter4.item17;

import java.util.ArrayList;
import java.util.List;

/**
 * author : haedoang
 * date : 2022/03/03
 * description :
 */
public class Group {
    private List<Member> members;

    private Group(List<Member> members) {
        validate(members);
        this.members = new ArrayList<>(members);
    }

    public static Group valueOf(List<Member> members) {
        return new Group(members);
    }

    private void validate(List<Member> members) {
        if (members.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Long id) {
        this.members.removeIf(member -> member.getId().equals(id));
    }

    public int countMember() {
        return members.size();
    }
}
