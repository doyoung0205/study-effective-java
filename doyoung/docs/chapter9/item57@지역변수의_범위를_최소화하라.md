### 지역변수의 범위를 최소화하라

지역변수의 범위를 줄이는 가장 강력한 기법은 역시 '가장 처음 쓰일 때 선언하기' 이다.

사용하려면 멀었는데, 미리 선언부터 해두면 코드가 어수선해져 가독성이 떨어진다.
변수를 실제로 사용하는 시점에 타입과 초깃값이 기억나지 않을 수도 있다.

### 거의 모든 지역변수는 선언과 동시에 초기화해야 한다.

- 지역변수를 잘한 For 문

```
for (Iterator<Element> i = c.iterator(); i.hasNext(); ) {
    Element e = i.next();
}
```

- 지역변수가 아닌 while 문

```
Iterator<Element> i = c.iterator();
while(i.hasNext()) {
...
}

Iterator<Element> i2 = c.iterator();
while(i.hasNext()) { // 버그 
...
} 
```

### 메서드를 작게 유지하고 한 가지 기능에 집중하는 것

