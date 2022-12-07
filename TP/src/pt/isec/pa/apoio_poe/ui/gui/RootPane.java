package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.data.AppManager;
import pt.isec.pa.apoio_poe.ui.gui.util.ToastMessage;

public class RootPane extends BorderPane {
    AppManager appManager;
    
    public RootPane(AppManager appManager){
        this.appManager = appManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        StackPane stackPane = new StackPane(
                new Fase1UI(appManager),
                new GestaoAlunosUI(appManager),
                new GestaoDocentesUI(appManager),
                new GestaoPropostasUI(appManager),
                new Fase1FechadaUI(appManager),
                new Fase2UI(appManager),
                new GestaoCandidaturasUI(appManager),
                new Fase2FechadaUI(appManager),
                new Fase3UI(appManager),
                new AtribuicaoManualPropUI(appManager),
                new DecideEmpateUI(appManager),
                new Fase3FechadaUI(appManager),
                new Fase4UI(appManager),
                new GestaoOrientadoresUI(appManager),
                new EsperaEscolhaUI(appManager),
                new DecideEmpateUI(appManager),
                new Fase5UI(appManager)
                );

        this.setCenter(stackPane);
        setTop(createMenu());
    }

    private void registerHandlers() {
        appManager.addPropertyChangeListener(evt -> { update(); });
    }

    private void update() {
    }

    MenuBar createMenu() {
        MenuItem mnSave       = new MenuItem("Salvar");
        MenuItem mnLoad      = new MenuItem("Carregar");
        MenuItem mnAbout      = new MenuItem("Sobre...");
        MenuItem mnExit       = new MenuItem("Sair");
        final CheckMenuItem mnTab   = new CheckMenuItem("Tabs");
        mnSave.setOnAction(event -> {
                if(appManager.save())
                    ToastMessage.show(getScene().getWindow(), "Dados salvos com sucesso!");
                else
                    ToastMessage.show(getScene().getWindow(), "Erro a salvar os dados!");
        });
        mnLoad.setOnAction(event -> {
            if(appManager.load())
                ToastMessage.show(getScene().getWindow(), "Dados carregados com sucesso!");
            else
                ToastMessage.show(getScene().getWindow(), "Erro no carregamento dos dados!");
        });
        mnAbout.setOnAction(event -> showAbout());
        mnExit.setOnAction(event -> Platform.exit());
        mnTab.fire();
        return new MenuBar(
                new Menu("Ficheiro",null, mnSave, mnLoad, mnAbout,new SeparatorMenuItem(),mnExit)
        );
    }

    private void showAbout() {
        final Stage stage = new Stage();
        String text = """
                  
                        DEIS-ISEC
                           LEI
                
                  Programação Avançada
                
                
                (c) João Baptista
                    Pedro Sequeira
                    2022
                
                """;
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefWidth(200);
        textArea.setPrefHeight(220);

        textArea.setStyle("-fx-font-family: 'Courier New';");

        textArea.setText(text);
        Button btnClose = new Button("Close");
        btnClose.setOnAction(event -> stage.close());
        btnClose.setCursor(Cursor.CLOSED_HAND);
        stage.setWidth(250);
        stage.setHeight(260);
        VBox vBox = new VBox(textArea,btnClose);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(vBox));
        stage.setTitle("Trabalho Prático");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.getScene().getWindow());
        stage.showAndWait();
    }
}
