package chapter4.item24;

import java.util.List;

/**
 * packageName : chapter4.item24
 * fileName : Car
 * author : haedoang
 * date : 2022-03-14
 * description :
 */
public class Car {
    private final String modelNo;
    private final String color;
    private final double price;
    private final List<Option> options;

    public Car(String modelNo, String color, double price, List<Option> options) {
        this.modelNo = modelNo;
        this.color = color;
        this.price = price;
        this.options = options;
    }

    public static Car valueOf(String modelNo, String color, double price, List<Option> options) {
        return new Car(modelNo, color, price, options);
    }

    public static class Option {
        private final Long optionId;
        private final String optionName;
        private final double optionPrice;

        private Option(Long optionId, String optionName, double optionPrice) {
            this.optionId = optionId;
            this.optionName = optionName;
            this.optionPrice = optionPrice;
        }

        public double getOptionPrice() {
            return optionPrice;
        }

        public static Option valueOf(Long optionId, String optionName, double optionPrice) {
            return new Option(optionId, optionName, optionPrice);
        }
    }

    public double getPrice() {
        return price + options.stream().mapToDouble(Option::getOptionPrice).sum();
    }
}
