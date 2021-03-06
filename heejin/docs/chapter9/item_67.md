# 일반적인 프로그래밍 원칙

[아이템 67. 최적화는 신중히 하라](#최적화는-신중히-하라)  
[- 설계 단계에서 성능을 위해 고려할 점](#설계-단계에서-성능을-위해-고려할-점)  
[- 프로파일링 도구](#프로파일링-도구)  
[- 성능 측정](#성능-측정)  
[- 정리](#정리)  

<br>

## 최적화는 신중히 하라
- 최적화는 좋은 결과보다는 해로운 결과로 이어지기 쉽고, 섣불리 진행하면 특히 더 그렇다.
- 성능 때문에 견고한 구조를 희생하지 말자.
- **빠른 프로그램보다는 좋은 프로그램을 작성하라.**
- 좋은 프로그램이지만 원하는 성능이 나오지 않는다면 그 아키텍처 자체가 최적화할 수 있는 길을 안내해줄 것이다.
  - 좋은 프로그램은 **정보 은닉 원칙**을 따르므로 개별 구성요소의 내부를 **독립적**으로 설계할 수 있다.
  - 따라서 시스템의 나머지 부분에 영향을 주지 않고도 각 요소를 다시 설계할 수 있다. _(→ item 15)_
  - 구현상의 문제는 나중에 최적화해 해결할 수 있지만, 아키텍처의 결함이 성능을 제한하는 상황이라면 시스템 전체를 다시 작성하지 않고는 해결하기 불가능할 수 있다.


### 설계 단계에서 성능을 위해 고려할 점
- 성능을 제한하는 설계를 피하라.
  - 완성 후 변경하기가 가장 어려운 설계 요소는 바로 컴포넌트끼리, 혹은 외부 시스템과의 소통 방식이다.
  - API, 네트워크 프로토콜, 영구 저장용 데이터 포맷 등이 대표적이다.
- API를 설계할 때 성능에 주는 영향을 고려하라.
  - 내부 데이터를 변경할 수 있게 만들면 불필요한 방어적 복사를 수없이 유발할 수 있다.
  - 컴포지션으로 해결할 수 있음에도 상속 방식으로 설계한 클래스는 상위 클래스에 종속되며, 성능에도 영향을 받는다.
- 성능을 위해 API를 왜곡하는 건 매우 안 좋은 생각이다.


### 프로파일링 도구
- 프로파일링 도구는 최적화 노력을 어디에 집중해야 할지 찾는데 도움을 준다.
  - 개별 메서드의 소비 시간과 호출 횟수 같은 런타임 정보를 제공한다.
  - 집중할 곳과 알고리즘을 변경해야 한다는 사실을 알려주기도 한다.
- jmh
  - 프로파일러는 아니지만 자바 코드의 상세한 성능을 알기 쉽게 보여주는 마이크로 벤치마킹 프레임워크다.


### 성능 측정
- 최적화 시도 전후의 성능 측정은 중요하다.
- 자바의 성능 모델은 정교하지 않을뿐더라 구현 시스템, 릴리스, 프로세서마다 차이가 있다.
- 프로그램이 여러 가지 자바 플랫폼이나 여러 하드웨어 플랫폼에서 구동한다면 최적화의 효과를 각각 측정해야 한다.
- 그러다 보면 다른 구현 혹은 하드웨어 플랫폼 사이에서 성능을 타협해야 하는 상황도 마주할 것이다.


### 정리
- 좋은 프로그램을 작성하다 보면 성능은 따라오게 마련이다.
- 하지만 시스템을 설계할 때, 특히 API, 네트워크 프로토콜, 영구 저장용 데이터 포맷을 설계할 때는 성능을 염두에 두어야 한다.
- 프로파일러를 사용해 문제의 원인이 되는 지점을 찾아 최적화를 수행하라.
- 가장 먼저 어떤 알고리즘을 사용했는지 살펴보자.
- 모든 변경 후에는 성능을 측정하라.

<br>