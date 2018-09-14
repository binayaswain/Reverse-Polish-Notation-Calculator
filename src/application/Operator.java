package application;

public enum Operator {

    POW("POW", 3) {
        @Override
        public double evaluate(double op1, double op2) {
            return Math.pow(op1, op2);
        }
    },

    DIVIDE("/", 2) {
        @Override
        public double evaluate(double op1, double op2) {
            if (op2 == 0) {
                throw new ArithmeticException("Cannot Divide By Zero" + InputOutput.LINE_FEED);
            }
            return op1 / op2;
        }
    },

    MOD("%", 2) {
        @Override
        public double evaluate(double op1, double op2) {
            if (op2 == 0) {
                throw new ArithmeticException("Cannot perform mod of Zero" + InputOutput.LINE_FEED);
            }
            return op1 % op2;
        }
    },

    MULTIPLY("*", 2) {
        @Override
        public double evaluate(double op1, double op2) {
            return op1 * op2;
        }
    },

    PLUS("+", 1) {
        @Override
        public double evaluate(double op1, double op2) {
            return op1 + op2;
        }
    },

    MINUS("-", 1) {
        @Override
        public double evaluate(double op1, double op2) {
            return op1 - op2;
        }
    };

    private final String sign;

    private final int precedenceValue;

    private Operator(String sign, int precedenceValue) {
        this.sign = sign;
        this.precedenceValue = precedenceValue;
    }

    public abstract double evaluate(double op1, double op2);

    public String getSign() {
        return sign;
    }

    public int getPrecedenceValue() {
        return precedenceValue;
    }

    public static int compare(String sign1, String sign2) {
        Operator op1 = toOperator(sign1);
        Operator op2 = toOperator(sign2);

        if (op1 == null || op2 == null) {
            return 10000;
        }

        return op1.getPrecedenceValue() - op2.getPrecedenceValue();
    }

    public static Operator toOperator(String sign) {
        for (Operator op : Operator.values()) {
            if (op.getSign().equals(sign)) {
                return op;
            }
        }
        return null;
    }

}
