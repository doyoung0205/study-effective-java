## 상속을 고려해 설계하고 문서화하라 그렇지 않았다면 상속을 금지하라

상속용 클래스는 재정의할 수 있는 메서드들을 내부적으로 어떻게 이용하는지 (자기사용) 문서로 남겨야 한다.

즉, 재정의 가능 메서드를 호출 할 수 있는 모든 상황을 문서로 남겨야 한다.

문서로 남길 떄는 `@implSpec` 주석을 사용해서 설명하고, '어떻게' 가 아닌 '무엇'을 하는지 설명해야한다.

### 주의사항

- 상속용으로 설계한 클래스는 배포 전에 반드시 하위클래스를 만들어 검증해야 한다.
- 상속용 클래스의 생성자는 직접적으로든 간접적으로든 재정의 가능 메서드를 호출해서는 안된다.

### 어려웠던 점

- (p.123) 클래스의 내부 동작 과정 중간에 끼어들 수 있는 훅(hock)을 잘 선별하여 protected 메서드 형태로 공개해야 할 수도 있다.
  - AbstractList 의 removeRange 메서드
  - 훅: 중독적이고 강렬한 후렴구 같은 뜻이 있듯이 성능의 많이 관여하는 메서드를 뜻함

```
// AbstractList

public void clear() {
    removeRange(0, size());
}

protected void removeRange(int fromIndex, int toIndex) {
    ListIterator<E> it = listIterator(fromIndex);
    for (int i=0, n=toIndex-fromIndex; i<n; i++) {
        it.next();
        it.remove();
    }
}


// ArrayList 

protected void removeRange(int fromIndex, int toIndex) {
    if (fromIndex > toIndex) {
        throw new IndexOutOfBoundsException(
                outOfBoundsMsg(fromIndex, toIndex));
    }
    modCount++;
    shiftTailOverGap(elementData, fromIndex, toIndex);
}


// ArrayList.SubList
protected void removeRange(int fromIndex, int toIndex) {
    checkForComodification(); // 동시성 체크
    root.removeRange(offset + fromIndex, offset + toIndex);
    updateSizeAndModCount(fromIndex - toIndex);
}


// vector

protected synchronized void removeRange(int fromIndex, int toIndex) {
    modCount++;
    shiftTailOverGap(elementData, fromIndex, toIndex);
}
```

- 상속을 구현하는 클래스에서 문서화 해보는 것
