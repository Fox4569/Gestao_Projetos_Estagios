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

import java.security.spec.ECField;

public class GestaoAlunosUI extends BorderPane {
    AppManager appManager;
    Label titulo, nAlunosL, nAlunos;
    Button btn1, btn2, btn3, btn4, btn5;

    public GestaoAlunosUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Gestão de Alunos");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Adicionar Alunos");
        btn2 = new Button("Eliminar Alunos");
        btn3 = new Button("Listar Alunos");
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

        titulo.setFont(new Font("Arial", 30));
        titulo.setMinHeight(100);

        nAlunosL = new Label("Número de Alunos");
        nAlunosL.setFont(new Font("Arial", 24));

        nAlunos = new Label();
        nAlunos.setFont(new Font("Arial", 24));
        nAlunos.setAlignment(Pos.CENTER);

        VBox info = new VBox(nAlunosL, nAlunos);
        info.setAlignment(Pos.BOTTOM_CENTER);
        info.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        
        this.setCenter(vBox);
        this.setBottom(info);
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });
        btn1.setOnAction( event -> {
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

            btn1.setOnAction(ev -> {
                Stage stage1 = new Stage();

                stage1.setTitle("Adicionar Alunos");


                Label nrEstudante = new Label("Nº Estudante: ");
                nrEstudante.setFont(new Font("Arial", 16));
                nrEstudante.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf1 = new TextField();
                HBox hBox1 = new HBox(nrEstudante, tf1);
                hBox1.setAlignment(Pos.CENTER);

                Label nome = new Label("Nome: ");
                nome.setFont(new Font("Arial", 16));
                nome.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf2 = new TextField();
                HBox hBox2 = new HBox(nome, tf2);
                hBox2.setAlignment(Pos.CENTER);

                Label email = new Label("Email: ");
                email.setFont(new Font("Arial", 16));
                email.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf3 = new TextField();
                HBox hBox3 = new HBox(email, tf3);
                hBox3.setAlignment(Pos.CENTER);

                Label curso = new Label("Curso: ");
                curso.setFont(new Font("Arial", 16));
                curso.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf4 = new TextField();
                HBox hBox4 = new HBox(curso, tf4);
                hBox4.setAlignment(Pos.CENTER);

                Label ramo = new Label("Ramo: ");
                ramo.setFont(new Font("Arial", 16));
                ramo.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf5 = new TextField();
                HBox hBox5 = new HBox(ramo, tf5);
                hBox5.setAlignment(Pos.CENTER);

                Label classificacao = new Label("Classificação: ");
                classificacao.setFont(new Font("Arial", 16));
                classificacao.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf6 = new TextField();
                HBox hBox6 =  new HBox(classificacao, tf6);
                hBox6.setAlignment(Pos.CENTER);

                Label estagioProjeto = new Label("Estágio/Projeto(sim/não): ");
                estagioProjeto.setFont(new Font("Arial", 16));
                estagioProjeto.setTextAlignment(TextAlignment.JUSTIFY);
                TextField tf7 = new TextField();
                HBox hBox7 = new HBox(estagioProjeto, tf7);
                hBox7.setAlignment(Pos.CENTER);

                Button btn = new Button("Confirmar");
                btn.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, btn);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(10);

                Scene scene1 = new Scene(vBox1, 700, 500);

                btn.setOnAction(ev1 -> {
                    long nEstudanteF = 0;
                    String nomeF = null, emailF = null, cursoF = null, ramoF = null;
                    double classificacaoF = 0.0;
                    boolean EePF = false;

                    try {
                        nEstudanteF = Integer.parseInt(tf1.getText());
                    }catch (Exception e){
                        tf1.clear();
                    }

                    if(tf2.getText().equals("")){
                        tf2.clear();
                    }else{
                        nomeF = tf2.getText();
                    }

                    if(tf3.getText().contains("@")){
                        emailF = tf3.getText();
                    }else{
                        tf3.clear();
                    }

                    cursoF = tf4.getText();

                    if(tf5.getText().equalsIgnoreCase("SI") || tf5.getText().equalsIgnoreCase("RAS") || tf5.getText().equalsIgnoreCase("DA")){
                        ramoF = tf5.getText();
                    }else{
                        tf5.clear();
                    }

                    try{
                        classificacaoF = Double.parseDouble(tf6.getText());
                        if(classificacaoF > 1.0 || classificacaoF < 0.0){
                            tf6.clear();
                        }
                    }catch (Exception e){
                        tf6.clear();
                    }

                    if(tf7.getText().equalsIgnoreCase("sim")){
                        EePF = true;
                    }else if(tf7.getText().equalsIgnoreCase("não"))
                        EePF = false;
                    else{
                        tf7.clear();
                    }

                    if(!tf1.getText().isEmpty() &&
                            !tf2.getText().isEmpty() &&
                            !tf3.getText().isEmpty() &&
                            !tf4.getText().isEmpty() &&
                            !tf5.getText().isEmpty() &&
                            !tf6.getText().isEmpty() &&
                            !tf7.getText().isEmpty()){
                        if(appManager.adicionarAluno(nEstudanteF, nomeF, emailF, cursoF, ramoF, classificacaoF, EePF))
                            ToastMessage.show(getScene().getWindow(), "Aluno adicionado com sucesso!");
                        else
                            ToastMessage.show(getScene().getWindow(), "Erro na adição do aluno!");
                        stage1.close();
                        stage.close();
                    }

                });

                stage1.setScene(scene1);
                stage1.showAndWait();
            });

            btn2.setOnAction(ev -> {
                Stage stage1 = new Stage();

                stage1.setTitle("Adicionar Alunos");

                Label txt = new Label("Nome do Ficheiro: ");
                txt.setFont(new Font("Arial", 14));

                TextField tf = new TextField();

                Button btn = new Button("Confirmar");

                HBox hBox = new HBox(txt, tf, btn);
                hBox.setAlignment(Pos.CENTER);

                Scene scene1 = new Scene(hBox, 400, 100);

                btn.setOnAction(ev2 -> {
                    String nomeFich = tf.getText();
                    boolean res = appManager.lerAlunos(nomeFich);
                    if(res)
                        ToastMessage.show(getScene().getWindow(), "Alunos adicionados com sucesso!");
                    else
                        ToastMessage.show(getScene().getWindow(), "Erro na adição dos alunos!");
                    stage1.close();
                    stage.close();
                });

                tf.setOnKeyPressed(keyEvent -> {
                    if(keyEvent.getCode() == KeyCode.ENTER){
                        btn.fire();
                    }
                });

                stage1.setScene(scene1);
                stage1.showAndWait();
            });

            stage.setScene(scene);
            stage.showAndWait();
        });

        btn2.setOnAction(event -> {
            Stage stage = new Stage();

            stage.setTitle("Eliminar Alunos");

            Label txt = new Label("Numero do Aluno: ");
            txt.setFont(new Font("Arial", 14));

            TextField tf = new TextField();

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                try {
                    String nAlunoS = tf.getText();
                    if (nAlunoS != null) {
                        long nAluno = Integer.parseInt(nAlunoS);
                        boolean res = appManager.eliminarAluno(nAluno);
                        if (res)
                            ToastMessage.show(getScene().getWindow(), "Aluno eliminado com sucesso!");
                        else
                            ToastMessage.show(getScene().getWindow(), "Erro na eliminação do aluno!");
                        stage.close();
                    }
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

        btn3.setOnAction(event ->{
            String alunos = appManager.listarAlunos();
            Stage stage = new Stage();
            stage.setTitle("Alunos");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Alunos");
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
                    boolean res = appManager.escreverAlunos(nomeFich);
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
        if(appManager.getState() != AppState.GESTAO_ALUNOS){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        nAlunos.setText("" + appManager.getAlunos());
    }
}
