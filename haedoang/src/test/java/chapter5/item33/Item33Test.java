package chapter5.item33;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * author : haedoang
 * date : 2022/04/08
 * description :
 */
public class Item33Test {

    static Annotation getAnnotation(AnnotatedElement element, String annotationTypeName) {
        Class<?> annotationType = null;
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }

        return element.getAnnotation(
                annotationType.asSubclass(Annotation.class)
        );
    }

    @Test
    @DisplayName("타입 토큰을 저장하는 이종 컨테이너 테스트")
    public void favoriteTest() {
        // given
        final Favorites favorites = new Favorites();

        // when
        favorites.putFavorite(String.class, "안녕하세요");
        favorites.putFavorite(Integer.class, 3);
        favorites.putFavorite(List.class, Lists.newArrayList(1, 2, 3));
        favorites.putFavorite(List.class, Lists.newArrayList("overwrite", "되어야", "한다"));
        favorites.putFavorite(Class.class, Favorites.class);


        // then
        assertThat(favorites.size()).isEqualTo(4);

        // when
        final String actual = favorites.getFavorite(String.class);
        final Class<?> actual2 = favorites.getFavorite(Class.class);

        // then
        assertThat(actual).isEqualTo("안녕하세요");
        assertThat(actual2.getSimpleName()).isEqualTo("Favorites");
    }

    @Test
    @DisplayName("로 타입을 사용하면 안정성을 깨뜨릴 수 있다")
    public void breakSafetyOnFavorites() {
        // given
        Favorites favorites = new Favorites();

        // when
        favorites.putFavorite((Class) Integer.class, "타입 안정성을 깨뜨릴 수 있음");

        // then
        assertThatThrownBy(() -> {
            final Integer actual = favorites.getFavorite(Integer.class);

        }).isInstanceOf(ClassCastException.class)
                .as("힙 오염을 발생시킬 수 있음");

        // when
        assertThatThrownBy(() -> {
            favorites.putFavoriteCast((Class) Integer.class, "타입 안정성을 깨뜨릴 수 있음");
        }).isInstanceOf(ClassCastException.class)
                .as("컬렉션에 객체를 넣기 전에 타입을 검사하면 오류를 미리 검증할 수 있다");
    }

    @Test
    @DisplayName("한정적 타입 토큰 테스트")
    public void annotationApi() {
        // when
        final Annotation actual = getAnnotation(Favorites.class, "chapter5.item33.Cool");

        // then
        assertThat(actual.annotationType()).isEqualTo(Cool.class)
                .as("프록시 객체인 어노테이션을 asSubclass를 통해 런타임 시점에서 타입을 읽어낼 수 있다");
    }
}
