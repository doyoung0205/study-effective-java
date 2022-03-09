package chapter4.item20;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public interface Songwriter {
    String NAME = "송라이터";
    String compose();

    default String printTypeName() {
        return "Songwriter";
    }
}
