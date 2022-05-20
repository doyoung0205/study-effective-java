package study.heejin.chapter8.item52;

import java.util.List;

public class Overriding {
    public static void main(String[] args) {
        List<Wine> wines = List.of(new Wine(), new SpaklingWine(), new Champagne());

        for (Wine wine : wines) {
            System.out.println(wine.name());
        }
    }
}

class Wine {
    String name() {
        return "포도주";
    }
}

class SpaklingWine extends Wine {
    @Override
    String name() {
        return "발포성 포도주";
    }
}

class Champagne extends Wine {
    @Override
    String name() {
        return "샴페인";
    }
}


