package chapter5.item26;

import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/03/20
 * description :
 */
public class Item26Test {
    @Test
    @DisplayName("로 타입은 오류를 발생할 우려가 있다")
    public void memberAdd() {
        // given
        final Group group = new Group();

        // when
        group.addMember(Member.valueOf("김해동"));
        group.addMember("파이팅하자!");

        // then
        assertThatThrownBy(() -> group.printMembers())
                .isInstanceOf(ClassCastException.class)
                .as("로 타입의 경우 런타임 시점에서 문제를 파악할 수 있다.");
    }

    @Test
    @DisplayName("매개변수화된 컬렉션 타입으로 타입을 보장할 수 있다")
    public void parameterizedCollection() {
        // given
        final NewGroup newGroup = new NewGroup();

        // when
        newGroup.addMember(Member.valueOf("김해동"));
        newGroup.addMember(Member.valueOf("두아리파"));

        // then
        newGroup.printMembers();
    }

    @Test
    @DisplayName("로타입을 사용하여 원소 내 존재하는 지 확인하는 테스트")
    public void numElementsInCommonTest1() {
        // given
        Set<Member> memberSet = new HashSet<>();
        memberSet.add(Member.valueOf("김해동"));
        memberSet.add(Member.valueOf("두아리파"));

        Set<Member> set = new HashSet<>();
        set.add(Member.valueOf("김해동"));

        // when
        final int actual = CollectionUtil.numElementsInCommon(set, memberSet);

        // then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("로타입을 사용하는 경우 타입 불변식을 훼손하기 쉽다.")
    public void numElementsInCommonTest2() {
        // given
        Set set1 = new HashSet<>();
        set1.add(Member.valueOf("김해동"));
        set1.add("두아리파");

        Set set2 = new HashSet();
        set2.add("할수있어!");
        set2.add(false);

        // when
        final int actual = CollectionUtil.numElementsInCommon(set1, set2);

        // then
        assertThat(actual).isEqualTo(0)
                .as("컴파일은 가능하지만 동일 타입을 보장하지 못하므로 안전하지 못하다.");
    }

    @Test
    @DisplayName("와일드 카드 타입으로 타입을 한정할 수 있다.")
    public void numElementsInCommonUseWildType() {
        // given
        Set<Member> memberSet = new HashSet<>();
        memberSet.add(Member.valueOf("김해동"));
        memberSet.add(Member.valueOf("두아리파"));

        Set<Member> set = new HashSet<>();
        set.add(Member.valueOf("김해동"));

        // when
        final int actual = CollectionUtil.numElementsInCommonWildcardType(memberSet, set);

        // then
        assertThat(actual).isEqualTo(1)
                .as("비한정적 와일드카드 타입으로 타입의 안정성을 보장한다.");

        // given
        final HashSet<String> 가고싶은기업목록 = Sets.newHashSet("네이버", "카카오", "하이퍼커넥트", "당근마켓", "오늘의집", "쏘카");
        final HashSet<String> 채용기업목록 = Sets.newHashSet("쏘카", "무신사", "카카오뱅크");

        // when
        final int actual2 = CollectionUtil.numElementsInCommonWildcardType(채용기업목록, 가고싶은기업목록);

        // then
        assertThat(actual2).isEqualTo(1);
    }

    @Test
    @DisplayName("Set<String>은 Set<Object>의 하위타입이 아니다")
    public void ruleOfGenericType() {
        // given
        Set<Object> todos1 = Sets.newHashSet("코딩테스트연습");
        Set<String> todos2 = Sets.newHashSet("빨래", "청소", "운동");

        // when
        //CollectionUtil.mergeObjectSet(todos1, todos2); //compile error

        // when
        final Set<Object> actual = CollectionUtil.mergeObjectSet(todos1, todos1);

        // then
        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("제네릭은 런타임 시점에서 제네릭 타입 정보가 지워진다")
    public void genericTypeOnRuntime() {
        // given
        final List<String> todos = Arrays.asList("빨래", "청소", "코테풀기");
        final List<Integer> lottoNumbers = Arrays.asList(1, 8, 11, 16, 18, 32);


        // then
        assertThat(todos.getClass() == lottoNumbers.getClass()).isTrue();
    }
}
