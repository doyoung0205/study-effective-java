package chapter8.item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * packageName : chapter8.item54
 * fileName : Team
 * author : haedoang
 * date : 2022-05-24
 * description :
 */
public class Team {
    private final String name;
    private final List<Member> members;

    private Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public static Team valueOf(String name) {
        return new Team(name);
    }

    public void addMembers(List<Member> members) {
        this.members.addAll(members);
    }

    public List<Member> getMembers() {
        return members.isEmpty() ? null : members;
    }

    public List<Member> getMembersIfEmptyThenReturnEmptyCollection() {
        return new ArrayList<>(members);
    }

    public List<Member> getMembersRefactoring() {
        return members.isEmpty() ? Collections.emptyList() : members;
    }
}
