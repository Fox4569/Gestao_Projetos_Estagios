package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

import java.util.HashMap;
import java.util.Map;

public class Fase5UI extends BorderPane {
    AppManager appManager;
    Label titulo;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    final CategoryAxis x = new CategoryAxis();
    final NumberAxis y = new NumberAxis();
    BarChart<String,Number> bc =  new BarChart<String,Number>(x,y);

    public Fase5UI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Consulta");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Alunos com Propostas Atribuídas");
        btn2 = new Button("Alunos sem Propostas Atribuídas");
        btn3 = new Button("Propostas Disponíveis");
        btn4 = new Button("Propostas Atribuídas");
        btn5 = new Button("Dados sobre as Orientações");
        btn6 = new Button("Resumos do Processo");
        btn7 = new Button("Exportar dados para ficheiro");
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

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btn1.setOnAction(event -> {
            String alunos = appManager.listarAlunosComPropAtribuida();
            Stage stage = new Stage();
            stage.setTitle("Alunos com Proposta Atribuida");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Alunos com Proposta Atribuida");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1000, 400);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn2.setOnAction(event -> {
            String alunos = appManager.listarAlunosSemPropAtribuida();
            Stage stage = new Stage();
            stage.setTitle("Alunos com Proposta Atribuida");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Alunos sem Proposta Atribuida");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1000, 400);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn3.setOnAction(event -> {
            String alunos = appManager.listarPropNaoAtribuidas();
            Stage stage = new Stage();
            stage.setTitle("Propostas Não Atribuídas");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Propostas Não Atribuídas");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 500, 500);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn4.setOnAction(event -> {
            String alunos = appManager.listarPropAtribuidas();
            Stage stage = new Stage();
            stage.setTitle("Propostas Atribuídas");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Propostas Atribuídas");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1000, 400);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });


        btn5.setOnAction(event -> {
            String alunos = appManager.dadosOrientadores();
            Stage stage = new Stage();
            stage.setTitle("Dados sobre Orientadores");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Dados sobre Orientadores");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            HashMap<String, Integer> top;

            top = appManager.getTopDocentes();

            bc.getData().clear();

            XYChart.Series series1 = new XYChart.Series();
            for(Map.Entry<String, Integer> set : top.entrySet()){
                series1.getData().add(new XYChart.Data(set.getKey(), set.getValue()));
            }

            bc.getData().addAll(series1);

            VBox vBox = new VBox(titulo, lb, bc, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1000, 1000);

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn6.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Resumo do Processo");

            Label titulo = new Label("Resumo do Processo");
            titulo.setFont(new Font("Arial", 30));

            Button btn1 = new Button("Distribuição dos Estágios/Projetos por Ramos");
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

            btn1.setOnAction(ev -> {
                HashMap<String, Integer> grafico;
                grafico = appManager.getEstagiosPorRamos();

                ObservableList<PieChart.Data> pieChartData = null;
                for (Map.Entry<String, Integer> set : grafico.entrySet()) {
                    pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data(set.getKey(), set.getValue()));
                }

                PieChart chart = new PieChart(pieChartData);

                ((VBox) scene.getRoot()).getChildren().add(chart);
            });

            btn2.setOnAction(ev -> {
                String alunos = appManager.listarAlunosComCand();
                Stage stage1 = new Stage();
                stage1.setTitle("Alunos com Candidatura");

                Label lb = new Label(alunos);
                lb.setFont(new Font("Arial", 16));

                Label titulo1 = new Label("Alunos com Candidatura");
                titulo1.setFont(new Font("Arial", 30));

                Button btnOk1 = new Button("OK");
                btnOk1.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(titulo1, lb, btnOk1);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(10);

                Scene scene1 = new Scene(vBox1, 1000, 400);

                btnOk1.setOnAction(event2 -> {
                    stage1.close();
                });

                stage1.setScene(scene1);
                stage1.showAndWait();
            });

            btn3.setOnAction(ev -> {
                String alunos = appManager.listarAlunosSemCand();
                Stage stage1 = new Stage();
                stage1.setTitle("Alunos sem Candidatura");

                Label lb = new Label(alunos);
                lb.setFont(new Font("Arial", 16));

                Label titulo1 = new Label("Alunos sem Candidatura");
                titulo1.setFont(new Font("Arial", 30));

                Button btnOk1 = new Button("OK");
                btnOk1.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(titulo1, lb, btnOk1);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(10);

                Scene scene1 = new Scene(vBox1, 1000, 400);

                btnOk1.setOnAction(event2 -> {
                    stage1.close();
                });

                stage1.setScene(scene1);
                stage1.showAndWait();
            });

            btnOk.setOnAction(ev -> {
                stage.close();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn7.setOnAction(event -> {
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

        btn8.setOnAction( ev -> {
            Platform.exit();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.FASE5){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
