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

public class GestaoPropostasUI extends BorderPane {
    AppManager appManager;
    Label titulo, nPropostasL, nPropostas;
    Button btn1, btn2, btn3, btn4, btn5;

    public GestaoPropostasUI(AppManager appManager) {
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        titulo = new Label("Gestão de Propostas");
        titulo.setTextFill(Color.DARKBLUE);

        btn1 = new Button("Adicionar Propostas");
        btn2 = new Button("Eliminar Propostas");
        btn3 = new Button("Listar Propostas");
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

        nPropostasL = new Label("Número de Propostas");
        nPropostasL.setFont(new Font("Arial", 24));

        nPropostas = new Label();
        nPropostas.setFont(new Font("Arial", 24));
        nPropostas.setAlignment(Pos.CENTER);

        VBox info = new VBox(nPropostasL, nPropostas);
        info.setAlignment(Pos.BOTTOM_CENTER);
        info.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

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

                stage1.setTitle("Adicionar Propostas");

                Button btn3 = new Button("Projeto");
                btn3.setFont(new Font("Arial", 16));

                Button btn4 = new Button("Estágio");
                btn4.setFont(new Font("Arial", 16));

                Button btn5 = new Button("Autoproposta");
                btn5.setFont(new Font("Arial", 16));

                VBox vBox1 = new VBox(btn3, btn4, btn5);
                vBox1.setAlignment(Pos.CENTER);
                vBox1.setSpacing(20);

                Scene scene1 = new Scene(vBox1, 400, 400);

                btn3.setOnAction(ev1 -> {
                    Stage stage2 = new Stage();
                    stage2.setTitle("Proposta");

                    Label id = new Label("Id: ");
                    id.setFont(new Font("Arial", 16));
                    id.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf1 = new TextField();
                    HBox hBox1 = new HBox(id, tf1);
                    hBox1.setAlignment(Pos.CENTER);

                    Label ramo = new Label("Ramo: ");
                    ramo.setFont(new Font("Arial", 16));
                    ramo.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf2 = new TextField();
                    HBox hBox2 = new HBox(ramo, tf2);
                    hBox2.setAlignment(Pos.CENTER);

                    Label tituloS = new Label("Título: ");
                    tituloS.setFont(new Font("Arial", 16));
                    tituloS.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf3 = new TextField();
                    HBox hBox3 = new HBox(tituloS, tf3);
                    hBox3.setAlignment(Pos.CENTER);

                    Label email = new Label("Email: ");
                    email.setFont(new Font("Arial", 16));
                    email.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf4 = new TextField();
                    HBox hBox4 = new HBox(email, tf4);
                    hBox4.setAlignment(Pos.CENTER);

                    Label nAluno = new Label("nAluno: ");
                    nAluno.setFont(new Font("Arial", 16));
                    nAluno.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf5 = new TextField();
                    HBox hBox5 = new HBox(nAluno, tf5);
                    hBox5.setAlignment(Pos.CENTER);

                    Button btn = new Button("Confirmar");

                    VBox vBox2 = new VBox(hBox1, hBox2, hBox3, hBox4, hBox5, btn);
                    vBox2.setAlignment(Pos.CENTER);
                    vBox2.setSpacing(10);

                    Scene scene2 = new Scene(vBox2, 700, 500);

                    btn.setOnAction(ev2 -> {
                        String idF = null, ramoF = null, tituloF = null, emailDocenteF = null;
                        long nAlunoF = -1;

                        if(tf1.getText().equals("") || tf1.getText().charAt(0) != 'P'){
                            tf1.clear();
                        }else{
                            idF = tf1.getText();
                        }

                        if(tf2.getText().equalsIgnoreCase("SI") || tf2.getText().equalsIgnoreCase("RAS") || tf2.getText().equalsIgnoreCase("DA")){
                            ramoF = tf5.getText();
                        }else{
                            tf2.clear();
                        }

                        tituloF = tf3.getText();

                        if(tf4.getText().contains("@")){
                            emailDocenteF = tf4.getText();
                        }else{
                            tf4.clear();
                        }

                        try {
                            nAlunoF = Integer.parseInt(tf5.getText());
                        }catch (Exception e){
                            tf5.clear();
                        }

                        if(!tf1.getText().isEmpty() &&
                                !tf2.getText().isEmpty() &&
                                !tf3.getText().isEmpty() &&
                                !tf4.getText().isEmpty() &&
                                tf5.getText().isEmpty()){
                            if(appManager.adicionarProjetoSAluno(idF, ramoF, tituloF, emailDocenteF))
                                ToastMessage.show(getScene().getWindow(), "Proposta adicionada com sucesso!");
                            else
                                ToastMessage.show(getScene().getWindow(), "Erro na adição da proposta!");
                            stage1.close();
                            stage.close();
                            stage2.close();
                        }else if(!tf1.getText().isEmpty() &&
                                !tf2.getText().isEmpty() &&
                                !tf3.getText().isEmpty() &&
                                !tf4.getText().isEmpty() &&
                                !tf5.getText().isEmpty()) {
                            if (appManager.adicionarProjetoCAluno(idF, ramoF, tituloF, emailDocenteF, nAlunoF))
                                ToastMessage.show(getScene().getWindow(), "Proposta adicionada com sucesso!");
                            else
                                ToastMessage.show(getScene().getWindow(), "Erro na adição da proposta!");
                            stage1.close();
                            stage.close();
                            stage2.close();
                        }
                    });

                    stage2.setScene(scene2);
                    stage2.showAndWait();
                });

                btn4.setOnAction(ev1 -> {
                    Stage stage2 = new Stage();
                    stage2.setTitle("Proposta");

                    Label id = new Label("Id: ");
                    id.setFont(new Font("Arial", 16));
                    id.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf1 = new TextField();
                    HBox hBox1 = new HBox(id, tf1);
                    hBox1.setAlignment(Pos.CENTER);

                    Label ramo = new Label("Ramo: ");
                    ramo.setFont(new Font("Arial", 16));
                    ramo.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf2 = new TextField();
                    HBox hBox2 = new HBox(ramo, tf2);
                    hBox2.setAlignment(Pos.CENTER);

                    Label tituloS = new Label("Título: ");
                    tituloS.setFont(new Font("Arial", 16));
                    tituloS.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf3 = new TextField();
                    HBox hBox3 = new HBox(tituloS, tf3);
                    hBox3.setAlignment(Pos.CENTER);

                    Label idAcS = new Label("Id Acolhimento: ");
                    idAcS.setFont(new Font("Arial", 16));
                    idAcS.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf4 = new TextField();
                    HBox hBox4 = new HBox(idAcS, tf4);
                    hBox4.setAlignment(Pos.CENTER);

                    Label nAluno = new Label("nAluno: ");
                    nAluno.setFont(new Font("Arial", 16));
                    nAluno.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf5 = new TextField();
                    HBox hBox5 = new HBox(nAluno, tf5);
                    hBox5.setAlignment(Pos.CENTER);

                    Button btn = new Button("Confirmar");

                    VBox vBox2 = new VBox(hBox1, hBox2, hBox3, hBox4, hBox5, btn);
                    vBox2.setAlignment(Pos.CENTER);
                    vBox2.setSpacing(10);

                    Scene scene2 = new Scene(vBox2, 700, 500);

                    btn.setOnAction(ev2 -> {
                        String idF = null, ramoF = null, tituloF = null, idAcoF = null;
                        long nAlunoF = -1;

                        if(tf1.getText().equals("") || tf1.getText().charAt(0) != 'P'){
                            tf1.clear();
                        }else{
                            idF = tf1.getText();
                        }

                        if(tf2.getText().equalsIgnoreCase("SI") || tf2.getText().equalsIgnoreCase("RAS") || tf2.getText().equalsIgnoreCase("DA")){
                            ramoF = tf2.getText();
                        }else{
                            tf2.clear();
                        }

                        tituloF = tf3.getText();
                        idAcoF = tf4.getText();

                        try {
                            nAlunoF = Long.parseLong(tf5.getText());
                        }catch (Exception e){
                            tf5.clear();
                        }

                        if(!tf1.getText().isEmpty() &&
                                !tf2.getText().isEmpty() &&
                                !tf3.getText().isEmpty() &&
                                !tf4.getText().isEmpty() &&
                                tf5.getText().isEmpty()){
                            if(appManager.adicionarEstagioSAluno(idF, ramoF, tituloF, idAcoF))
                                ToastMessage.show(getScene().getWindow(), "Proposta adicionada com sucesso!");
                            else
                                ToastMessage.show(getScene().getWindow(), "Erro na adição da proposta!");
                            stage1.close();
                            stage.close();
                            stage2.close();
                        }else if(!tf1.getText().isEmpty() &&
                                !tf2.getText().isEmpty() &&
                                !tf3.getText().isEmpty() &&
                                !tf4.getText().isEmpty() &&
                                !tf5.getText().isEmpty()) {
                            if (appManager.adicionarEstagioCAluno(idF, ramoF, tituloF, idAcoF, nAlunoF))
                                ToastMessage.show(getScene().getWindow(), "Proposta adicionada com sucesso!");
                            else
                                ToastMessage.show(getScene().getWindow(), "Erro na adição da proposta!");
                            stage1.close();
                            stage.close();
                            stage2.close();
                        }
                    });

                    stage2.setScene(scene2);
                    stage2.showAndWait();
                });

                btn5.setOnAction(ev1 -> {
                    Stage stage2 = new Stage();
                    stage2.setTitle("Proposta");

                    Label id = new Label("Id: ");
                    id.setFont(new Font("Arial", 16));
                    id.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf1 = new TextField();
                    HBox hBox1 = new HBox(id, tf1);
                    hBox1.setAlignment(Pos.CENTER);

                    Label tituloS = new Label("Título: ");
                    tituloS.setFont(new Font("Arial", 16));
                    tituloS.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf2 = new TextField();
                    HBox hBox2 = new HBox(tituloS, tf2);
                    hBox2.setAlignment(Pos.CENTER);

                    Label nAluno = new Label("nAluno: ");
                    nAluno.setFont(new Font("Arial", 16));
                    nAluno.setTextAlignment(TextAlignment.JUSTIFY);
                    TextField tf3 = new TextField();
                    HBox hBox3 = new HBox(nAluno, tf3);
                    hBox3.setAlignment(Pos.CENTER);

                    Button btn = new Button("Confirmar");

                    VBox vBox2 = new VBox(hBox1, hBox2, hBox3, btn);
                    vBox2.setAlignment(Pos.CENTER);
                    vBox2.setSpacing(10);

                    Scene scene2 = new Scene(vBox2, 700, 500);

                    btn.setOnAction(ev2 -> {
                        String idF = null, tituloF = null;
                        long nAlunoF = -1;

                        if(tf1.getText().equals("") || tf1.getText().charAt(0) != 'P'){
                            tf1.clear();
                        }else{
                            idF = tf1.getText();
                        }

                        tituloF = tf3.getText();

                        try {
                            nAlunoF = Long.parseLong(tf3.getText());
                        }catch (Exception e){
                            tf3.clear();
                        }

                        if(!tf1.getText().isEmpty() &&
                                !tf2.getText().isEmpty() &&
                                !tf3.getText().isEmpty()) {
                            if (appManager.adicionarAutoprop(idF, tituloF, nAlunoF))
                                ToastMessage.show(getScene().getWindow(), "Proposta adicionada com sucesso!");
                            else
                                ToastMessage.show(getScene().getWindow(), "Erro na adição da proposta!");
                            stage1.close();
                            stage.close();
                            stage2.close();
                        }
                    });

                    stage2.setScene(scene2);
                    stage2.showAndWait();
                });
                stage1.setScene(scene1);
                stage1.showAndWait();
            });

            btn2.setOnAction(ev -> {
                Stage stage2 = new Stage();

                stage2.setTitle("Adicionar Propostas");

                Label txt = new Label("Nome do Ficheiro: ");
                txt.setFont(new Font("Arial", 14));

                TextField tf = new TextField();

                Button btn = new Button("Confirmar");

                HBox hBox = new HBox(txt, tf, btn);
                hBox.setAlignment(Pos.CENTER);

                Scene scene2 = new Scene(hBox, 400, 100);

                btn.setOnAction(ev2 -> {
                    String nomeFich = tf.getText();
                    boolean res = appManager.lerPropostas(nomeFich);
                    if(res)
                        ToastMessage.show(getScene().getWindow(), "Propostas adicionadas com sucesso!");
                    else
                        ToastMessage.show(getScene().getWindow(), "Erro na adição das propostas!");
                    stage2.close();
                    stage.close();
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

            stage.setTitle("Eliminar Propostas");

            Label txt = new Label("Código da Proposta: ");
            txt.setFont(new Font("Arial", 14));

            TextField tf = new TextField();

            Button btn = new Button("Confirmar");

            HBox hBox = new HBox(txt, tf, btn);
            hBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(hBox, 400, 100);

            btn.setOnAction(ev -> {
                String codProp = tf.getText();
                boolean res = appManager.eliminarProposta(codProp);
                if(res)
                    ToastMessage.show(getScene().getWindow(), "Proposta eliminada com sucesso!");
                else
                    ToastMessage.show(getScene().getWindow(), "Erro na eliminação da proposta!");
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
            String alunos = appManager.listarPropostas();
            Stage stage = new Stage();
            stage.setTitle("Propostas");

            Label lb = new Label(alunos);
            lb.setFont(new Font("Arial", 16));

            Label titulo = new Label("Lista de Propostas");
            titulo.setFont(new Font("Arial", 30));

            Button btnOk = new Button("OK");
            btnOk.setFont(new Font("Arial", 16));

            VBox vBox = new VBox(titulo, lb, btnOk);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox, 1200, 500);

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
                    boolean res = appManager.escreverPropostas(nomeFich);
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
        if(appManager.getState() != AppState.GESTAO_PROPOSTAS){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        nPropostas.setText("" + appManager.getPropostas());
    }
}

