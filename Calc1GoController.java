import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc1GoController implements ActionListener {
    private Calc1GoModel model;
    private Calc1GoView view;

    private boolean startNewNum = true;
    private boolean calcOk = false;
    private boolean operatorSet = false;

    public Calc1GoController(Calc1GoModel model, Calc1GoView view) {
        this.model = model;
        this.view = view;
        view.addButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.matches("[0-9]")) {
            // 数値ボタン押下
            String strnum = view.getDisplay();
            if (startNewNum) {
                startNewNum = false;
                view.setDisplay(cmd);
            }else if (strnum.equals("0")) {
                view.setDisplay(cmd);
            } else {
                view.setDisplay(strnum + cmd);
            }

            if (operatorSet) {
                calcOk = true;
            }
        } else if (cmd.matches("[+\\-*/]")) {
            // 演算子ボタン押下
            double value = Double.parseDouble(view.getDisplay());
            if (calcOk) {
                calcOk = false;
                model.calculate(value);
                view.setDisplay(String.valueOf(model.getValue()));
            } else {
                model.setValue(value);
            }
            model.setOperator(Operator.fromSymbol(cmd));
            startNewNum = true;
            operatorSet = true;
        } else if (cmd.equals("=")) {
            // 等号ボタン押下
            if (calcOk) {
                calcOk = false;
                double value = Double.parseDouble(view.getDisplay());
                model.calculate(value);
                view.setDisplay(String.valueOf(model.getValue()));
                startNewNum = true;
                operatorSet = false;
            }
        } else if (cmd.equals("C")) {
            // クリアボタン押下
            model.clear();
            view.setDisplay("0");
            startNewNum = true;
            calcOk = false;
            operatorSet = false;
        }
    }
}
