package chapter5.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * author : haedoang
 * date : 2022/03/19
 * description :
 */
public class Group {
    private Collection members = new ArrayList();

    public void addMember(Object member) {
        this.members.add(member);
    }

    public void printMembers() {
        for (Iterator i = members.stream().iterator(); i.hasNext();) {
            Member member  = (Member) i.next();
            System.out.println(member);
        }
    }
}
