package chapter6.item35;

/**
 * author : haedoang
 * date : 2022/04/16
 * description :
 */
public enum SmartCharacters {
    똘기("자", 1), 떵이("축", 2),

    호치("인", 3), 새초미("묘", 4),

    드라고("진", 5), 요롱이("사", 6),

    마초("오", 7), 미미("미", 8),

    몽키("신", 9), 키키("유", 10),

    강당이("술", 11), 찡찡이("해", 12);

    private String zi;
    private int order;

    SmartCharacters(String zi, int order) {
        this.zi = zi;
        this.order = order;
    }

    public String getZi() {
        return zi;
    }

    public int getOrder() {
        return order;
    }
}
