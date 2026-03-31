import controller.CController;
import model.CModel;
import view.CView;

/**
 * 電卓アプリケーションを起動するメインクラス。
 */
public class CMain {

    /**
     * アプリケーションを起動する。
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        var model = new CModel();
        var view = new CView();
        new CController(model, view);
        view.setVisible(true);
    }
}
