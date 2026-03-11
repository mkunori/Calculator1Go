import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calc1GoView extends JFrame {
    private JTextField display = new JTextField("0");   // 電卓の表示画面
    private List<JButton> buttons = new ArrayList<>();

    public Calc1GoView() {
        setTitle("Calculator-1Go");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウを閉じると終了
        setLayout(new BorderLayout());

        // 表示画面を設定する
        display.setEditable(false); // 直接入力は不可
        display.setHorizontalAlignment(JTextField.RIGHT);   // 右詰め
        add(display, BorderLayout.NORTH);   // アプリ上部に配置

        // ボタンをグリッド上に配置する
        JPanel panel = new JPanel(new GridLayout(4, 4));
        final String[] button = {    // ボタン配置
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        for (String text : button) {
            var b = new JButton(text);
            b.setActionCommand(text);
            panel.add(b);
            buttons.add(b);
        }
        add(panel, BorderLayout.CENTER);    // アプリ中央に配置
    }

    public void setDisplay(String text) {
        display.setText(text);
    }

    public String getDisplay() {
        return display.getText();
    }

    // ボタンにアクションリスナーを付与する
    public void addButtonListener(ActionListener listener) {
        for (JButton b: buttons) {
            b.addActionListener(listener);
        }
    }
}
