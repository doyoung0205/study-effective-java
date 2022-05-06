package chapter7.item47;

import java.util.List;
import java.util.stream.Stream;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public interface PersonService {

    Stream<Person> persons();

    List<Person> personList();
}
