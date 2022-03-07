package chapter4.item20;

/**
 * author : haedoang
 * date : 2022/03/07
 * description :
 */
public interface SingerSongwriter extends Singer, Songwriter {
    String NAME = "싱어송라이터";
    String singMySong();

    @Override
    default String printTypeName() {
        //return Singer.super.printTypeName();
        return "SingSongwriter";
    }
}
