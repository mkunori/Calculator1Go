package model;

import java.util.function.DoubleBinaryOperator;

public enum Operator {
    ADD("+", (a, b) -> a + b),
    SUB("-", (a, b) -> a - b),
    MUL("*", (a, b) -> a * b),
    DIV("/", (a, b) -> a / b);

    private final String symbol;
    private final DoubleBinaryOperator operation;

    Operator(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.operation = op;
    }

    public double apply(double a, double b) {
        return operation.applyAsDouble(a, b);
    }

    // ボタン入力でStringで受け取った演算子をenumとして取り出し
    public static Operator fromSymbol(String s) {
        for (Operator o : values()) {
            if (o.symbol.equals(s)) {
                return o;
            }
        }
        throw new IllegalArgumentException("未定義の演算子");
    }
}
