package chapter4.item20;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public interface Singer {
    String NAME = "가수";
    String sing();

//    default void printTypeName() {
//        System.out.println("Singer");
//    }
    default String printTypeName() {
        return "Singer";
    }
}
