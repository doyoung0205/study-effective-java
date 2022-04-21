### ordinal 인덱싱 대신 EnumMap 을 사용하라

ordinal 을 사용하면 안되는 점은 item 35 에서 다루었다.

Enum 이 들어간 Map 을 사용할 때는 EnumMap 을 사용한다.

Key 값이 Enum 으로 들어가고 성능도 다른 Map 과 크게 차이 나지 않는다.

EnumMap 의 성능이 ordinal을 쓴 배열에 비견되는 이유는 그 내부에서 배열을 사용하기 때문이다.


