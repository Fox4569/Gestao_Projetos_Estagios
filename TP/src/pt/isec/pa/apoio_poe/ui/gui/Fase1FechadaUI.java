package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;

public class Fase1FechadaUI extends BorderPane {
    AppManager appManager;
    Label titulo;
    Button btn1, btn2, btn3, btn4, btn5;

    public Fase1FechadaUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Opcões de Configuração (FECHADO)");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Listar Alunos");
        btn2 = new Button("Listar Docentes");
        btn3 = new Button("Listar Propostas");
        btn4 = new Button("Opções de Candidatura");
        btn5 = new Button("Sair");

        VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4, btn5);

        btn1.setFont(new Font("Arial", 16));
        btn1.setMinSize(250, 10);

        btn2.setFont(new Font("Arial", 16));
        btn2.setMinSize(250, 10);

        btn3.setFont(new Font("Arial", 16));
        btn3.setMinSize(250, 10);

        btn4.setFont(new Font("Arial", 16));
        btn4.setMinSize(250, 10);

        btn5.setFont(new Font("Arial", 16));
        btn5.setMinSize(250, 10);

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(ev -> { update(); });

        btn1.setOnAction(ev-> {
            String alunos = appManager.listarAlunos();
            Stage stage = new Stage();
            stage.setTitle("Alunos");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Alunos");
            titulo.setFont(new Font("Arial", 30));

            VBox vBox = new VBox(titulo, lb);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1200, 300);


            stage.setScene(scene);
            stage.showAndWait();
        });

        btn2.setOnAction(ev -> {
            String alunos = appManager.listarDocentes();
            Stage stage = new Stage();
            stage.setTitle("Docentes");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Docentes");
            titulo.setFont(new Font("Arial", 30));

            VBox vBox = new VBox(titulo, lb);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 400, 300);

            stage.setScene(scene);
            stage.showAndWait();
            });

        btn3.setOnAction(ev -> {
            String alunos = appManager.listarPropostas();
            Stage stage = new Stage();
            stage.setTitle("Propostas");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Propostas");
            titulo.setFont(new Font("Arial", 30));

            VBox vBox = new VBox(titulo, lb);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1200, 500);

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn4.setOnAction(ev -> {
            appManager.fase2();
        });

        btn5.setOnAction( ev -> {
            Platform.exit();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.FASE1FECHADA){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
