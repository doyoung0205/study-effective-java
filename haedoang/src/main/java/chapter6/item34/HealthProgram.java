package chapter6.item34;

import com.google.common.collect.Lists;

import java.util.List;

import static chapter6.item34.HealthProgram.ExerciseType.*;

/**
 * author : haedoang
 * date : 2022/04/15
 * description :
 */
public enum HealthProgram {
    MONDAY(CHEST),
    TUESDAY(BACK),
    WEDNESDAY(LEG),
    THURSDAY(CHEST),
    FRIDAY(BACK),
    SATURDAY(LEG),
    SUNDAY(REST);

    private final ExerciseType exerciseType;

    HealthProgram(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    @Override
    public String toString() {
        return "오늘 할 운동은 " + exerciseType.exercises() + " 입니다.";
    }

    enum ExerciseType {
        CHEST {
            @Override
            List<String> exercises() {
                return Lists.newArrayList("벤치프레스", "인클라인 벤치프레스", "버터플라이", "케이블 크로스오버");
            }
        },
        BACK {
            @Override
            List<String> exercises() {
                return Lists.newArrayList("스미스머신 오버핸드그립 바벨로우", "스미스머신 얼티네이티브 그립 바벨로우", "인버티드 로우", "랫풀 다운");
            }
        },
        LEG {
            @Override
            List<String> exercises() {
                return Lists.newArrayList("스쿼트");
            }
        },
        REST {
            @Override
            List<String> exercises() {
                return Lists.newArrayList("");
            }
        };

        abstract List<String> exercises();
    }
}
