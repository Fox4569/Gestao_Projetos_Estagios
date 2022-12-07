package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

public class GestaoCandidaturasUI extends BorderPane {
    AppManager appManager;
    Label titulo, nAlunosL, nAlunos, nAlunosCCandL, nAlunosCCand, nAlunosSCandL, nAlunosSCand;
    Button btn1, btn2, btn3, btn4;

    public GestaoCandidaturasUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Gestão de Candidaturas");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Adicionar Candidaturas");
        btn2 = new Button("Eliminar Candidaturas");
        btn3 = new Button("Listar Candidaturas");
        btn4 = new Button("Voltar atrás");
        VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4);

        btn1.setFont(new Font("Arial", 16));
        btn2.setFont(new Font("Arial", 16));
        btn3.setFont(new Font("Arial", 16));
        btn4.setFont(new Font("Arial", 16));

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        nAlunosCCandL = new Label("Número de Alunos c/ Candidaturas");
        nAlunosCCandL.setFont(new Font("Arial", 16));

        nAlunosCCand = new Label();
        nAlunosCCand.setFont(new Font("Arial", 16));
        nAlunosCCand.setAlignment(Pos.CENTER);

        nAlunosL = new Label("Número de Alunos");
        nAlunosL.setFont(new Font("Arial", 16));

        nAlunos = new Label();
        nAlunos.setFont(new Font("Arial", 16));
        nAlunos.setAlignment(Pos.CENTER);

        nAlunosSCandL = new Label("Número de Alunos s/ Candidaturas");
        nAlunosSCandL.setFont(new Font("Arial", 16));

        nAlunosSCand = new Label();
        nAlunosSCand.setFont(new Font("Arial", 16));
        nAlunosSCand.setAlignment(Pos.CENTER);

        VBox info1 = new VBox(nAlunosCCandL, nAlunosCCand);
        info1.setAlignment(Pos.BOTTOM_CENTER);

        VBox info2 = new VBox(nAlunosL, nAlunos);
        info2.setAlignment(Pos.BOTTOM_CENTER);

        VBox info3 = new VBox(nAlunosSCandL, nAlunosSCand);
        info3.setAlignment(Pos.BOTTOM_CENTER);

        HBox hBox = new HBox(info2, info1, info3);
        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(20);

        this.setCenter(vBox);
        this.setBottom(hBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btn1.setOnAction(event -> {
            Stage stage = new Stage();

            stage.setTitle("Adicionar Candidaturas");

            Label txt = new Label("Nome do Ficheiro: ");
            txt.setFont(new Font("Arial", 14));

            TextField tf = new TextField();

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                String nomeFich = tf.getText();
                boolean res = appManager.lerCandidaturas(nomeFich);
                if(res)
                    ToastMessage.show(getScene().getWindow(), "Cadidaturas adicionadas com sucesso!");
                else
                    ToastMessage.show(getScene().getWindow(), "Erro na adição das candidaturas!");
                stage.close();
            });

            tf.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode() == KeyCode.ENTER){
                    btn.fire();
                }
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn2.setOnAction(event -> {
            Stage stage = new Stage();

            stage.setTitle("Eliminar Candidaturas");

            Label txt = new Label("Numero do aluno: ");
            txt.setFont(new Font("Arial", 14));

            TextField tf = new TextField();

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                try{
                    String nAlunoS = tf.getText();
                    long nAluno = Integer.parseInt(nAlunoS);
                    boolean res = appManager.eliminarCandidatura(nAluno);
                    if(res)
                        ToastMessage.show(getScene().getWindow(), "Candidatura eliminada com sucesso!");
                    else
                        ToastMessage.show(getScene().getWindow(), "Erro na eliminação da candidatura!");
                    stage.close();
                }catch (Exception e){
                    tf.clear();
                    tf.requestFocus();
                }
            });

            tf.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode() == KeyCode.ENTER){
                    btn.fire();
                }
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn3.setOnAction(event -> {
            String alunos = appManager.listarCandidaturas();
            Stage stage = new Stage();
            stage.setTitle("Candidaturas");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Candidaturas");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 500, 300);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn4.setOnAction( event -> {
            appManager.fase2();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.GESTAO_CANDIDATURAS){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        nAlunosCCand.setText("" + appManager.getCandidaturas());
        nAlunos.setText("" + appManager.getAlunos());
        nAlunosSCand.setText("" + (appManager.getAlunos() - appManager.getCandidaturas()));
    }
}
