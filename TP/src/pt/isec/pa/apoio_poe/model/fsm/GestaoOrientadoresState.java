package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class GestaoOrientadoresState extends AppStateAdapter{
    protected GestaoOrientadoresState(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public boolean esperaEscolha() {
        changeState(AppState.ESPERAESCOLHA);
        return true;
    }

    @Override
    public String listaOrientadoresEAlunos() {
        return app.listaOrientadoresEAlunos();
    }

    @Override
    public AppState getState() {
        return AppState.GESTAO_ORIENTADORES;
    }

    @Override
    public boolean fase4() {
        changeState(AppState.FASE4);
        return true;
    }
}
