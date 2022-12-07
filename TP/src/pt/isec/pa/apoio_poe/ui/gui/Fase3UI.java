package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

public class Fase3UI extends BorderPane {
    AppManager appManager;
    Label titulo;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;

    public Fase3UI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Atribuição de Propostas");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Atribuir automaticamente autopropostas e propostas de docentes");
        btn2 = new Button("Atribuir automaticamente propostas");
        btn3 = new Button("Atribuir manualmente propostas");
        btn4 = new Button("Remover proposta");
        btn5 = new Button("Remover todas as propostas possíveis");
        btn6 = new Button("Listar Alunos");
        btn7 = new Button("Listar Propostas");
        btn8 = new Button("Exportar dados para ficheiro");
        btn9 = new Button("Fechar Fase");
        btn10 = new Button("Opções de Candidatura");
        btn11 = new Button("Atribuição de Orientadores");
        btn12 = new Button("Sair");
        VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12);

        btn1.setFont(new Font("Arial", 16));
        btn1.setMinSize(500, 10);

        btn2.setFont(new Font("Arial", 16));
        btn2.setMinSize(500, 10);

        btn3.setFont(new Font("Arial", 16));
        btn3.setMinSize(500, 10);

        btn4.setFont(new Font("Arial", 16));
        btn4.setMinSize(500, 10);

        btn5.setFont(new Font("Arial", 16));
        btn5.setMinSize(500, 10);

        btn6.setFont(new Font("Arial", 16));
        btn6.setMinSize(500, 10);

        btn7.setFont(new Font("Arial", 16));
        btn7.setMinSize(500, 10);

        btn8.setFont(new Font("Arial", 16));
        btn8.setMinSize(500, 10);

        btn9.setFont(new Font("Arial", 16));
        btn9.setMinSize(500, 10);

        btn10.setFont(new Font("Arial", 16));
        btn10.setMinSize(500, 10);

        btn11.setFont(new Font("Arial", 16));
        btn11.setMinSize(500, 10);

        btn12.setFont(new Font("Arial", 16));
        btn12.setMinSize(500, 10);

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btn1.setOnAction(ev-> {
            if(appManager.atribuicaoAutoPropostasEDocentes())
                ToastMessage.show(getScene().getWindow(), "Atribuições efetuadas com sucesso!");
            else
                ToastMessage.show(getScene().getWindow(), "Erro nas atribuições!");
        });

        btn2.setOnAction(ev -> {
            if(!appManager.atribuicaoPropostas()) {
                appManager.decideEmpate();
            }else{
                ToastMessage.show(getScene().getWindow(), "Atribuições efetuadas com sucesso!");
            }
        });

        btn3.setOnAction(ev -> {
            if(appManager.verificaTamanhos())
                appManager.atribuicaoManual();
            else
                ToastMessage.show(getScene().getWindow(), "Não é possível efetuar atribuições manuais!");
        });

        btn4.setOnAction(ev -> {
            Stage stage = new Stage();
            stage.setTitle("Remover Proposta");

            Label titulo = new Label("Remover Proposta");
            titulo.setFont(new Font("Arial", 30));

            Label lb = new Label(appManager.listaRemocaoManualPropostas());
            lb.setFont(new Font("Arial", 16));

            Label lb2 = new Label("Introduza o valor: ");
            lb2.setFont(new Font("Arial", 16));

            TextField tf = new TextField();
            tf.setFont(new Font("Arial", 16));

            Button btnC = new Button("Confirmar");
            btnC.setFont(new Font("Arial", 16));

            HBox hBox = new HBox(lb2, tf, btnC);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);

            VBox vBox = new VBox(titulo, lb, hBox);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(20);

            btnC.setOnAction(ev1 -> {
                String valorS = tf.getText();
                int valor = Integer.parseInt(valorS);
                if(appManager.remocaoManualPropostas(valor))
                    ToastMessage.show(getScene().getWindow(), "Eliminação efetuada com sucesso!");
                else
                    ToastMessage.show(getScene().getWindow(), "Erro na eliminação!");
                stage.close();
            });

            Scene scene2 = new Scene(vBox, 500, 400);
            stage.setScene(scene2);
            stage.showAndWait();
        });

        btn5.setOnAction(ev -> {
            if(appManager.remocaoTodasPropostas())
                ToastMessage.show(getScene().getWindow(), "Eliminações efetuadas com sucesso!");
            else
                ToastMessage.show(getScene().getWindow(), "Erro na eliminação!");
        });

        btn6.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Listar Alunos");

            Label titulo = new Label("Listar Alunos");
            titulo.setFont(new Font("Arial", 30));

            Button btn1 = new Button("Alunos com Autoproposta");
            btn1.setFont(new Font("Arial", 16));
            Button btn2 = new Button("Alunos com Candidatura");
            btn2.setFont(new Font("Arial", 16));
            Button btn3 = new Button("Alunos com Propostas Atribuídas");
            btn3.setFont(new Font("Arial", 16));
            Button btn4 = new Button("Alunos sem Propostas Atribuídas");
            btn4.setFont(new Font("Arial", 16));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, btn1, btn2, btn3, btn4, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(20);

            Scene scene = new Scene(vBox, 500, 500);

            btn1.setOnAction(ev -> {
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
                stage2.setTitle("Alunos com Propostas Atribuídas");

                Label lb = new Label(appManager.listarAlunosComPropAtribuida());
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

            btn4.setOnAction(ev2 -> {
                Stage stage2 = new Stage();
                stage2.setTitle("Alunos sem Propostas Atribuídas");

                Label lb = new Label(appManager.listarAlunosSemPropAtribuida());
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

        btn7.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Listar Propostas");

            Label titulo = new Label("Listar Propostas");
            titulo.setFont(new Font("Arial", 30));

            CheckBox cb1 = new CheckBox("Autopropostas de Alunos");
            cb1.setFont(new Font("Arial", 16));
            CheckBox cb2 = new CheckBox("Propostas de Docentes");
            cb2.setFont(new Font("Arial", 16));
            CheckBox cb3 = new CheckBox("Propostas Atribuídas");
            cb3.setFont(new Font("Arial", 16));
            CheckBox cb4 = new CheckBox("Propostas Não Atribuídas");
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

            btnOk.setOnAction(ev -> {
                Label lb = new Label();
                lb.setFont(new Font("Arial", 16));

                if(cb1.isSelected())
                    lb.setText(appManager.listaAutopropostas());
                if(cb2.isSelected())
                    lb.setText(lb.getText() + appManager.listaPropDocentes());
                if(cb3.isSelected())
                    lb.setText(lb.getText() + appManager.listarPropAtribuidas());
                if(cb4.isSelected())
                    lb.setText(lb.getText() + appManager.listarPropNaoAtribuidas());

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

        btn8.setOnAction(ev -> {
            Stage stage = new Stage();

            stage.setTitle("Exportar Dados");

            Label txt = new Label("Numero do Ficheiro: ");
            txt.setFont(new Font("Arial", 14));
            //txt.setAlignment(Pos.BOTTOM_LEFT);

            TextField tf = new TextField();

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(event -> {
                String nomeFich = tf.getText();
                if(nomeFich != null) {
                    boolean res = appManager.exportarDadosFase3(nomeFich);
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

        btn9.setOnAction(ev -> {
            appManager.fechaFase();
        });

        btn10.setOnAction(ev -> {
            appManager.fase2();
        });

        btn11.setOnAction(ev -> {
            appManager.fase4();
        });

        btn12.setOnAction( ev -> {
            Platform.exit();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.FASE3){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
