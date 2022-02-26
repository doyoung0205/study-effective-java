package chapter3.iterm13;

/**
 * packageName : chapter3.iterm13
 * fileName : JapanesePerson
 * author : haedoang
 * date : 2022-02-25
 * description :
 */
public class JapaneseWomen extends Women {
    private String favoriteMusic;

    public JapaneseWomen(String name, int age, String favoriteMusic) {
        super(name, age);
        this.favoriteMusic = favoriteMusic;
    }

    @Override
    public void speak() {
        System.out.println("와카리마시타");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getFavoriteMusic() {
        return favoriteMusic;
    }
}
