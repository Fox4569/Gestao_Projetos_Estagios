package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.App;

public class Fase1State extends AppStateAdapter {
    protected Fase1State(AppContext context, App app) {
        super(context, app);
    }

    @Override
    public AppState getState() {
        return AppState.FASE1;
    }

    @Override
    public boolean alunos() {
        changeState(AppState.GESTAO_ALUNOS);
        return true;
    }

    @Override
    public boolean docentes() {
        changeState(AppState.GESTAO_DOCENTES);
        return true;
    }

    @Override
    public boolean propostas() {
        changeState(AppState.GESTAO_PROPOSTAS);
        return true;
    }

    @Override
    public boolean fechaFase() {
        if(app.fechaFase1()){
            changeState(AppState.FASE2);
            return true;
        }
        return false;
    }

    @Override
    public boolean fase2() {
        changeState(AppState.FASE2);
        return true;
    }
}
