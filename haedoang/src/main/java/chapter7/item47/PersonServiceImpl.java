package chapter7.item47;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * author : haedoang
 * date : 2022/05/06
 * description :
 */
public class PersonServiceImpl implements PersonService {
    private final List<Person> persons = Arrays.asList(
            new Person("person1"),
            new Person("person2"),
            new Person("person3"),
            new Person("person4")
    );

    @Override
    public Stream<Person> persons() {
        return persons.stream();
    }

    @Override
    public List<Person> personList() {
        return persons;
    }
}
