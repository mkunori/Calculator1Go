public class Calc1GoModel {
    private double currentValue = 0;
    private String operator = "";

    public void setValue(double value) {
        currentValue = value;
    }

    public double getValue() {
        return currentValue;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void calculate(double value) {
        switch (operator) {
            case "+" -> currentValue += value;
            case "-" -> currentValue -= value;
            case "*" -> currentValue *= value;
            case "/" -> currentValue /= value;
        }
    }

    public void clear() {
        currentValue = 0;
        operator = "";
    }
}
