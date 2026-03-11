package model;

public class Calc1GoModel {
    // Modelは計算状態と計算ロジックを持つ
    
    private double currentValue = 0;
    private Operator operator;

    public void setValue(double value) {
        currentValue = value;
    }

    public double getValue() {
        return currentValue;
    }

    public void setOperator(Operator op) {
        this.operator = op;
    }

    public void calculate(double value) {
        if (operator == null) return;
        
        currentValue = operator.apply(currentValue, value);
    }

    public void clear() {
        currentValue = 0;
        operator = null;
    }
}
