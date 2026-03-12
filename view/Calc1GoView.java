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

public class Calc1GoView extends JFrame {
    private JTextField display = new JTextField("0"); // 電卓の表示画面
    private List<JButton> buttons = new ArrayList<>();

    public Calc1GoView() {
        setTitle("Calculator-1Go");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウを閉じると終了
        setLayout(new BorderLayout());

        // 表示画面を設定する
        display.setEditable(false); // 直接入力は不可
        display.setHorizontalAlignment(JTextField.RIGHT); // 右詰め
        display.setFont(new Font("SanSerif", Font.PLAIN, 28));
        add(display, BorderLayout.NORTH); // アプリ上部に配置

        // ボタンをグリッド上に配置する
        JPanel panel = new JPanel(new GridLayout(4, 4));
        final String[] button = { // ボタン配置
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };
        for (String text : button) {
            var b = new JButton(text);
            b.setActionCommand(text);
            b.setFont(new Font("SanSerif", Font.PLAIN, 20));
            panel.add(b);
            buttons.add(b);
        }
        add(panel, BorderLayout.CENTER); // アプリ中央に配置
    }

    public void setDisplay(String text) {
        display.setText(text);
    }

    public void setDisplay(double value) {
        if (value == (long) value) {
            display.setText(String.valueOf((long) value)); // 整数ならば小数点以下は取り除く
        } else {
            display.setText(String.valueOf(value));
        }
    }

    public String getDisplay() {
        return display.getText();
    }

    public double getDisplayValue() {
        String text = display.getText();

        // 演算子が含まれている場合は削除する
        text = text.replaceAll("[+\\-*/]", "").trim();

        return Double.parseDouble(text);
    }

    public void appendOperator(String op) {
        display.setText(display.getText() + " " + op);
    }

    public void replaceOperator(String op) {
        String text = display.getText();
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 2);
        }
        display.setText(text + " " + op);
    }

    // ボタンにアクションリスナーを付与する
    public void addButtonListener(ActionListener listener) {
        for (JButton b : buttons) {
            b.addActionListener(listener);
        }
    }
}
