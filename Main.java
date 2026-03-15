import controller.Calc1GoController;
import model.Calc1GoModel;
import view.Calc1GoView;

/**
 * 電卓アプリケーションを起動するメインクラス。
 */
public class Main {

    /**
     * アプリケーションを起動する。
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        var model = new Calc1GoModel();
        var view = new Calc1GoView();
        new Calc1GoController(model, view);
        view.setVisible(true);
    }
}
