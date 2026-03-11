import controller.Calc1GoController;
import model.Calc1GoModel;
import view.Calc1GoView;

public class Main {
    public static void main(String[] args) {
        var model = new Calc1GoModel();
        var view = new Calc1GoView();
        new Calc1GoController(model, view);
        view.setVisible(true);
    }
}