package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

public class EsperaEscolhaUI extends BorderPane {
    AppManager appManager;
    Label titulo, lb, lb2, lb3;
    Button btnC;
    TextField tf1, tf2;


    public EsperaEscolhaUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Atribuição de Docente a Aluno");
        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);
        titulo.setTextFill(Color.DARKBLUE);

        lb = new Label();

        lb2 = new Label("Introduza o valor do Orientador: ");
        lb2.setFont(new Font("Arial", 16));

        tf1 = new TextField();
        tf1.setFont(new Font("Arial", 16));

        lb3 = new Label("Introduza o valor do Aluno: ");
        lb3.setFont(new Font("Arial", 16));

        tf2 = new TextField();
        tf2.setFont(new Font("Arial", 16));

        btnC = new Button("Confirmar");
        btnC.setFont(new Font("Arial", 16));

        HBox hBox1 = new HBox(lb2, tf1);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(10);

        HBox hBox2 = new HBox(lb3, tf2);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(10);


        VBox vBox = new VBox(titulo, lb, hBox1, hBox2, btnC);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btnC.setOnAction(ev -> {
            int orientador = Integer.parseInt(tf1.getText());
            int aluno = Integer.parseInt(tf2.getText());
            if(appManager.atribuicaoOrientadorAluno(orientador, aluno))
                ToastMessage.show(getScene().getWindow(), "Orientador atribuído com sucesso!");
            else
                ToastMessage.show(getScene().getWindow(), "Erro na atribuição!");
            appManager.orientadores();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.ESPERAESCOLHA){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        lb.setText(appManager.listaAtribuicaoOrientadorAluno());
        lb.setFont(new Font("Arial", 16));
    }
}
