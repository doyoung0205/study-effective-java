package study.heejin.chapter2.item1;

import static study.heejin.chapter2.item1.CoffeeBean.*;

public class Coffee {
    private String bean;

    public static Coffee espresso(String bean) {
        if (bean.equals(Kenya.name())) {
            return new EspressoKenya();
        }
        if (bean.equals(Brazil.name())) {
            return new EspressoBrazil();
        }
        if (bean.equals(Ethiopia.name())) {
            return new EspressoEthiopia();
        }
        if (bean.equals(Colombia.name())) {
            return new EspressoColombia();
        }
        return new Espresso();
    }
}

class Espresso extends Coffee {
}

class EspressoKenya extends Coffee {
}

class EspressoBrazil extends Coffee {
}

class EspressoEthiopia extends Coffee {
}

class EspressoColombia extends Coffee {
}
