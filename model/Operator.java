package model;

import java.util.function.DoubleBinaryOperator;

/**
 * 電卓で使用する演算子を表す列挙型。
 * 各演算子は表示用記号と計算処理を持つ。
 */
public enum Operator {

    /** 加算を表す演算子。 */
    ADD("+", (a, b) -> a + b),

    /** 減算を表す演算子。 */
    SUB("-", (a, b) -> a - b),

    /** 乗算を表す演算子。 */
    MUL("*", (a, b) -> a * b),

    /** 除算を表す演算子。 */
    DIV("/", (a, b) -> a / b);

    /** 画面表示用の演算子記号。 */
    private final String symbol;

    /** 実際の計算処理。 */
    private final DoubleBinaryOperator operation;

    /**
     * 演算子を初期化する。
     *
     * @param symbol 表示用記号
     * @param operation 計算処理
     */
    Operator(String symbol, DoubleBinaryOperator operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    /**
     * 2つの値に対して演算を適用する。
     *
     * @param a 左辺値
     * @param b 右辺値
     * @return 演算結果
     */
    public double apply(double a, double b) {
        return operation.applyAsDouble(a, b);
    }

    /**
     * 記号に対応する演算子を返す。
     *
     * @param s 演算子記号
     * @return 対応する演算子
     * @throws IllegalArgumentException 未定義の記号が渡された場合
     */
    public static Operator fromSymbol(String s) {
        for (Operator o : values()) {
            if (o.symbol.equals(s)) {
                return o;
            }
        }
        throw new IllegalArgumentException("未定義の演算子: " + s);
    }
}
