### 아이템6 불필요한 객체 생성을 피하라
- 객체 생성에는 비용이 많이 든다. 
- 불필요한 객체는 가비지 컬렉터의 수거 대상이 되지만 규모가 큰 프로젝트의 경우 불필요한 메모리 사용으로 인한 서버 성능 저하를 발생할 수 있다.
- 불필요한 객체 생성을 줄여서 성능을 개선할 수 있다.

#### 불필요한 객체란 ?
- String 클래스는 String constant pool 영역에 생성되고 공유된다. 따라서 new String()을 객체를 생성하여 heap 영역으로 할당하는 것은 비효율 적이다.
- Pattern.match 클래스 사용
      - item6.NumberUtilTest  
      - compile 정적 메서드를 호출할 떄마다 객체 내부에서 Pattern 객체를 생성한다
  ```java
  public class Pattern {
      // ...
        
      public static boolean matches(String regex, CharSequence input) {
          Pattern p = Pattern.compile(regex);
          Matcher m = p.matcher(input);
          return m.matches();
      }
     
      public static Pattern compile(String regex){
              return new Pattern(regex,0); // 호출 시 생성된다
      }
  }
  ```
- 오토박싱은 객체 타입에 값을 할당할 경우 새로운 객체를 생성해야 하기 때문에 성능저하를 발생한다.
  - item6.AutobixingTest 

#### 용어 정리
 - 어댑터(또는 뷰):
   - 실제 작업은 뒷단 객체에 위임하고, 자신은 제 2의 인터페이스 역할을 해주는 객체. 
   - 뒷단 객체 외에는 관리할 상태가 없으므로 뒷단 객체 하나당 어댑터 하나씩만 만들어지면 된다
   - 스프링 프레임워크 HandlerAdapter 예시(아래 참고)

#### 스프링 프레임워크 HandlerAdapter
![image_info](../images/dispatcher-servlet.png)
(이미지 출처: [https://www.programmerall.com/article/9264417519/](https://www.programmerall.com/article/9264417519/))
1) DispatcherServlet이 Http 요청을 수신한다.
2) http요청을 처리할 handler를 찾기 위해 HandlerMapping을 가져온다
  - HandlerMapping은 request를 처리할 적절한 Controller를 선택하는 기능을 담당한다.
  - Spring 기본으로 지원하는 HandlerMapping(아래)
  - HandlerMapping 은 우선순위로 가진다. (Order)
    ```text
       0 = {RequestMappingHandlerMapping@7589} // 어노테이션 기반, @RequestMapping
       1 = {BeanNameUrlHandlerMapping@7590}    // 빈 이름으로 url 매핑할 핸들러 찾음
       2 = {RouterFunctionMapping@7591}
       3 = {SimpleUrlHandlerMapping@7592}
       4 = {WelcomePageHandlerMapping@7593}
    ```
3) 처리할 handler를 통해 handler를 실행하는 HandlerAdapter를 찾는다
   - DispatcherServlet은 controller 비즈니스 로직 실행 작업을 `HandlerAdapter` 에게 위임한다.
   - Spring 기본으로 지원하는 HandlerAdapter(아래)
   - HandlerAdapter 은 우선순위를 가진다.(Order)
   ```text
      0 = {RequestMappingHandlerAdapter@7705}  // 어노테이션 기반, @RequestMapping
      1 = {HandlerFunctionAdapter@7706}
      2 = {HttpRequestHandlerAdapter@7707}
      3 = {SimpleControllerHandlerAdapter@7708}
   ```
4) HanderAdapter가 controller의 비즈니스 로직을 수행한 결과를 `ModelAndView` 객체에 담아 반환한다
5) DispatcherServlet은 `ViewResolver`를 통해 결과를 보여줄 view를 선택한다
6) view 객체를 통해 요청에 대한 response를 client에게 전달한다 

#### RequestMappingHandlerAdapter 역할
- request를 처리할 handler에게 처리를 위임한다
- RequestMappingHandlerAdapter는 request요청에 대해 RequestMappingHandler를 실행하는 역할을 한다
- DispatherServlet은 요청 메서드를 직접호출하지 않고 handlerAdapter를 통해 handler를 실행한다

- DispatcherServlet 내 HandlerAdapter 
    ```java
        public class DispatcherServlet extends FrameworkServlet {
        // ...
        // Actually invoke the handler. line 1066
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler()); // controller 의 비즈니스 로직을 handler에게 처리하게 한다
    }
- HandlerAdapter: handler 객체 사이의 다리역할을 한다
    ```java
        public interface HandlerAdapter {
        ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
    }
    ```

### 정리 
 - 불필요한 객체 생성이 있는지 확인하고 호출되는 객체의 경우 `static 변수`로 선언한다.
 - 불필요한 오토박싱을 지양한다.
