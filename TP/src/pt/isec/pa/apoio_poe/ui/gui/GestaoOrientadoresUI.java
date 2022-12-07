package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;

public class GestaoOrientadoresUI extends BorderPane {
    AppManager appManager;
    Label titulo, nDocL, nDoc, nAlunosCOrientL, nAlunosCOrient, nAlunosSOrientL, nAlunosSOrient;
    Button btn1, btn2, btn3;

    public GestaoOrientadoresUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Gestão de Orientadores");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Atribuir Orientadores a Alunos");
        btn2 = new Button("Consultar Orientadores e Respetivas Alunos");
        btn3 = new Button("Voltar atrás");
        VBox vBox = new VBox(titulo, btn1, btn2, btn3);

        btn1.setFont(new Font("Arial", 16));
        btn1.setMinSize(250, 10);

        btn2.setFont(new Font("Arial", 16));
        btn2.setMinSize(250, 10);

        btn3.setFont(new Font("Arial", 16));
        btn3.setMinSize(250, 10);

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        nDocL = new Label("Número de Docentes");
        nDocL.setFont(new Font("Arial", 16));

        nDoc = new Label();
        nDoc.setFont(new Font("Arial", 16));
        nDoc.setAlignment(Pos.CENTER);

        nAlunosCOrientL = new Label("Número de Alunos c/ Orientador");
        nAlunosCOrientL.setFont(new Font("Arial", 16));

        nAlunosCOrient = new Label();
        nAlunosCOrient.setFont(new Font("Arial", 16));
        nAlunosCOrient.setAlignment(Pos.CENTER);

        nAlunosSOrientL = new Label("Número de Alunos s/ Orientador");
        nAlunosSOrientL.setFont(new Font("Arial", 16));

        nAlunosSOrient = new Label();
        nAlunosSOrient.setFont(new Font("Arial", 16));
        nAlunosSOrient.setAlignment(Pos.CENTER);

        VBox info1 = new VBox(nDocL, nDoc);
        info1.setAlignment(Pos.BOTTOM_CENTER);

        VBox info2 = new VBox(nAlunosCOrientL, nAlunosCOrient);
        info2.setAlignment(Pos.BOTTOM_CENTER);

        VBox info3 = new VBox(nAlunosSOrientL, nAlunosSOrient);
        info3.setAlignment(Pos.BOTTOM_CENTER);

        HBox hBox = new HBox(info1, info2, info3);
        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(20);

        this.setCenter(vBox);
        this.setBottom(hBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });
        btn1.setOnAction(ev -> {
            appManager.esperaEscolha();
        });

        btn2.setOnAction(event -> {
            String orientadores = appManager.listaOrientadoresEAlunos();
            Stage stage = new Stage();
            stage.setTitle("Orientadores");

            Label lb = new Label(orientadores);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Orientadores");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1200, 300);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn3.setOnAction(ev -> {
            appManager.fase4();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.GESTAO_ORIENTADORES){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        nDoc.setText("" + appManager.getDocentes());
        nAlunosCOrient.setText("" + appManager.getAlunosComOrientador());
        nAlunosSOrient.setText("" + (appManager.getAlunos() - appManager.getAlunosComOrientador()));
    }
}
