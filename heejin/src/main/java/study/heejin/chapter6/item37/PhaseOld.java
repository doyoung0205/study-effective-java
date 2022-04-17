package study.heejin.chapter6.item37;

public enum PhaseOld {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;

        private static final Transition[][] TRANSITIONS = {
                {null, MELT, SUBLIME},
                {FREEZE, null, BOIL},
                {DEPOSIT, CONDENSE, null}
        };

        public static Transition from(PhaseOld from, PhaseOld to) {
            return TRANSITIONS[from.ordinal()][to.ordinal()];
        }
    }

    public static void main(String[] args) {
        Transition transition = Transition.from(SOLID, LIQUID);
        System.out.println(transition);
    }
}
