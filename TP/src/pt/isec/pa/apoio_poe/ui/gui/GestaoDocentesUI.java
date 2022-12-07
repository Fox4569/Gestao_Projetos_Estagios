package pt.isec.pa.apoio_poe.ui.gui;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.model.fsm.AppState;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

public class GestaoDocentesUI extends BorderPane {
    AppManager appManager;
    Label titulo, nDocentesL, nDocentes;
    Button btn1, btn2, btn3, btn4, btn5;

    public GestaoDocentesUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Gestão de Docentes");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Adicionar Docentes");
        btn2 = new Button("Eliminar Docentes");
        btn3 = new Button("Listar Docentes");
        btn4 = new Button("Exportar dados para ficheiro");
        btn5 = new Button("Voltar atrás");
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

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        nDocentesL = new Label("Número de Docentes");
        nDocentesL.setFont(new Font("Arial", 24));

        nDocentes = new Label();
        nDocentes.setFont(new Font("Arial", 24));
        nDocentes.setAlignment(Pos.CENTER);

        VBox info = new VBox(nDocentesL, nDocentes);
        info.setAlignment(Pos.BOTTOM_CENTER);
        info.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        this.setCenter(vBox);
        this.setBottom(info);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });

        btn1.setOnAction(event -> {
            Stage stage = new Stage();

            Label titulo = new Label("Adicionar por: ");
            titulo.setFont(new Font("Arial", 30));

            Button btn1 = new Button("Teclado");
            btn1.setFont(new Font("Arial", 16));

            Button btn2 = new Button("Ficheiro");
            btn2.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, btn1, btn2);
            vBox.setAlignment(Pos.CENTER);

            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 500, 300);

            btn1.setOnAction(ev1 -> {
                Stage stage1 = new Stage();
                stage1.setTitle("Adicionar Docentes");

                Label nome = new Label("Nome: ");
                nome.setFont(new Font("Arial", 16));
                nome.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf1 = new TextField();
                HBox hBox1 = new HBox(nome, tf1);
                hBox1.setAlignment(Pos.CENTER);

                Label email = new Label("Email: ");
                email.setFont(new Font("Arial", 16));
                email.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf2 = new TextField();
                HBox hBox2 = new HBox(email, tf2);
                hBox2.setAlignment(Pos.CENTER);

                Button btn = new Button("Confirmar");
                btn.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(hBox1, hBox2, btn);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(10);

                Scene scene1 = new Scene(vBox1, 700, 500);

                btn.setOnAction(ev2 -> {
                    String nomeF = null, emailF = null;

                    if(tf1.getText().equals("")){
                        tf1.clear();
                    }else{
                        nomeF = tf1.getText();
                    }

                    if(tf2.getText().contains("@")){
                        emailF = tf2.getText();
                    }else{
                        tf2.clear();
                    }

                    if(!tf1.getText().isEmpty() &&
                            !tf2.getText().isEmpty()){
                        if(appManager.adicionarDocente(nomeF, emailF))
                            ToastMessage.show(getScene().getWindow(), "Docente adicionado com sucesso!");
                        else
                            ToastMessage.show(getScene().getWindow(), "Erro na adição do docente!");
                        stage1.close();
                        stage.close();
                    }
                });
                stage1.setScene(scene1);
                stage1.showAndWait();
            });

            btn2.setOnAction(ev2 -> {
                Stage stage2 = new Stage();

                stage2.setTitle("Adicionar Docentes");

                Label txt = new Label("Nome do Ficheiro: ");
                txt.setFont(new Font("Arial", 14));

                TextField tf = new TextField();

                Button btn = new Button("Confirmar");

                HBox hBox = new HBox(txt, tf, btn);
                hBox.setAlignment(Pos.CENTER);

                Scene scene2 = new Scene(hBox, 400, 100);

                btn.setOnAction(ev -> {
                    String nomeFich = tf.getText();
                    boolean res = appManager.lerDocentes(nomeFich);
                    if(res)
                        ToastMessage.show(getScene().getWindow(), "Docentes adicinados com sucesso!");
                    else
                        ToastMessage.show(getScene().getWindow(), "Erro na adição dos docentes!");
                    stage.close();
                    stage2.close();
                });

                tf.setOnKeyPressed(keyEvent -> {
                    if(keyEvent.getCode() == KeyCode.ENTER){
                        btn.fire();
                    }
                });

                stage2.setScene(scene2);
                stage2.showAndWait();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });


        btn2.setOnAction(event -> {
            Stage stage = new Stage();

            stage.setTitle("Eliminar Docentes");

            Label txt = new Label("Email do Docente: ");
            txt.setFont(new Font("Arial", 14));
            //txt.setAlignment(Pos.BOTTOM_LEFT);

            TextField tf = new TextField();
            //tf.setPromptText("Email do docente...");

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                String emailDocente = tf.getText();
                boolean res = appManager.eliminarDocente(emailDocente);
                if(res)
                    ToastMessage.show(getScene().getWindow(), "Docente eliminado com sucesso!");
                else
                    ToastMessage.show(getScene().getWindow(), "Erro na eliminação do docente!");
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

        btn3.setOnAction(event ->{
            String alunos = appManager.listarDocentes();
            Stage stage = new Stage();
            stage.setTitle("Docentes");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Docentes");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 400, 300);

            btnOk.setOnAction(ev -> {
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
            //txt.setAlignment(Pos.BOTTOM_LEFT);

            TextField tf = new TextField();
            //tf.setPromptText("Nome do ficheiro...");

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                String nomeFich = tf.getText();
                if(nomeFich != null) {
                    boolean res = appManager.escreverDocentes(nomeFich);
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
            appManager.fase1();
        });
    }

    private void update() {
        if(appManager.getState() != AppState.GESTAO_DOCENTES){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        nDocentes.setText("" + appManager.getDocentes());
    }
}
