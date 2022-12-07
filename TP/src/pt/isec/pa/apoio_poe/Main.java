package pt.isec.pa.apoio_poe;

import javafx.application.Application;
import pt.isec.pa.apoio_poe.model.data.App;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppContext;
import pt.isec.pa.apoio_poe.ui.gui.MainJFX;
import pt.isec.pa.apoio_poe.ui.text.AppUI;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        /*AppContext fsm = new AppContext();
        AppManager app = new AppManager();
        AppUI ui = new AppUI(fsm,app);
        ui.start();*/
        Application.launch(MainJFX.class, args);
    }
}
