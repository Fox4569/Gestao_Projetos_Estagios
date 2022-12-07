package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

import javax.swing.*;

public class GestaoDocentesState extends AppStateAdapter {
    protected GestaoDocentesState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.GESTAO_DOCENTES;
    }

    @Override
    public boolean fase1() {
        changeState(AppState.FASE1);
        return true;
    }

    @Override
    public boolean add(String nomeFich) {
        app.lerDocentes(nomeFich);
        changeState(AppState.GESTAO_DOCENTES);
        return true;
    }

    @Override
    public boolean eliminarDocente(String email) {
        if(app.eliminarDocente(email)){
            changeState(AppState.GESTAO_DOCENTES);
            return true;
        }
        return false;
    }

    public String listar(){
        return app.listaDocentes();
    }

    @Override
    public boolean escreverDocentes(String nome) {
        if(app.escreverDocentes(nome)) {
            changeState(AppState.GESTAO_DOCENTES);
            return true;
        }
        return false;
    }
}
