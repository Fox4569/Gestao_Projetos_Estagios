package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

public class Fase4UI extends BorderPane {
    AppManager appManager;
    Label titulo;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    public Fase4UI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Atribuição de Orientadores");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Atribuir automaticamente docente");
        btn2 = new Button("Configurar orientadores");
        btn3 = new Button("Listar Dados");
        btn4 = new Button("Exportar dados para ficheiro");
        btn5 = new Button("Fechar Fase");
        btn6 = new Button("Atribuição de Propostas");
        btn7 = new Button("Sair");
        VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4, btn5, btn6, btn7);

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

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btn1.setOnAction(ev-> {
            if(appManager.atribuicaoAutoDocentes())
                ToastMessage.show(getScene().getWindow(), "Atribuições efetuadas com sucesso!");
            else
                ToastMessage.show(getScene().getWindow(), "Erro nas atribuições!");
        });

        btn2.setOnAction(ev -> {
            appManager.orientadores();
        });

        btn3.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Listar Dados");

            Label titulo = new Label("Listar Dados");
            titulo.setFont(new Font("Arial", 30));

            Button btn1 = new Button("Alunos com Orientador");
            btn1.setFont(new Font("Arial", 16));
            Button btn2 = new Button("Alunos sem Orientador");
            btn2.setFont(new Font("Arial", 16));
            Button btn3 = new Button("Dados sobre Orientadores");
            btn3.setFont(new Font("Arial", 16));
            Button btn4 = new Button("Voltar atrás");
            btn4.setFont(new Font("Arial", 16));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, btn1, btn2, btn3, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(20);

            Scene scene = new Scene(vBox, 500, 500);

            btn1.setOnAction(ev -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Alunos com Orientador");

                Label lb = new Label(appManager.listarAlunosComOrientador());
                lb.setFont(new Font("Arial", 16));

                Button btnOk2 = new Button("OK");
                btnOk2.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(lb, btnOk2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                btnOk2.setOnAction(ev1 -> {
                    stage2.close();
                });

                Scene scene2 = new Scene(vBox1, 1200, 400);
                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            btn2.setOnAction(ev1 -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Alunos sem Orientador");

                Label lb = new Label(appManager.listarAlunosSemOrientador());
                lb.setFont(new Font("Arial", 16));

                Button btnOk2 = new Button("OK");
                btnOk2.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(lb, btnOk2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                btnOk2.setOnAction(ev2 -> {
                    stage2.close();
                });

                Scene scene2 = new Scene(vBox1, 1200, 400);
                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            btn3.setOnAction(ev2 -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Dados sobre Orientadores");

                Label lb = new Label(appManager.dadosOrientadores());
                lb.setFont(new Font("Arial", 16));

                Button btnOk2 = new Button("OK");
                btnOk2.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(lb, btnOk2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                btnOk2.setOnAction(ev3 -> {
                    stage2.close();
                });

                Scene scene2 = new Scene(vBox1, 1200, 400);
                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            btnOk.setOnAction(ev4 -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });


        btn4.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Exportar Dados");

            Label txt = new Label("Numero do Ficheiro: ");
            txt.setFont(new Font("Arial", 14));

            TextField tf = new TextField();

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                String nomeFich = tf.getText();
                if(nomeFich != null) {
                    boolean res = appManager.exportarDadosFase4e5(nomeFich);
                    if(res)
                        ToastMessage.show(getScene().getWindow(), "Dados exportados com sucesso!");
                    else
                        ToastMessage.show(getScene().getWindow(), "Erro na exportação dos dados!");
                    stage.close();
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


        btn5.setOnAction(ev -> {
            appManager.fechaFase();
        });

        btn6.setOnAction(ev -> {
            appManager.fase3();
        });

        btn7.setOnAction( ev -> {
            Platform.exit();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.FASE4){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
