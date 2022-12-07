package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

public class Fase1UI extends BorderPane {
    AppManager appManager;
    Label titulo;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    public Fase1UI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Opções de Configuração");

        btn1 = new Button("Gestão de Alunos");
        btn2 = new Button("Gestão de Docentes");
        btn3 = new Button("Gestão de Propostas");
        btn4 = new Button("Carregar Dados Anteriores");
        btn5 = new Button("Salvar Dados Atuais");
        btn6 = new Button("Fechar Fase");
        btn7 = new Button("Opções de Candidatura");
        btn8 = new Button("Sair");
        VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8);

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

        btn6.setFont(new Font("Arial", 16));
        btn6.setMinSize(250, 10);

        btn7.setFont(new Font("Arial", 16));
        btn7.setMinSize(250, 10);

        btn8.setFont(new Font("Arial", 16));
        btn8.setMinSize(250, 10);


        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);
        titulo.setTextFill(Color.DARKBLUE);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btn1.setOnAction( event -> {
            appManager.faseGestaoAlunos();
        });

        btn2.setOnAction(ev -> {
            appManager.faseGestaoDocentes();
        });

        btn3.setOnAction(ev -> {
            appManager.faseGestaoPropostas();
        });

        btn7.setOnAction(ev -> {
            appManager.fase2();
        });

        btn8.setOnAction( event -> {
            Platform.exit();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.FASE1){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        btn6.setOnAction(ev -> {
            boolean res = appManager.fechaFase();
            if(res)
                ToastMessage.show(getScene().getWindow(), "Fase fechada com sucesso!");
            else
                ToastMessage.show(getScene().getWindow(), "Não é possível fechar a fase!");
        });
    }
}
