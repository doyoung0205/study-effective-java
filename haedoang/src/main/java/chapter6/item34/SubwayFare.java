package chapter6.item34;

/**
 * author : haedoang
 * date : 2022/04/15
 * description :
 */
public enum SubwayFare {
    NORMAL("일반", 1_250, 1_350),
    TEEN("청소년", 720, 1_350),
    CHILD("어린이", 450, 450),
    OTHERS("노인, 장애인, 국가유공자", 0, 0);

    private String desc;
    private int fareByCard;
    private int fareByCash;
    private double earlyBirdFareByCard;
    private double earlyBirdFareByCash;

    private static final double earlyBirdDiscountRate = 20;

    SubwayFare(String desc, int fareByCard, int fareByCash) {
        this.desc = desc;
        this.fareByCard = fareByCard;
        this.fareByCash = fareByCash;
        this.earlyBirdFareByCard = (int) Math.ceil(this.fareByCard * (1 - earlyBirdDiscountRate / 100) / 10) * 10;
        this.earlyBirdFareByCash = (int) Math.ceil(this.fareByCash * (1 - earlyBirdDiscountRate / 100) / 10) * 10;
    }

    public String getDesc() {
        return desc;
    }

    public int getFareByCard() {
        return fareByCard;
    }

    public int getFareByCash() {
        return fareByCash;
    }

    public double getEarlyBirdFareByCard() {
        return earlyBirdFareByCard;
    }

    public double getEarlyBirdFareByCash() {
        return earlyBirdFareByCash;
    }
}
