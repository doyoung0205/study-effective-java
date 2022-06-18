package chapter10.item72;

/**
 * author : haedoang
 * date : 2022/06/18
 * description :
 */
public enum Position {
    GK, FW, DF, MF;

    public boolean isGk() {
        return this == GK;
    }

    public boolean isFw() {
        return this == FW;
    }

    public boolean isDf() {
        return this == DF;
    }

    public boolean isMf() {
        return this == MF;
    }
}
