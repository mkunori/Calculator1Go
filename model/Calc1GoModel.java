package model;

/**
 * 電卓の計算状態と計算ロジックを管理するモデルクラス。
 */
public class Calc1GoModel {

    /** 現在の計算値。 */
    private double currentValue = 0;

    /** 現在選択されている演算子。 */
    private Operator operator;

    /**
     * 現在値を設定する。
     *
     * @param value 設定する値
     */
    public void setValue(double value) {
        currentValue = value;
    }

    /**
     * 現在値を返す。
     *
     * @return 現在値
     */
    public double getValue() {
        return currentValue;
    }

    /**
     * 現在の演算子を設定する。
     *
     * @param op 設定する演算子
     */
    public void setOperator(Operator op) {
        this.operator = op;
    }

    /**
     * 現在値と指定された値で計算を行う。
     * 演算子が未設定の場合は何もしない。
     *
     * @param value 計算に使用する値
     */
    public void calculate(double value) {
        if (operator == null) {
            return;
        }

        currentValue = operator.apply(currentValue, value);
    }

    /**
     * モデルの状態を初期化する。
     */
    public void clear() {
        currentValue = 0;
        operator = null;
    }
}
