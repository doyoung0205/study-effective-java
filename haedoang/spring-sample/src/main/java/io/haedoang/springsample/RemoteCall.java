package io.haedoang.springsample;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * author : haedoang
 * date : 2022/04/09
 * description :
 */
public class RemoteCall {
    public static void main(String[] args) {
        RestTemplate rt = new RestTemplate();

//        final List<Application.User> users = rt.getForObject("http://localhost:8080", List.class); //ClassCastException
//class java.util.LinkedHashMap cannot be cast to class io.haedoang.springsample.Application$User

        final List<Application.User> users = rt.exchange("http://localhost:8080", HttpMethod.GET, null, new ParameterizedTypeReference<List<Application.User>>() {
        }).getBody();

        users.forEach(System.out::println);
    }
}
