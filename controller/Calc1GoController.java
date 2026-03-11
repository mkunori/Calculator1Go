package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Calc1GoModel;
import model.Operator;
import view.Calc1GoView;

public class Calc1GoController implements ActionListener {
    private Calc1GoModel model;
    private Calc1GoView view;

    enum CalcState {
        INPUT_FIRST_NUMBER,
        OPERATOR_SET,
        INPUT_SECOND_NUMBER,
        RESULT_SHOWN
    }
    private CalcState state = CalcState.INPUT_FIRST_NUMBER;

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
            doNumber(cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            // 演算子ボタン押下
            doOperator(cmd);
        } else if (cmd.equals("=")) {
            // 等号ボタン押下
            doEqual();
        } else if (cmd.equals("C")) {
            // クリアボタン押下
            doClear();
        }
    }

    private void doNumber(String numstr) {
        switch (state) {
            case INPUT_FIRST_NUMBER:
            case INPUT_SECOND_NUMBER:
                if (view.getDisplay().equals("0")) {
                    view.setDisplay(numstr);
                } else {
                    view.setDisplay(view.getDisplay() + numstr);
                }
                break;
            case OPERATOR_SET:
                view.setDisplay(numstr);
                state = CalcState.INPUT_SECOND_NUMBER;
                break;
            case RESULT_SHOWN:
                view.setDisplay(numstr);
                state = CalcState.INPUT_FIRST_NUMBER;
                break;
            default:
                break;
        }
    }

    private void doOperator(String op) {
        double value = view.getDisplayValue();

        if (state == CalcState.INPUT_SECOND_NUMBER) {
            model.calculate(value);
            view.setDisplay(model.getValue());
        } else if (state == CalcState.INPUT_FIRST_NUMBER ){
            model.setValue(value);
            //view.setDisplay(model.getValue());
        } else if (state == CalcState.OPERATOR_SET) {
            // すでに入力された演算子を上書きする
            view.replaceOperator(op);
            model.setOperator(Operator.fromSymbol(op));
            return;
        }

        model.setOperator(Operator.fromSymbol(op));
        view.appendOperator(op);

        state = CalcState.OPERATOR_SET;
    }

    private void doEqual() {
        if (state != CalcState.INPUT_SECOND_NUMBER) return;
        
        double value = view.getDisplayValue();
        model.calculate(value);
        view.setDisplay(model.getValue());

        state = CalcState.RESULT_SHOWN;
    }

    private void doClear() {
        model.clear();
        view.setDisplay("0");

        state = CalcState.INPUT_FIRST_NUMBER;
    }
}
