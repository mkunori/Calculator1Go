package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 電卓アプリケーションの画面表示を担当するビュークラス。
 */
public class Calc1GoView extends JFrame {

    /** 電卓の表示欄。 */
    private final JTextField display = new JTextField("0");

    /** 電卓の各ボタン。 */
    private final List<JButton> buttons = new ArrayList<>();

    /**
     * 電卓画面を初期化する。
     */
    public Calc1GoView() {
        setTitle("Calculator-1Go");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("SansSerif", Font.PLAIN, 28));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4));
        final String[] buttonTexts =
                {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};

        for (String text : buttonTexts) {
            var b = new JButton(text);
            b.setActionCommand(text);
            b.setFont(new Font("SansSerif", Font.PLAIN, 20));
            panel.add(b);
            buttons.add(b);
        }

        add(panel, BorderLayout.CENTER);
    }

    /**
     * 表示欄に文字列を設定する。
     *
     * @param text 表示する文字列
     */
    public void setDisplay(String text) {
        display.setText(text);
    }

    /**
     * 表示欄に数値を設定する。
     * 整数として表現できる場合は小数点以下を省略する。
     *
     * @param value 表示する値
     */
    public void setDisplay(double value) {
        if (value == (long) value) {
            display.setText(String.valueOf((long) value));
        } else {
            display.setText(String.valueOf(value));
        }
    }

    /**
     * 表示欄の文字列を返す。
     *
     * @return 表示欄の文字列
     */
    public String getDisplay() {
        return display.getText();
    }

    /**
     * 表示欄に表示されている数値部分を取得する。
     * 演算子が含まれている場合は取り除いてから数値へ変換する。
     *
     * @return 表示欄の数値
     * @throws NumberFormatException 表示内容が数値に変換できない場合
     */
    public double getDisplayValue() {
        String text = display.getText();
        text = text.replaceAll("[+\\-*/]", "").trim();
        return Double.parseDouble(text);
    }

    /**
     * 表示欄の右端に演算子を追加する。
     *
     * @param op 追加する演算子
     */
    public void appendOperator(String op) {
        display.setText(display.getText() + " " + op);
    }

    /**
     * 表示欄の右端の演算子を別の演算子で上書きする。
     *
     * @param op 新しく表示する演算子
     */
    public void replaceOperator(String op) {
        String text = display.getText();
        if (text.length() >= 2) {
            text = text.substring(0, text.length() - 2);
        }
        display.setText(text + " " + op);
    }

    /**
     * すべてのボタンにアクションリスナーを登録する。
     *
     * @param listener 登録するリスナー
     */
    public void addButtonListener(ActionListener listener) {
        for (JButton b : buttons) {
            b.addActionListener(listener);
        }
    }
}
