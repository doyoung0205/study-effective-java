package chapter6.item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * author : haedoang
 * date : 2022/04/19
 * description :
 */
public enum SmartPhase {
    SOLID, LIQUID, GAS, PLASMA;

    public enum SmartTransition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID), IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);

        private final SmartPhase from;
        private final SmartPhase to;

        SmartTransition(SmartPhase from, SmartPhase to) {
            this.from = from;
            this.to = to;
        }

        private static final Map<SmartPhase, Map<SmartPhase, SmartTransition>> m
                = Stream.of(values()).collect(

                /***
                 *  args[0] : Function<? super T, ? extends K> classifier, ===> KEY
                 *  args[1] : Supplier<M> mapFactory, ===> 결과가 삽입될 Map Supplier
                 *  args[2] : Collector<? super T, A, D> downstream ===? VALUE
                 *
                 *  classifier – a classifier function mapping input elements to keys
                 *  mapFactory – a supplier providing a new empty Map into which the results will be inserted
                 *  downstream – a Collector implementing the downstream reduction
                 */
                Collectors.groupingBy(
                        t -> t.from, // from을 키로사용한다
                        () -> new EnumMap<>(SmartPhase.class), //SmartPhase 타입의 EnumMap을 반환함을 정의한다


                        /**
                         * args[0] : Function<? super T, ? extends K> keyMapper,  => KEY
                         * args[1] : Function<? super T, ? extends U> valueMapper, => VALUE
                         * args[2] : BinaryOperator<U> mergeFunction, =>
                         * args[3] : Supplier<M> mapFactory
                         *
                         *
                         * keyMapper – a mapping function to produce keys
                         * valueMapper – a mapping function to produce values
                         * mergeFunction – a merge function, used to resolve collisions between values associated with the same key, as supplied to Map.merge(Object, Object, BiFunction)
                         * mapFactory – a supplier providing a new empty Map into which the results will be inserted
                         */
                        toMap(
                                t -> t.to,
                                t -> t,
                                (x, y) -> y, //bifunction 키충돌방지 (동일 키일 경우 뒤에것을 사용한다라는뜻
                                () -> new EnumMap<>(SmartPhase.class)
                        )
                ));

        public static SmartTransition from(SmartPhase from, SmartPhase to) {
            return m.get(from).get(to);
        }
    }


}
