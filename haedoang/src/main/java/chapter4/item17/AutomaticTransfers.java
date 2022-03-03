package chapter4.item17;

/**
 * author : haedoang
 * date : 2022/03/04
 * description :
 */
public enum AutomaticTransfers {
    보험(200_000),
    통신비(100_000),
    은행이자(150_000),
    가스비(90_000),
    전기세(25_000),
    관리비(50_000);

    private Money price;

    AutomaticTransfers(int price) {
        this.price = Money.valueOf(price);
    }

    public Money getPrice() {
        return price;
    }
}
