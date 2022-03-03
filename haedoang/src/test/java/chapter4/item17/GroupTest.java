package chapter4.item17;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static chapter4.item17.AutomaticTransfers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * author : haedoang
 * date : 2022/03/03
 * description :
 */
class GroupTest {

    @Test
    @DisplayName("그룹은 회원을 들록할 수 있다")
    public void addMember() {
        // given
        final ArrayList<Member> members =
                Lists.newArrayList(Member.valueOf(1L, "A"), Member.valueOf(2L, "B"));

        // when
        final Group group = Group.valueOf(members);

        // then
        assertThat(group.countMember()).isEqualTo(2);

        // when
        group.addMember(Member.valueOf(3L, "C"));

        // then
        assertThat(group.countMember()).isEqualTo(3);
    }
}


