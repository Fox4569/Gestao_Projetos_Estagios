package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;


public class MainJFX extends Application {
    AppManager appManager;

    @Override
    public void init() throws Exception {
        super.init();
        appManager = new AppManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(appManager);
        Scene scene = new Scene(root, 700, 600);

        /*Image img = new Image("C:\\Users\\Utilizador\\Desktop\\TP\\background.jpg");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);*/

        stage.setScene(scene);
        stage.setTitle("Aplicação");
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setResizable(false);
        stage.show();
    }
}
