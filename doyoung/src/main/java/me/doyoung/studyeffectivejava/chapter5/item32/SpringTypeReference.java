package me.doyoung.studyeffectivejava.chapter5.item32;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

public class SpringTypeReference {
    public static void main(String[] args) {

        // 왜 {} 를 붙이는가? -> SuperTypeToken 은 상속과 익명 클래스를 이용한다.
        // 제네릭 타입의 클래스를 익명클래스 인스턴스로 만들어서 상속하고있는
        // 수퍼클래스의 제네릭 타입 파라미터 정보를 전달하기 위한 용도
        ParameterizedTypeReference<?> typeRef = new ParameterizedTypeReference<Set<Integer>>() {
        };

        System.out.println(typeRef.getType());

        RestTemplate rt = new RestTemplate();

        final String url = "http://localhost:8080/users";
//        final List<Map> users = rt.getForObject(url, List.class);
        final List<SuperTypeTokenApplication.User> users = rt.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SuperTypeTokenApplication.User>>() {
        }).getBody();

        users.forEach(System.out::println);
    }
}
