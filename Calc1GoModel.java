public class Calc1GoModel {
    private double currentValue = 0;
    private Operator operator = null;

    public void setValue(double value) {
        currentValue = value;
    }

    public double getValue() {
        return currentValue;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void calculate(double value) {
        currentValue = operator.apply(currentValue, value);
    }

    public void clear() {
        currentValue = 0;
        operator = null;
    }
}
