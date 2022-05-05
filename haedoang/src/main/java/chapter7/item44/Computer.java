package chapter7.item44;

import java.util.Map;

/**
 * author : haedoang
 * date : 2022/05/02
 * description :
 */
public class Computer {
    private Map<String, String> computerParts;

    public Computer(Map<String, String> computerParts) {
        this.computerParts = computerParts;
    }

    public Map<String, String> getComputerParts() {
        return computerParts;
    }
}
