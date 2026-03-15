package controller;

import model.Calc1GoModel;
import model.Operator;
import view.Calc1GoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 電卓アプリケーションの入力制御を担当するコントローラクラス。
 */
public class Calc1GoController implements ActionListener {

    /** 計算状態と計算処理を保持するモデル。 */
    private final Calc1GoModel model;

    /** 画面表示を担当するビュー。 */
    private final Calc1GoView view;

    /**
     * 電卓の入力状態を表す列挙型。
     */
    enum CalcState {

        /** 第1オペランドを入力中の状態。 */
        INPUT_FIRST_NUMBER,

        /** 演算子入力直後の状態。 */
        OPERATOR_SET,

        /** 第2オペランドを入力中の状態。 */
        INPUT_SECOND_NUMBER,

        /** 計算結果を表示中の状態。 */
        RESULT_SHOWN
    }

    /** 現在の入力状態。 */
    private CalcState state = CalcState.INPUT_FIRST_NUMBER;

    /**
     * コントローラを初期化し、ビューへリスナーを登録する。
     *
     * @param model 使用するモデル
     * @param view 使用するビュー
     */
    public Calc1GoController(Calc1GoModel model, Calc1GoView view) {
        this.model = model;
        this.view = view;
        view.addButtonListener(this);
    }

    /**
     * ボタン押下時のイベントを処理する。
     *
     * @param e アクションイベント
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            doNumber(cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            doOperator(cmd);
        } else if (cmd.equals("=")) {
            doEqual();
        } else if (cmd.equals("C")) {
            doClear();
        }
    }

    /**
     * 数値ボタン押下時の処理を行う。
     *
     * @param numStr 入力された数字
     */
    private void doNumber(String numStr) {
        switch (state) {
            case INPUT_FIRST_NUMBER:
            case INPUT_SECOND_NUMBER:
                if (view.getDisplay().equals("0")) {
                    view.setDisplay(numStr);
                } else {
                    view.setDisplay(view.getDisplay() + numStr);
                }
                break;

            case OPERATOR_SET:
                view.setDisplay(numStr);
                state = CalcState.INPUT_SECOND_NUMBER;
                break;

            case RESULT_SHOWN:
                view.setDisplay(numStr);
                state = CalcState.INPUT_FIRST_NUMBER;
                break;

            default:
                break;
        }
    }

    /**
     * 演算子ボタン押下時の処理を行う。
     *
     * @param op 入力された演算子
     */
    private void doOperator(String op) {
        double value = view.getDisplayValue();

        if (state == CalcState.INPUT_SECOND_NUMBER) {
            model.calculate(value);
            view.setDisplay(model.getValue());
        } else if (state == CalcState.INPUT_FIRST_NUMBER) {
            model.setValue(value);
        } else if (state == CalcState.OPERATOR_SET) {
            view.replaceOperator(op);
            model.setOperator(Operator.fromSymbol(op));
            return;
        }

        model.setOperator(Operator.fromSymbol(op));
        view.appendOperator(op);
        state = CalcState.OPERATOR_SET;
    }

    /**
     * 等号ボタン押下時の処理を行う。
     * 第2オペランド入力中の場合のみ計算を実行する。
     */
    private void doEqual() {
        if (state != CalcState.INPUT_SECOND_NUMBER) {
            return;
        }

        double value = view.getDisplayValue();
        model.calculate(value);
        view.setDisplay(model.getValue());
        state = CalcState.RESULT_SHOWN;
    }

    /**
     * クリアボタン押下時の処理を行う。
     */
    private void doClear() {
        model.clear();
        view.setDisplay("0");
        state = CalcState.INPUT_FIRST_NUMBER;
    }
}
