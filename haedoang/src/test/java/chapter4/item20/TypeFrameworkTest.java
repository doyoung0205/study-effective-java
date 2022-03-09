package chapter4.item20;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public class TypeFrameworkTest {
    private Singer singer;
    private Songwriter songwriter;
    private SingerSongwriter singerSongwriter;

    @BeforeEach
    void setUp() {
        singer = () -> Singer.NAME + "가 노래를 부릅니다";

        songwriter = () -> Songwriter.NAME + "가 작곡을 합니다";

        singerSongwriter = new SingerSongwriter() {
            @Override
            public String singMySong() {
                return SingerSongwriter.NAME + "가 자신이 작곡한 노래를 부릅니다";
            }

            @Override
            public String sing() {
                return SingerSongwriter.NAME + "가 노래를 부릅니다";
            }

            @Override
            public String compose() {
                return SingerSongwriter.NAME + "가 작곡을 합니다";
            }
        };
    }

    @Test
    @DisplayName("타입 프레임워크 테스트")
    public void typeFrameworkTest() {
        assertThat(singer.sing()).isEqualTo("가수가 노래를 부릅니다");
        assertThat(singer.printTypeName()).isEqualTo("Singer");

        assertThat(songwriter.compose()).isEqualTo("송라이터가 작곡을 합니다");
        assertThat(songwriter.printTypeName()).isEqualTo("Songwriter");

        assertThat(singerSongwriter.sing()).isEqualTo("싱어송라이터가 노래를 부릅니다");
        assertThat(singerSongwriter.compose()).isEqualTo("싱어송라이터가 작곡을 합니다");
        assertThat(singerSongwriter.singMySong()).isEqualTo("싱어송라이터가 자신이 작곡한 노래를 부릅니다")
                .as("Singer,Songwriter를 확장하는 SingerSongWriter는 printTypeName() default method를 가진다")
                .as("확장하려는 타입의 메서드가 충돌(같은 메서드가 2개 이상)될 경우 재정의를 해주어야 한다.");
    }
}
