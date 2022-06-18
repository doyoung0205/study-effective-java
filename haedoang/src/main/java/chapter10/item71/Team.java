package chapter10.item71;

import org.apache.commons.lang3.StringUtils;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public class Team {
    private String name;

    private Team(String name) {
        validate(name);
        this.name = name;
    }

    public static Team valueOf(String name) {
        return new Team(name);
    }

    private void validate(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new MyRuntimeException("팀명은 필수값입니다.");
        }
    }
}
