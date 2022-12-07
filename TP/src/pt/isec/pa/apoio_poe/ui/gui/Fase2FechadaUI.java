package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;

public class Fase2FechadaUI extends BorderPane {
    AppManager appManager;
    Label titulo;
    Button btn1, btn2, btn3, btn4, btn5, btn6;

    public Fase2FechadaUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Opções de Candidatura (FECHADO)");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Listar Candidaturas");
        btn2 = new Button("Listar Alunos");
        btn3 = new Button("Listar Propostas");
        btn4 = new Button("Configuração");
        btn5 = new Button("Atribuição de Propostas");
        btn6 = new Button("Sair");

        VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4, btn5, btn6);

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

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(ev -> { update(); });

        btn1.setOnAction(ev-> {
            String alunos = appManager.listarCandidaturas();
            Stage stage = new Stage();
            stage.setTitle("Candidaturas");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Candidaturas");
            titulo.setFont(new Font("Arial", 30));

            VBox vBox = new VBox(titulo, lb);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox);

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn2.setOnAction(ev -> {
            Stage stage = new Stage();
            stage.setTitle("Listar Alunos");

            Label titulo = new Label("Listar Alunos");
            titulo.setFont(new Font("Arial", 30));

            Button btn1 = new Button("Alunos com Autoproposta");
            btn1.setFont(new Font("Arial", 16));
            Button btn2 = new Button("Alunos com Candidatura");
            btn2.setFont(new Font("Arial", 16));
            Button btn3 = new Button("Alunos sem Candidatura");
            btn3.setFont(new Font("Arial", 16));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, btn1, btn2, btn3, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(20);

            Scene scene = new Scene(vBox, 500, 500);

            btn1.setOnAction(ev2 -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Alunos com Autoproposta");

                Label lb = new Label(appManager.listarAlunosAutoproposta());
                lb.setFont(new Font("Arial", 14));

                Button btnOk2 = new Button("OK");
                btnOk2.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(lb, btnOk2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                btnOk2.setOnAction(ev1 -> {
                    stage2.close();
                });

                Scene scene2 = new Scene(vBox1, 1000, 200);
                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            btn2.setOnAction(ev1 -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Alunos com Candidatura");

                Label lb = new Label(appManager.listarAlunosComCand());
                lb.setFont(new Font("Arial", 14));

                Button btnOk2 = new Button("OK");
                btnOk2.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(lb, btnOk2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                btnOk2.setOnAction(ev2 -> {
                    stage2.close();
                });

                Scene scene2 = new Scene(vBox1, 1000, 200);
                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            btn3.setOnAction(ev2 -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Alunos sem Candidatura");

                Label lb = new Label(appManager.listarAlunosSemCand());
                lb.setFont(new Font("Arial", 14));

                Button btnOk2 = new Button("OK");
                btnOk2.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(lb, btnOk2);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                btnOk2.setOnAction(ev3 -> {
                    stage2.close();
                });

                Scene scene2 = new Scene(vBox1, 1000, 200);
                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            btnOk.setOnAction(ev4 -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn3.setOnAction(ev -> {
            btn3.setOnAction(event -> {
                Stage stage = new Stage();
                stage.setTitle("Listar Propostas");

                Label titulo = new Label("Listar Propostas");
                titulo.setFont(new Font("Arial", 30));

                CheckBox cb1 = new CheckBox("Autopropostas de Alunos");
                cb1.setFont(new Font("Arial", 16));
                CheckBox cb2 = new CheckBox("Propostas de Docentes");
                cb2.setFont(new Font("Arial", 16));
                CheckBox cb3 = new CheckBox("Propostas com Candidatura");
                cb3.setFont(new Font("Arial", 16));
                CheckBox cb4 = new CheckBox("Propostas sem Candidatura");
                cb4.setFont(new Font("Arial", 16));

                Button btnOk = new Button("OK");
                btnOk.setFont(new Font("Arial", 16));

                Button btnVA = new Button("Voltar Atrás");
                btnVA.setFont(new Font("Arial", 16));

                HBox hBox = new HBox(btnOk, btnVA);
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(20);

                VBox vBox = new VBox(titulo, cb1, cb2, cb3, cb4, hBox);
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(20);

                Scene scene = new Scene(vBox, 500, 500);

                btnOk.setOnAction(ev2 -> {
                    Label lb = new Label();
                    lb.setFont(new Font("Arial", 16));

                    if(cb1.isSelected())
                        lb.setText(appManager.listaAutopropostas());
                    if(cb2.isSelected())
                        lb.setText(lb.getText() + appManager.listaPropDocentes());
                    if(cb3.isSelected())
                        lb.setText(lb.getText() + appManager.listarPropComCand());
                    if(cb4.isSelected())
                        lb.setText(lb.getText() + appManager.listarPropSemCand());

                    if(cb1.isSelected() || cb2.isSelected() || cb3.isSelected() || cb4.isSelected()){
                        Stage stage2 = new Stage();

                        Button btnOk2 = new Button("OK");
                        btnOk2.setFont(new Font("Arial", 16));

                        VBox vBox1 = new VBox(lb, btnOk2);
                        vBox1.setAlignment(Pos.CENTER);
                        vBox1.setSpacing(20);

                        Scene scene2 = new Scene(vBox1, 1000, 400);

                        btnOk2.setOnAction(ev4 -> {
                            stage2.close();
                        });

                        stage2.setScene(scene2);
                        stage2.showAndWait();
                    }
                });

                btnVA.setOnAction(ev2 -> {
                    stage.close();
                });


                stage.setScene(scene);
                stage.showAndWait();
            });
        });

        btn4.setOnAction(ev -> {
            appManager.fase1();
        });

        btn5.setOnAction(ev -> {
            appManager.fase3();
        });

        btn6.setOnAction( ev -> {
            Platform.exit();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.FASE2FECHADA){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}

