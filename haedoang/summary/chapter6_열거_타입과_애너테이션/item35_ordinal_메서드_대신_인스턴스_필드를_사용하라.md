## item35 ordinal 메서드 대신 인스턴스 필드를 사용하라

### ordinal은 안전하지 못하다
- 순서의 변경에 취약할 수 밖에 없다. (유지보수하기 어려움)
- 인스턴스 필드를 사용하는 편이 낫다
- `Enum` API에서는 `ordinal`의 사용을 열거 타입 기반의 범용 자료구조에 사용될 목적으로 설계되었다고 한다
- 개발자인 우리는 타입 안전성을 해치는 메서드의 사용을 지양하는 것이 좋다