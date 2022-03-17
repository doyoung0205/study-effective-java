package chapter4.item24;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName : chapter4.item24
 * fileName : Car
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
public class CarWithInnerClass {
    private final String modelNo;
    private final String color;
    private final double price;
    private final List<Option> options = new ArrayList<>();

    public CarWithInnerClass(String modelNo, String color, double price) {
        this.modelNo = modelNo;
        this.color = color;
        this.price = price;
    }

    public void addOption(Long optionId, String optionName, double optionPrice) {
        Option option = new Option(optionId, optionName, optionPrice);
        this.options.add(option);
    }

    public static CarWithInnerClass valueOf(String modelNo, String color, double price) {
        return new CarWithInnerClass(modelNo, color, price);
    }

    public class Option {
        private final Long optionId;
        private final String optionName;
        private final double optionPrice;

        public Option(Long optionId, String optionName, double optionPrice) {
            this.optionId = optionId;
            this.optionName = optionName;
            this.optionPrice = optionPrice;
        }

        public double getOptionPrice() {
            return optionPrice;
        }
    }

    public double getPrice() {
        return price + options.stream().mapToDouble(Option::getOptionPrice).sum();
    }
}
