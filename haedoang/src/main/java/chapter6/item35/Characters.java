package chapter6.item35;

/**
 * author : haedoang
 * date : 2022/04/16
 * description :
 */
public enum Characters {
    똘기, 떵이, 호치, 새초미,
    드라고, 요롱이, 마초, 미미,
    몽키, 키키, 강당이, 찡찡이;

    //anti
    public int numberOfCharacters() {
        return ordinal() + 1;
    }
}
