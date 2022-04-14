package chapter5.item33;

import org.springframework.core.ParameterizedTypeReference;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author : haedoang
 * date : 2022/04/09
 * description :
 */
public class SpringTypeReference {
    public static void main(String[] args) {
        org.springframework.core.ParameterizedTypeReference type =
                new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>() {};

        System.out.println(type.getType());
    }
}
