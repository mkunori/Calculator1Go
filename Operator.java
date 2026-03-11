import java.util.function.DoubleBinaryOperator;

public enum Operator {
    ADD("+", (a, b) -> a + b),
    SUB("-", (a, b) -> a - b),
    MUL("*", (a, b) -> a * b),
    DIV("/", (a, b) -> a / b);

    private final String symbol;
    private final DoubleBinaryOperator operation;

    Operator(String symbol, DoubleBinaryOperator operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public double apply(double a, double b) {
        return operation.applyAsDouble(a, b);
    }

    // ボタン入力で文字列で受け取った演算子を列挙定数として取り出し
    public static Operator fromSymbol(String s) {
        for (Operator o : values()) {
            if (o.symbol.equals(s)) {
                return o;
            }
        }
        throw new IllegalArgumentException("未定義の演算子");
    }
}
