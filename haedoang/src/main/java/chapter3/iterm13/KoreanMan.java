package chapter3.iterm13;

/**
 * packageName : chapter3.iterm13
 * fileName : KoreanPerson
 * author : haedoang
 * date : 2022-02-25
 * description :
 */
public class KoreanMan extends Man {
    private String favoriteFood;

    public KoreanMan(String name, int age, String favoriteFood) {
        super(name, age);
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void speak() {
        System.out.println("안녕하십니까요");
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }
}
